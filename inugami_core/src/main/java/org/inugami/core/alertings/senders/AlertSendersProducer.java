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
package org.inugami.core.alertings.senders;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.inject.Named;

import org.inugami.api.alertings.AlertsSender;
import org.inugami.core.cdi.extensions.producers.BeansProducer;

/**
 * AlertSendersProducer
 * 
 * @author patrickguillerm
 * @since 20 janv. 2018
 */
@Named
public class AlertSendersProducer implements BeansProducer<AlertsSender> {
    
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    @Inject
    private BeanManager beanManager;
    
    // =========================================================================
    // METHODS
    // =========================================================================
    @Produces
    @ApplicationScoped
    public List<AlertsSender> produces() {
        return produceBeansInstances();
    }
    
    @Override
    public BeanManager getBeanManager() {
        return beanManager;
    }
    
    @Override
    public Class<? extends AlertsSender> getType() {
        return AlertsSender.class;
    }
}
