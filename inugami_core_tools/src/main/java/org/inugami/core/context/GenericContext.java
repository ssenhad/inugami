/* --------------------------------------------------------------------
 *  Inugami  
 * --------------------------------------------------------------------
 * 
 * This program is free software: you can redistribute it and/or modify  
 * it under the terms of the GNU General Public License as published by  
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License 
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.inugami.core.context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import javax.annotation.Priority;

import org.inugami.api.constants.JvmKeyValues;
import org.inugami.api.ctx.BootstrapContext;
import org.inugami.api.handlers.Handler;
import org.inugami.api.listeners.EngineListener;
import org.inugami.api.loggers.Loggers;
import org.inugami.api.models.plugins.ManifestInfo;
import org.inugami.api.providers.Provider;
import org.inugami.api.spi.PropertiesProducerSpi;
import org.inugami.api.tools.NamedComponent;
import org.inugami.commons.messages.MessagesServices;
import org.inugami.commons.spi.SpiLoader;
import org.inugami.commons.threads.ThreadsExecutorService;
import org.inugami.configuration.models.plugins.PluginConfiguration;
import org.inugami.configuration.models.plugins.PropertyModel;
import org.inugami.core.services.cache.CacheService;
import org.inugami.core.services.connectors.HttpConnector;

/**
 * GenericContext
 * 
 * @author patrickguillerm
 * @since 3 sept. 2018
 */
public class GenericContext<T> implements BootstrapContext<T> {
    
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    private static final SpiLoader                           SPI_LOADER       = new SpiLoader();
    
    private static final boolean                             VERBOSE          = Boolean.parseBoolean(JvmKeyValues.APPLICATION_VERBOSE.or("false"));
    
    private static final CacheService                        CACHE            = new CacheService();
    
    private final static Map<String, HttpConnector>          HTTP_CONNECTORS  = new ConcurrentHashMap<>();
    
    private final static Map<String, ThreadsExecutorService> EXECUTORS        = new ConcurrentHashMap<>();
    
    private final static Set<String>                         CRON_EXPRESSIONS = new HashSet<>();
    
    private final static Set<NamedComponent>                 NAMED_COMPONENTS = new HashSet<>();
    
    private final static List<EngineListener>                ENGINE_LISTENERS = new ArrayList<>();
    
    private Function<String, Provider>                       providerHandler;
    
    private Function<String, ? extends Handler>              handler;
    
    // =========================================================================
    // CONTEXT SPI
    // =========================================================================
    public CacheService getCache() {
        return CACHE;
    }
    
    public boolean isVerbose() {
        return VERBOSE;
    }
    
    public ThreadsExecutorService getThreadsExecutor(final String name, final int maxThreads) {
        ThreadsExecutorService result = EXECUTORS.get(name);
        if (result == null) {
            result = createThreadsExecutor(name, maxThreads);
        }
        return result;
    }
    
    private synchronized ThreadsExecutorService createThreadsExecutor(final String name, final int nbMaxThreads) {
        final ThreadsExecutorService result = new ThreadsExecutorService(name, nbMaxThreads, false);
        EXECUTORS.put(name, result);
        return result;
    }
    
    public HttpConnector getHttpConnector(final String name, final int maxConnections, final int timeout, final int ttl,
                                          final int maxPerRoute, final int socketTimeout) {
        HttpConnector result = HTTP_CONNECTORS.get(name);
        if (result == null) {
            result = new HttpConnector(timeout, ttl, maxConnections, maxPerRoute, socketTimeout);
            HTTP_CONNECTORS.put(name, result);
        }
        return result;
    }
    
    public HttpConnector getHttpConnector(final String name, final int maxConnections, final int timeout, final int ttl,
                                          final int maxPerRoute) {
        HttpConnector result = HTTP_CONNECTORS.get(name);
        if (result == null) {
            result = new HttpConnector(timeout, ttl, maxConnections, maxPerRoute, timeout);
            HTTP_CONNECTORS.put(name, result);
        }
        return result;
    }
    
