package pl.michalkruk.pz.i18n;

import org.apache.log4j.Logger;
import pl.michalkruk.pz.util.PropertiesReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Properties;

public class MessagesReader extends Observable {

    private static final MessagesReader instance = new MessagesReader();
    private final Map<ApplicationLanguage, Properties> languagePropertiesMap;
    private ApplicationLanguage currentLanguage;

    private MessagesReader() {
        this.currentLanguage = ApplicationLanguage.valueOf(PropertiesReader.getProperty("application.default.language", "PL"));
        this.languagePropertiesMap = initializePropertiesFromXML();
    }

    public static MessagesReader getInstance() {
        return instance;
    }

    private Map<ApplicationLanguage, Properties> initializePropertiesFromXML() {
        Map<ApplicationLanguage, Properties> map = new HashMap<>();

        for (ApplicationLanguage l : ApplicationLanguage.values()) {
            InputStream inputStream = null;
            final Properties messageProperties = new Properties();

            try {
                inputStream = this.getClass().getClassLoader().getResourceAsStream(l.getMessagesPath());
                messageProperties.loadFromXML(inputStream);
            } catch (FileNotFoundException e) {
                Logger.getRootLogger().warn("Message file not found", e);
            } catch (IOException e) {
                Logger.getRootLogger().warn("Exception while message file proceeding", e);
            } finally {
                if(inputStream != null){
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        Logger.getRootLogger().warn("Exception while closing reader", e);
                    }
                }
            }
            map.put(l, messageProperties);
        }

        return map;
    }

    public ApplicationLanguage getCurrentLanguage() {
        return currentLanguage;
    }

    public String getMessage(String key) {
        return languagePropertiesMap.get(currentLanguage).getProperty(key);
    }

    public void changeLanguage(ApplicationLanguage applicationLanguage) {
        this.currentLanguage = applicationLanguage;
        setChanged();
        notifyObservers();
    }
}
