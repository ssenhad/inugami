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
package org.inugami.security.ldap.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchResult;

import org.inugami.api.exceptions.Asserts;
import org.inugami.api.loggers.Loggers;
import org.inugami.commons.security.EncryptionUtils;
import org.picketlink.idm.model.basic.User;

/**
 * DefaultLdapMapper
 * 
 * @author patrick_guillerm
 * @since 14 déc. 2017
 */
public class DefaultLdapMapper implements LdapMapper {
    
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    private static final EncryptionUtils ENCRYPTION = new EncryptionUtils();
    
    // =========================================================================
    // METHODS
    // =========================================================================
    @Override
    public User mapping(final SearchResult data) {
        final User result = new User();
        final Attributes attributes = data.getAttributes();
        
        result.setEmail(getValueStr("mail", attributes));
        result.setLastName(getValueStr("sn", attributes));
        
        final String fullName = getValueStr("name", attributes);
        Asserts.notNull("full name mustn't be null!", fullName);
        result.setFirstName(fullName.replaceAll(result.getLastName(), ""));
        final String whencreated = getValueStr("whencreated", attributes);
        if (whencreated != null) {
            result.setCreatedDate(parseDate(whencreated));
        }
        
        final String guid = getValueStr("objectguid", attributes);
        result.setId(guid == null ? null : ENCRYPTION.encodeSha1(guid));
        return result;
    }
    
    private Date parseDate(final String value) {
        Date result = null;
        String data = null;
        if (value.contains(".")) {
            data = value.substring(0, value.lastIndexOf('.'));
        }
        else {
            data = value;
        }
        
        try {
            result = new SimpleDateFormat("YYYYMMddHHmmss").parse(data);
        }
        catch (final ParseException e) {
            Loggers.DEBUG.error(e.getMessage(), e);
        }
        return result;
    }
    
    // =========================================================================
    // TOOLS
    // =========================================================================
    private String getValueStr(final String key, final Attributes attributes) {
        final Attribute attribute = attributes.get(key);
        String result = null;
        try {
            result = String.valueOf(attribute.get());
        }
        catch (final NamingException e) {
            if (Loggers.SECURITY.isDebugEnabled()) {
                Loggers.SECURITY.debug(e.getMessage(), e);
            }
        }
        return result;
    }
}
