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
package org.inugami.configuration.tools;

import org.inugami.api.exceptions.services.ProcessorException;
import org.inugami.api.models.events.GenericEvent;
import org.inugami.api.processors.Processor;
import org.inugami.api.providers.task.ProviderFutureResult;

/**
 * TestProcessor
 * 
 * @author patrick_guillerm
 * @since 20 janv. 2017
 */
public class TestProcessor implements Processor {
    
    // =========================================================================
    // METHODS
    // =========================================================================
    @Override
    public ProviderFutureResult process(final GenericEvent eventName,
                                        final ProviderFutureResult data) throws ProcessorException {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
    
}
