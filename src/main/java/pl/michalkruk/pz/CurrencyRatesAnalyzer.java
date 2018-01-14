package pl.michalkruk.pz;

import org.apache.log4j.Logger;
import pl.michalkruk.pz.gui.CurrencyRatesAnalyzerFrame;
import pl.michalkruk.pz.gui.tray.CurrencyRatesAnalyzerTrayIcon;

import java.awt.*;

public class CurrencyRatesAnalyzer {

    public static void main(String[] args) {
        Logger.getRootLogger().info("Application start");

        if (!SystemTray.isSupported()) {
            Logger.getRootLogger().warn("SystemTray is not supported");
            new CurrencyRatesAnalyzerFrame(false);
        } else {
            new CurrencyRatesAnalyzerTrayIcon(
                    new CurrencyRatesAnalyzerFrame(true),
                    SystemTray.getSystemTray());
        }
    }
}
