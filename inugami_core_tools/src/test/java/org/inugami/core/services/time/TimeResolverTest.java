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
package org.inugami.core.services.time;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Test;

/**
 * TimeResolverTest
 * 
 * @author patrick_guillerm
 * @since 11 oct. 2016
 */
public class TimeResolverTest {
    
    // =========================================================================
    // METHODS
    // =========================================================================
    @Test
    public void testResolveShiftTime() throws Exception {
        final TimeResolver service = new TimeResolver();
        
        assertEquals(10, service.resolveShiftTime("-10min"));
        assertEquals(75, service.resolveShiftTime("-75min"));
        assertEquals(5, service.resolveShiftTime("-5s"));
        assertEquals(3, service.resolveShiftTime("-3h"));
        assertEquals(7, service.resolveShiftTime("-7d"));
        assertEquals(2, service.resolveShiftTime("-2Y"));
    }
    
    @Test
    public void testShiftTime() throws Exception {
        final TimeResolver service = new TimeResolver();
        
        assertEquals(Calendar.YEAR, service.resolveCalendarField("-10y"));
        assertEquals(Calendar.YEAR, service.resolveCalendarField("-5Y"));
        assertEquals(Calendar.MONTH, service.resolveCalendarField("-12M"));
        assertEquals(Calendar.WEEK_OF_YEAR, service.resolveCalendarField("-3w"));
        assertEquals(Calendar.WEEK_OF_YEAR, service.resolveCalendarField("-4W"));
        assertEquals(Calendar.DAY_OF_MONTH, service.resolveCalendarField("-7D"));
        assertEquals(Calendar.DAY_OF_MONTH, service.resolveCalendarField("-14d"));
        assertEquals(Calendar.HOUR_OF_DAY, service.resolveCalendarField("-22H"));
        assertEquals(Calendar.HOUR_OF_DAY, service.resolveCalendarField("-4h"));
        assertEquals(Calendar.MINUTE, service.resolveCalendarField("-30min"));
        assertEquals(Calendar.MINUTE, service.resolveCalendarField("-10m"));
        assertEquals(Calendar.SECOND, service.resolveCalendarField("-15s"));
        assertEquals(Calendar.MILLISECOND, service.resolveCalendarField("-200S"));
        
    }
}
