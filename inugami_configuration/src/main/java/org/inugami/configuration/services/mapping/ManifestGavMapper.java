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
package org.inugami.configuration.services.mapping;

import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.inugami.api.mapping.Mapper;
import org.inugami.api.models.Gav;

/**
 * ManifestMapper
 * 
 * @author patrickguillerm
 * @since 26 nov. 2017
 */
public class ManifestGavMapper implements Mapper<Gav, Manifest> {
    
    // =========================================================================
    // METHODS
    // =========================================================================
    @Override
    public Gav mapping(final Manifest data) {
        Gav result = null;
        final Attributes mainData = data.getMainAttributes();
        
        final String artifactId = mainData.getValue(new Attributes.Name("Implementation-Module"));
        final String groupId = mainData.getValue(new Attributes.Name("Implementation-Group"));
        final String version = mainData.getValue(new Attributes.Name("Implementation-Version"));
        final String artifactType = mainData.getValue(new Attributes.Name("ArtifactType"));
        
        if ((artifactId != null) && (groupId != null) && (version != null) && "inugami_plugin".equals(artifactType)) {
            result = new Gav(groupId, artifactId, version, null);
        }
        
        return result;
    }
    
}
