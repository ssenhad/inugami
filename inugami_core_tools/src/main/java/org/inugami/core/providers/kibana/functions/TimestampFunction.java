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
package org.inugami.core.providers.kibana.functions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.inugami.configuration.services.functions.FunctionData;
import org.inugami.configuration.services.functions.ProviderAttributFunction;
import org.inugami.configuration.services.functions.ProviderAttributFunctionException;

/**
 * TimestampFunction
 * 
 * @author patrick_guillerm
 * @since 7 nov. 2016
 */
public class TimestampFunction implements ProviderAttributFunction {
    
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    private final static String NAME                 = "timestamp";
    
    private final static String NOW                  = "now";
    
    private final static String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    
    // =========================================================================
    // METHODS
    // =========================================================================
    @Override
    public String getName() {
        return NAME;
    }
    
    @Override
    public String apply(final FunctionData data) {
        ProviderAttributFunctionException.assertParams(1, 2, data);
        
        Date date = null;
        if (NOW.equals(data.getParameters()[0].trim())) {
            date = new Date();
        }
        else {
            final String pattern = extractPattern(data.getParameters());
            
            try {
                date = new SimpleDateFormat(pattern).parse(data.getParameters()[0]);
            }
            catch (final ParseException e) {
                throw new ProviderAttributFunctionException(e.getMessage(), e);
            }
        }
        return String.valueOf(date.getTime());
    }
    
    private String extractPattern(final String[] data) {
        String result = DEFAULT_DATE_PATTERN;
        if ((data.length == 2) && (data[1] != null)) {
            result = data[1];
        }
        return result;
    }
    
}
