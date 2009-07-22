/*
 * Copyright 2006-2007,  Unitils.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.dbmaintain.config;

import static org.apache.commons.io.IOUtils.closeQuietly;
import org.dbmaintain.util.DbMaintainException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;


/**
 * Utility that loads the configuration of DbMaintain.
 *
 * @author Tim Ducheyne
 * @author Filip Neven
 */
public class DbMaintainConfigurationLoader {

    /**
     * Name of the fixed configuration file that contains all defaults
     */
    public static final String DEFAULT_PROPERTIES_FILE_NAME = "dbmaintain-default.properties";

    /**
     * Property in the defaults configuration file that contains the name of the custom configuration file
     */
    public static final String PROPKEY_CUSTOM_CONFIGURATION = "dbmaintain.configuration.customFileName";

    
    /**
     * Creates and loads all configuration settings.
     *
     * @return the settings, not null
     */
    public Properties loadConfiguration() {
        return loadConfiguration(null);
    }
    

    /**
     * Creates and loads all configuration settings.
     * 
     * @param customConfiguration URL that points to the custom configuration, may be null if there is no custom config 
     *
     * @return the settings, not null
     */
    public Properties loadConfiguration(URL customConfiguration) {
    	Properties properties = new Properties();
    	
    	// Load the default properties file, that is distributed with DbMaintain (dbmaintain-default.properties)
    	properties.putAll(loadDefaultConfiguration());
    	
    	if (customConfiguration != null) {
    		properties.putAll(loadPropertiesFromURL(customConfiguration));
    	}
        
        return properties;
    }


    /**
     * Creates and loads the default configuration settings from the {@link #DEFAULT_PROPERTIES_FILE_NAME} file.
     *
     * @return the defaults, not null
     * @throws RuntimeException if the file cannot be found or loaded
     */
    public Properties loadDefaultConfiguration() {
        Properties defaultConfiguration = loadPropertiesFromClasspath(DEFAULT_PROPERTIES_FILE_NAME);
        if (defaultConfiguration == null) {
            throw new DbMaintainException("Configuration file: " + DEFAULT_PROPERTIES_FILE_NAME + " not found in classpath.");
        }
        return defaultConfiguration;
    }

    
	protected Properties loadPropertiesFromClasspath(String propertiesFileName) {
		InputStream inputStream = null;
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFileName);
            if (inputStream == null) {
                return null;
            }
            return loadPropertiesFromStream(inputStream);

        } catch (IOException e) {
            throw new DbMaintainException("Unable to load configuration file: " + propertiesFileName, e);
        } finally {
            closeQuietly(inputStream);
        }
	}


    protected Properties loadPropertiesFromURL(URL propertiesFileUrl) {
	    if (propertiesFileUrl == null) {
            return null;
        }
        InputStream urlStream = null;
	    try {
	        urlStream = propertiesFileUrl.openStream();
	        return loadPropertiesFromStream(urlStream);
	    } catch (IOException e) {
	        throw new DbMaintainException("Unable to load configuration file", e);
	    } finally {
	        closeQuietly(urlStream);
	    }
	}

    
    protected Properties loadPropertiesFromStream(InputStream inputStream) throws IOException {
        Properties properties = new Properties();
        properties.load(inputStream);
        return properties;
    }
	
}
