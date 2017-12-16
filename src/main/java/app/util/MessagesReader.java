package app.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class MessagesReader {

    private static MessagesReader instance = new MessagesReader();
    private final String MESSAGES_PATH_PL = "/messages/messages_pl.xml";
    private final String MESSAGES_PATH_EN = "/messages/messages_en.xml";
    private Properties propertiesPL;
    private Properties propertiesEN;

    public static MessagesReader getInstance() {
        return instance;
    }

    private MessagesReader() {
        initializePropertiesPL();
        initializePropertiesEN();
    }

    private void initializePropertiesPL() {
        this.propertiesPL = initializePropertiesFromXML(MESSAGES_PATH_PL);
    }

    private void initializePropertiesEN() {
        this.propertiesEN = initializePropertiesFromXML(MESSAGES_PATH_EN);
    }

    private Properties initializePropertiesFromXML(String messagePath) {
        InputStream inputStream;
        URL resourceURL = Thread.currentThread().getContextClassLoader().getResource("");
        final Properties messageProperties = new Properties();

        if (resourceURL != null) {
            String rootPath = resourceURL.getPath();
            String appConfigPath = rootPath + messagePath;

            try {
                inputStream = new FileInputStream(appConfigPath);
                messageProperties.loadFromXML(inputStream);
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
        return messageProperties;
    }

    public String getMessage(String locale, String key) {
        if ("PL".equalsIgnoreCase(locale)) {
            return propertiesPL.getProperty(key);
        }

        return propertiesEN.getProperty(key);
    }
}
