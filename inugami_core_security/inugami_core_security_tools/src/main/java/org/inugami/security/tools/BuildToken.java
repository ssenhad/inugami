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
package org.inugami.security.tools;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.inugami.api.constants.JvmKeyValues;
import org.inugami.api.exceptions.Asserts;
import org.inugami.commons.cli.CliHelper;
import org.inugami.commons.cli.DefaultOptions;
import org.inugami.commons.cli.MainCliAction;
import org.inugami.commons.security.TokenBuilder;
import org.inugami.commons.writer.RapportWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * EncodeAes
 * 
 * @author patrick_guillerm
 * @since 18 déc. 2017
 */
public class BuildToken implements MainCliAction {
    
    // =========================================================================
    // METHODS
    // =========================================================================
    @Override
    public void process(DefaultOptions defaultOpts, String[] args, RapportWriter arg2) throws Exception {
        final Options options = new Options();
        options.addOption(new Option("k", "key", true, "Secret key to use"));
        options.addOption(new Option("i", "input", true, "login for build token"));
        
        new ValueEncoder(options, defaultOpts, args).encode(this::encode, this::showHelp);
    }
    
    private void encode(CommandLine cmd) {
        final String secretKey = cmd.getOptionValue('k');
        if (secretKey != null && !secretKey.trim().isEmpty()) {
            System.setProperty(JvmKeyValues.SECURITY_AES_SECRET_KEY.getKey(), secretKey);
        }
        
        final String value = cmd.getOptionValue('i');
        Asserts.notEmpty("value is mandatory!", value);
        
        System.out.println(new TokenBuilder().buildTechnicalToken(value));
    }
    
    // =========================================================================
    // OVERRIDES
    // =========================================================================
    private void showHelp() {
        final Logger log = LoggerFactory.getLogger("ROOT");
        CliHelper.drawDeco("HELP", "*", log);
        log.info("This main programm allow to encode value in AES encryption.");
        log.info("-k    -key    : for specify secret token");
        log.info("-i    -input  : value to encode");
        log.info("-h    -help   : for show help");
    }
}
