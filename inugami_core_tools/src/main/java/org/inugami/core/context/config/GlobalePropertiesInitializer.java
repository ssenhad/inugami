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
package org.inugami.core.context.config;

import java.util.Map;

import javax.annotation.Priority;

import org.inugami.api.spi.PropertiesProducerSpi;
import org.inugami.core.context.handlers.hesperides.HesperidesHandler;
import org.inugami.core.processors.GraphiteBucketProcessor;
import org.inugami.core.providers.cache.PurgeCacheProvider;
import org.inugami.core.providers.csv.CsvProvider;
import org.inugami.core.providers.els.ElasticSearchWriter;
import org.inugami.core.providers.files.FilesJsonProvider;
import org.inugami.core.providers.gitlab.GitlabProvider;
import org.inugami.core.providers.graphite.GraphiteProvider;
import org.inugami.core.providers.jira.JiraProvider;
import org.inugami.core.providers.kibana.KibanaProvider;
import org.inugami.core.providers.mock.MockJsonProvider;
import org.inugami.core.providers.rest.RestProvider;

/**
 * GlobalPropertiesInitializer
 * 
 * @author patrick_guillerm
 * @since 9 nov. 2017
 */
@Priority(0)
public class GlobalePropertiesInitializer implements PropertiesProducerSpi {
    
    @Override
    public Map<String, String> produce() {
        //@formatter:off
        return producerFromClasses(
                   //providers
                   PurgeCacheProvider.class,
                   FilesJsonProvider.class,
                   KibanaProvider.class,
                   MockJsonProvider.class,
                   RestProvider.class,
                   GraphiteProvider.class,
                   JiraProvider.class,
                   GitlabProvider.class,
                   CsvProvider.class,
                   //handlers
                   HesperidesHandler.class,
                   
                   //processors
                   GraphiteBucketProcessor.class,

                   // writer 
                   ElasticSearchWriter.class
        );
        //@formatter:on
    }
    
}
