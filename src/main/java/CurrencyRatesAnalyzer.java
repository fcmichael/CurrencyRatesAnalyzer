import gui.CurrencyRatesAnalyzerFrame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class CurrencyRatesAnalyzer {

    private static final String APPLICATION_PROPERTIES_FILE = "application.properties";

    public static void main(String[] args) {
        Properties appProperties = getProperties();
        int frameWidth = Integer.parseInt(appProperties.getProperty("frame.size.width", "800"));
        int frameHeight = Integer.parseInt(appProperties.getProperty("frame.size.height", "600"));
        int frameX = Integer.parseInt(appProperties.getProperty("frame.location.x", "0"));
        int frameY = Integer.parseInt(appProperties.getProperty("frame.location.y", "0"));

        new CurrencyRatesAnalyzerFrame(frameWidth, frameHeight, frameX, frameY);
    }

    private static Properties getProperties() {
        InputStream inputStream;
        Properties appProperties = new Properties();
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
        return appProperties;
    }
}
