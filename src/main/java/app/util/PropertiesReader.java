package app.util;

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
                // TODO: log
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("Properties file not found");
            // TODO: log
        }
    }

    public static String getProperty(String key, String defaultValue){
        return propertiesReader.appProperties.getProperty(key, defaultValue);
    }
}
