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
package org.inugami.api.providers.task;

import java.util.List;

import org.inugami.api.alertings.AlertingResult;
import org.inugami.api.models.data.JsonObject;
import org.inugami.api.models.events.GenericEvent;

/**
 * EventCompositeResult.
 *
 * @author pguillerm
 * @since 30 sept. 2017
 */
public class EventCompositeResult extends ProviderFutureResult {
    
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -1877469436997897387L;
    
    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    public EventCompositeResult(final String message, final Exception exception, final JsonObject data,
                                final GenericEvent event, final String channel, final String scheduler,
                                final List<AlertingResult> alerts) {
        super(message, exception, data, event, channel, scheduler, alerts);
    }
    
    // =========================================================================
    // OVERRIDES
    // =========================================================================
    @Override
    protected String initFieldData() {
        return "values";
    }
    
}
