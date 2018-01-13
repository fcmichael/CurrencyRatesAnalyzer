package pl.michalkruk.pz.util;

import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

	private final static PropertiesReader propertiesReader = new PropertiesReader();
	private final String APPLICATION_PROPERTIES_FILE = "application.properties";
	private final Properties appProperties = new Properties();

	private PropertiesReader() {
		InputStream inputStream = null;
			try {
				inputStream = this.getClass().getClassLoader().getResourceAsStream(APPLICATION_PROPERTIES_FILE);
				appProperties.load(inputStream);
			} catch (FileNotFoundException e) {
				Logger.getRootLogger().warn("Properties file not found", e);
			} catch (IOException e) {
				Logger.getRootLogger().warn("Exception while properties file proceeding", e);
			} finally {
				if(inputStream != null){
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        Logger.getRootLogger().warn("Exception while closing reader", e);
                    }
                }
			}
	}

	public static String getProperty(String key, String defaultValue) {
		return propertiesReader.appProperties.getProperty(key, defaultValue);
	}
}
