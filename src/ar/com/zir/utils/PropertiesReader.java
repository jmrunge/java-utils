/*
    Copyright 2012 Juan Martín Runge
    
    jmrunge@gmail.com
    http://www.zirsi.com.ar
    
    This file is part of ZirUtils.

    ZirUtils is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    ZirUtils is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with ZirUtils.  If not, see <http://www.gnu.org/licenses/>.
*/
package ar.com.zir.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Singleton utility class for reading properties files
 * 
 * @author jmrunge
 * @version 1.00
 */
public class PropertiesReader {
    private String propsFileName;
    private Properties props = null;
    
    /**
     * Singleton constructor
     */
    private PropertiesReader() {
    }
    
    /**
     * Method that returns the singleton instance of the class
     * 
     * @return the singleton instance of the class
     */
    public static PropertiesReader getInstance() {
        return PropertiesReaderHolder.INSTANCE;
    }
    
    /**
     * Static class that holds the singleton instance of the class
     */
    private static class PropertiesReaderHolder {
        private static final PropertiesReader INSTANCE = new PropertiesReader();
    }
    
    /**
     * Method that inits the class, should be invoked prior to any other method
     * 
     * @param propsFile the path where the properties file is
     * @throws FileNotFoundException inherited from readFile()
     * @throws IOException inherited from readFile()
     * @see readFile()
     */
    public void init(String propsFile) throws FileNotFoundException, IOException {
        propsFileName = propsFile;
        readFile();
    }
    
    /**
     * Method that reads the properties fila and loads the properties into 
     * the internal properties object
     * 
     * @throws FileNotFoundException inherited from FileInputStream(String)
     * @throws IOException inherited from Properties.load(InputStream)
     * @see FileInputStream#FileInputStream(java.lang.String)
     * @see Properties#load(java.io.InputStream) 
     */
    private void readFile() throws FileNotFoundException, IOException {
        InputStream is = new FileInputStream(propsFileName);
        props = new Properties();
        props.load(is);
    }
    
    /**
     * Method that returns the property value for this key
     * 
     * @param key the key
     * @return the property value
     */
    public String getProperty(String key) {
        return props.getProperty(key);
    }    
    
    /**
     * Method that returns all properties read from the properties file
     * 
     * @return a reference to the internal properties object
     */
    public Properties getProperties() {
        return props;
    }
}
