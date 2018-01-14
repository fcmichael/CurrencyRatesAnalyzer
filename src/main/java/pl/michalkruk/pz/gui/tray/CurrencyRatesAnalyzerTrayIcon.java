package pl.michalkruk.pz.gui.tray;

import org.apache.log4j.Logger;
import pl.michalkruk.pz.gui.CurrencyRatesAnalyzerFrame;
import pl.michalkruk.pz.gui.tray.action.AboutAction;
import pl.michalkruk.pz.gui.tray.action.ExitAction;
import pl.michalkruk.pz.gui.tray.action.TrayIconClick;

import javax.swing.*;
import java.awt.*;

public class CurrencyRatesAnalyzerTrayIcon extends TrayIcon {

    private PopupMenu popupMenu;
    private final SystemTray tray;

    public CurrencyRatesAnalyzerTrayIcon(CurrencyRatesAnalyzerFrame frame, SystemTray tray) {
        super(new ImageIcon(CurrencyRatesAnalyzerTrayIcon.class.getResource("/images/tray/tray.png")).getImage());
        addMouseListener(new TrayIconClick(frame));
        this.tray = tray;
        popupMenu = initializePopupMenu();
        setPopupMenu(popupMenu);

        try {
            tray.add(this);
        } catch (AWTException e) {
            Logger.getRootLogger().warn("TrayIcon could not be added.");
        }
    }

    private PopupMenu initializePopupMenu() {
        popupMenu = new PopupMenu();
        MenuItem aboutItem = new MenuItem("About");
        MenuItem exitItem = new MenuItem("Exit");
        popupMenu.add(aboutItem);
        popupMenu.addSeparator();
        popupMenu.add(exitItem);

        aboutItem.addActionListener(new AboutAction());
        exitItem.addActionListener(new ExitAction(this, tray));

        return popupMenu;
    }
}
