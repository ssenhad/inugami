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
package org.inugami.commons.spi;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Comparator;

import org.inugami.api.spi.SpiPriority;
import org.inugami.commons.tools.AnnotationTools;

/**
 * PriorityComparator
 * 
 * @author patrick_guillerm
 * @since 28 déc. 2018
 */
public class PriorityComparator<T> implements Comparator<T> {
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    private static final String JAVAX_PRIORITY = "javax.annotation.Priority";
    
    // =========================================================================
    // METHODS
    // =========================================================================
    @Override
    public int compare(final T ref, final T value) {
        final int refPriority = loadPriority(ref);
        final int valuePriority = loadPriority(ref);
        return new Integer(refPriority).compareTo(valuePriority);
    }
    
    private int loadPriority(final T object) {
        
        Annotation[] annotations = null;
        if (object != null) {
            annotations = object.getClass().getDeclaredAnnotations();
        }
        
        final Annotation priorityAnnotation = AnnotationTools.searchAnnotation(annotations, JAVAX_PRIORITY,
                                                                               SpiPriority.class.getName());
        final Method getValue = AnnotationTools.searchMethod(priorityAnnotation, "value");
        final Integer result = AnnotationTools.invoke(getValue, priorityAnnotation);
        return result == null ? 0 : result;
    }
    
}
