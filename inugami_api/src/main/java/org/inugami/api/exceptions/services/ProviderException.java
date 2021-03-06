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
package org.inugami.api.exceptions.services;

import org.inugami.api.exceptions.MessagesFormatter;
import org.inugami.api.exceptions.TechnicalException;

/**
 * ProcessorException
 * 
 * @author patrick_guillerm
 * @since 6 oct. 2016
 */
public class ProviderException extends TechnicalException {
    
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -4976777345663055137L;
    
    // =========================================================================
    // PROTECTED CONSTRUCTORS
    // =========================================================================
    protected ProviderException(final int code, final String message, final Throwable cause) {
        super(code, message, cause);
    }
    
    // =========================================================================
    // PUBLIC CONSTRUCTORS
    // =========================================================================
    
    public ProviderException() {
        this(ERR_CODE, null, null);
    }
    
    public ProviderException(final String message, final Throwable cause) {
        this(ERR_CODE, message, cause);
    }
    
    public ProviderException(final String message, final Object... values) {
        this(ERR_CODE, MessagesFormatter.format(message, values), null);
    }
    
    public ProviderException(final Throwable cause, final String message, final Object... values) {
        this(ERR_CODE, MessagesFormatter.format(message, values), cause);
    }
    
    public ProviderException(final String message) {
        this(ERR_CODE, message, null);
    }
    
    public ProviderException(final Throwable cause) {
        this(ERR_CODE, null, cause);
    }
}