    // =========================================================================
    // CONTEXT TOOLS
    // =========================================================================
    public Map<String, String> getInitialProperties() {
        final Map<String, String> result = new HashMap<>();
        
        final List<PropertiesProducerSpi> producers = SPI_LOADER.loadSpiService(PropertiesProducerSpi.class);
        
        if (producers != null) {
            producers.sort(this::comparePropertiesProducer);
            producers.stream().map(PropertiesProducerSpi::produce).forEach(result::putAll);
        }
        
        return result;
    }
    
    private int comparePropertiesProducer(final PropertiesProducerSpi ref, final PropertiesProducerSpi value) {
        final Priority refPriority = ref == null ? null : ref.getClass().getAnnotation(Priority.class);
        final Priority valuePriority = value == null ? null : value.getClass().getAnnotation(Priority.class);
        
        final int refWeigth = refPriority == null ? 0 : refPriority.value();
        final int valueWeigth = valuePriority == null ? 0 : valuePriority.value();
        
        return new Integer(refWeigth).compareTo(valueWeigth);
        
    }
    
    // =========================================================================
    // BOOTSTRAP
    // =========================================================================
    @Override
    public void shutdown(final T ctx) {
        HTTP_CONNECTORS.entrySet().forEach(entry -> entry.getValue().close());
        EXECUTORS.entrySet().forEach(entry -> entry.getValue().shutdown());
    }
    
    // =========================================================================
    // REGISTER
    // =========================================================================
    public synchronized void registerListeners(final List<EngineListener> listeners) {
        if (listeners != null) {
            ENGINE_LISTENERS.addAll(listeners);
        }
    }
    
    public synchronized void registerNamedComponents(final List<? extends NamedComponent> namedComponents) {
        for (final NamedComponent item : Optional.ofNullable(namedComponents).orElse(new ArrayList<>())) {
            if (isNamedComponentAlreadyRegisted(item)) {
                //@formatter:off
                Loggers.INIT.error("NamedComponent already register with name :{}, please check your configuration",item.getName());
                //@formatter:on
            }
            else {
                NAMED_COMPONENTS.add(item);
            }
        }
    }
    
    public synchronized void registerFrontProperties(final PluginConfiguration config) {
        if (config.getFrontProperties().isPresent()) {
            final Map<String, String> frontProperties = new HashMap<>();
            for (final PropertyModel property : config.getFrontProperties().orElseGet(Collections::emptyList)) {
                frontProperties.put(property.getKey(), property.getValue());
            }
            MessagesServices.registerConfig(frontProperties);
        }
    }
    
    public synchronized void registerPropertiesLabels(final ManifestInfo manifest) {
        
    }
    
    public synchronized void registerPropertiesLabels(final Map<String, Map<String, String>> labels) {
        if (labels != null) {
            MessagesServices.register(labels);
        }
    }
    
    public synchronized void registerFrontProperties(final Map<String, String> frontProperties) {
        MessagesServices.registerFrontProperties(frontProperties);
    }
    
    public synchronized void registerCronExpression(final String cronExpression) {
        if (cronExpression != null) {
            CRON_EXPRESSIONS.add(cronExpression);
        }
    }
    
    // =========================================================================
    // TOOLS
    // =========================================================================
    private boolean isNamedComponentAlreadyRegisted(final NamedComponent item) {
        boolean result = false;
        for (final NamedComponent saved : NAMED_COMPONENTS) {
            result = saved.getName() == null ? item.getName() == null : saved.getName().equals(item.getName());
            if (result) {
                break;
            }
        }
        return result;
    }
    
    // =========================================================================
    // GETTERS
    // =========================================================================
    public List<Provider> getProviders() {
        final List<Provider> result = new ArrayList<>();
        for (final NamedComponent item : NAMED_COMPONENTS) {
            if (item instanceof Provider) {
                result.add((Provider) item);
            }
        }
        return result;
    }
    
    public Provider getProvider(final String providerName) {
        return getNamedComponent(providerName);
    }
    
    public Handler getHandler(final String name) {
        return getNamedComponent(name);
    }
    
    public <N extends NamedComponent> N getNamedComponent(final String name) {
        N result = null;
        
        for (final NamedComponent item : NAMED_COMPONENTS) {
            if (item.getName().equals(name)) {
                result = (N) item;
            }
        }
        return result;
    }
    
}
