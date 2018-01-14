package pl.michalkruk.pz.gui.tray.action;

import org.apache.log4j.Logger;
import pl.michalkruk.pz.db.DbFacade;
import pl.michalkruk.pz.gui.tray.CurrencyRatesAnalyzerTrayIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ExitAction extends AbstractAction {

    private CurrencyRatesAnalyzerTrayIcon trayIcon;
    private final SystemTray tray;

    public ExitAction(CurrencyRatesAnalyzerTrayIcon trayIcon, SystemTray tray) {
        this.trayIcon = trayIcon;
        this.tray = tray;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tray.remove(trayIcon);
        DbFacade.getInstance().close();
        Logger.getRootLogger().info("Application end");
        System.exit(0);
    }
}
