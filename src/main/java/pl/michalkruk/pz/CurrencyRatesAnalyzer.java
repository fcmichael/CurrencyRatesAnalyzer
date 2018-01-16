package pl.michalkruk.pz;

import org.apache.log4j.Logger;
import pl.michalkruk.pz.gui.CurrencyRatesAnalyzerFrame;
import pl.michalkruk.pz.gui.tray.CurrencyRatesAnalyzerTrayIcon;
import pl.michalkruk.pz.nbp.analyse.AnalyzeThread;
import pl.michalkruk.pz.nbp.notification.NotificationExecutor;

import java.awt.*;

public class CurrencyRatesAnalyzer {

    public static void main(String[] args) {
        Logger.getRootLogger().info("Application start");

        if (!SystemTray.isSupported()) {
            Logger.getRootLogger().warn("SystemTray is not supported");
            new CurrencyRatesAnalyzerFrame(false);
        } else {
            CurrencyRatesAnalyzerFrame frame = new CurrencyRatesAnalyzerFrame(true);
            SystemTray tray = SystemTray.getSystemTray();
            CurrencyRatesAnalyzerTrayIcon trayIcon = new CurrencyRatesAnalyzerTrayIcon(frame, tray);
            NotificationExecutor.analyze(new AnalyzeThread(trayIcon));
        }
    }
}
