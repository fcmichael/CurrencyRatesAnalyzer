package app.i18n;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MessagesReader {

    private static MessagesReader instance = new MessagesReader();
    private Map<ApplicationLanguage, Properties> languagePropertiesMap;

    public static MessagesReader getInstance() {
        return instance;
    }

    private MessagesReader() {
        this.languagePropertiesMap = initializePropertiesFromXML();
    }

    private Map<ApplicationLanguage, Properties> initializePropertiesFromXML() {
        Map<ApplicationLanguage, Properties> map = new HashMap<>();

        for (ApplicationLanguage l : ApplicationLanguage.values()) {
            InputStream inputStream;
            URL resourceURL = Thread.currentThread().getContextClassLoader().getResource("");
            final Properties messageProperties = new Properties();

            if (resourceURL != null) {
                String rootPath = resourceURL.getPath();
                String appConfigPath = rootPath + l.getMessagesPath();

                try {
                    inputStream = new FileInputStream(appConfigPath);
                    messageProperties.loadFromXML(inputStream);
                } catch (FileNotFoundException e) {
                    Logger.getRootLogger().warn("Message file not found", e);
                } catch (IOException e) {
                    Logger.getRootLogger().warn("Error while message file proceeding", e);
                }
            } else {
                Logger.getRootLogger().warn("Cannot get resource URL");
            }
            map.put(l, messageProperties);
        }

        return map;
    }

    public String getMessage(String locale, String key) {
        Properties properties = languagePropertiesMap.get(ApplicationLanguage.EN);

        try {
            properties = languagePropertiesMap.get(ApplicationLanguage.valueOf(locale));
        } catch (IllegalArgumentException e) {
            Logger.getRootLogger().warn("No ApplicationLanguage called: " + locale, e);
        }

        return properties.getProperty(key);
    }
}
