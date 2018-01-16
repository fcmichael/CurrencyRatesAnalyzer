package pl.michalkruk.pz.gui.tray;

import org.apache.log4j.Logger;
import pl.michalkruk.pz.gui.CurrencyRatesAnalyzerFrame;
import pl.michalkruk.pz.gui.tray.action.AboutAction;
import pl.michalkruk.pz.gui.tray.action.ExitAction;
import pl.michalkruk.pz.gui.tray.action.TrayIconClick;
import pl.michalkruk.pz.i18n.MessagesReader;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class CurrencyRatesAnalyzerTrayIcon extends TrayIcon implements Observer {

    private final String FRAME_NAME_MESSAGE_KEY = "CurrencyRatesAnalyzerFrameName";
    private final String ABOUT_LABEL_MESSAGE_KEY = "About";
    private final String EXIT_LABEL_MESSAGE_KEY = "Exit";
    private MenuItem aboutItem;
    private MenuItem exitItem;
    private PopupMenu popupMenu;
    private final SystemTray tray;

    public CurrencyRatesAnalyzerTrayIcon(CurrencyRatesAnalyzerFrame frame, SystemTray tray) {
        super(new ImageIcon(CurrencyRatesAnalyzerTrayIcon.class.getResource("/images/tray/tray.png")).getImage());
        addMouseListener(new TrayIconClick(frame));
        this.tray = tray;
        popupMenu = initializePopupMenu();
        setPopupMenu(popupMenu);
        MessagesReader.getInstance().addObserver(this);

        try {
            tray.add(this);
        } catch (AWTException e) {
            Logger.getRootLogger().warn("TrayIcon could not be added.");
        }
    }

    private PopupMenu initializePopupMenu() {
        popupMenu = new PopupMenu();
        aboutItem = new MenuItem(MessagesReader.getInstance().getMessage(ABOUT_LABEL_MESSAGE_KEY));
        exitItem = new MenuItem(MessagesReader.getInstance().getMessage(EXIT_LABEL_MESSAGE_KEY));
        popupMenu.add(aboutItem);
        popupMenu.addSeparator();
        popupMenu.add(exitItem);

        aboutItem.addActionListener(new AboutAction());
        exitItem.addActionListener(new ExitAction(this, tray));

        return popupMenu;
    }

    public void displayMessage(String message) {
        displayMessage(MessagesReader.getInstance().getMessage(FRAME_NAME_MESSAGE_KEY),
                message,
                TrayIcon.MessageType.INFO);
    }

    @Override
    public void update(Observable o, Object arg) {
        aboutItem.setLabel(MessagesReader.getInstance().getMessage(ABOUT_LABEL_MESSAGE_KEY));
        exitItem.setLabel(MessagesReader.getInstance().getMessage(EXIT_LABEL_MESSAGE_KEY));
    }
}
