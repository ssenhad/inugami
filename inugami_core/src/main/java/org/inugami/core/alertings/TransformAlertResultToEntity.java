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
package org.inugami.core.alertings;

import org.inugami.api.alertings.AlertingResult;
import org.inugami.api.functionnals.ApplyIfNotNull;
import org.inugami.api.mapping.Mapper;

/**
 * TransformAlertResultToEntity
 * 
 * @author patrickguillerm
 * @since 20 janv. 2018
 */
public class TransformAlertResultToEntity implements Mapper<AlertEntity, AlertingResult>, ApplyIfNotNull {
    
    // =========================================================================
    // MAPPING AlertEntity -> AlertingResult
    // =========================================================================
    @Override
    public AlertEntity mapping(final AlertingResult data) {
        return data == null ? null : processMapping(data);
    }
    
    private AlertEntity processMapping(final AlertingResult data) {
        final AlertEntity result = new AlertEntity();
        //@formatter:off
        applyIfNotNull(data.getAlerteName(), result::setAlerteName);
        applyIfNotNull(data.getChannel(),    result::setChannel);
        applyIfNotNull(data.getLevel(),      result::setLevel);
        applyIfNotNull(data.getMessage(),    result::setLabel);
        applyIfNotNull(data.getSubLabel(),   result::setSubLabel);
        applyIfNotNull(data.getUrl(),        result::setUrl);
        applyIfNotNull(data.getData(),       (value)-> result.setData(cleanData(data)));
        //@formatter:on        
        result.setCreated(data.getCreated());
        result.setDuration(data.getDuration());
        
        return result;
    }
    
    private String cleanData(final AlertingResult data) {
        return data.convertData();
    }
    
}
