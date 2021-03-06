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
package org.inugami.monitoring.api.senders;

import java.util.List;

import org.inugami.api.processors.ConfigHandler;
import org.inugami.api.spi.NamedSpi;
import org.inugami.monitoring.api.data.GenericMonitoringModel;

/**
 * MonitoringProvider
 * 
 * @author patrick_guillerm
 * @since 27 déc. 2018
 */
public interface MonitoringSender extends NamedSpi {
    
    MonitoringSender buildInstance(ConfigHandler<String, String> configuration);
    
    void process(List<GenericMonitoringModel> data) throws MonitoringSenderException;
    
    default void shutdown() {
    }
}
