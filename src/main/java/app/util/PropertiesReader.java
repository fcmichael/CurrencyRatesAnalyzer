package app.util;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class PropertiesReader {

	private final static PropertiesReader propertiesReader = new PropertiesReader();
	private final String APPLICATION_PROPERTIES_FILE = "application.properties";
	private final Properties appProperties = new Properties();

	private PropertiesReader() {
		InputStream inputStream;
		URL resourceURL = Thread.currentThread().getContextClassLoader().getResource("");

		if (resourceURL != null) {
			String rootPath = resourceURL.getPath();
			String appConfigPath = rootPath + APPLICATION_PROPERTIES_FILE;

			try {
				inputStream = new FileInputStream(appConfigPath);
				appProperties.load(inputStream);
			} catch (FileNotFoundException e) {
				Logger.getRootLogger().warn("Properties file not found", e);
			} catch (IOException e) {
				Logger.getRootLogger().warn("Exception while properties file proceeding", e);
			}
		} else {
			Logger.getRootLogger().warn("Cannot get resource URL");
		}
	}

	public static String getProperty(String key, String defaultValue) {
		return propertiesReader.appProperties.getProperty(key, defaultValue);
	}
}
