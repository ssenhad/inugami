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
package org.inugami.configuration.exceptions;

import org.inugami.api.exceptions.TechnicalException;

/**
 * ConfigurationException
 * 
 * @author patrick_guillerm
 * @since 15 déc. 2017
 */
public class ConfigurationException extends TechnicalException {
    
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    private static final long serialVersionUID = 6886074066050191742L;
    
    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    public ConfigurationException() {
        super();
    }
    
    public ConfigurationException(final String message, final Object... values) {
        super(message, values);
    }
    
    public ConfigurationException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    public ConfigurationException(final String message) {
        super(message);
    }
    
    public ConfigurationException(final Throwable cause, final String message, final Object... values) {
        super(cause, message, values);
    }
    
    public ConfigurationException(final Throwable cause) {
        super(cause);
    }
    
}
