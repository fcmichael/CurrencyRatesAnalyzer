package pl.michalkruk.pz;

import pl.michalkruk.pz.db.DbFacade;
import pl.michalkruk.pz.gui.CurrencyRatesAnalyzerFrame;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CurrencyRatesAnalyzer {

    public static void main(String[] args) {
        Logger.getRootLogger().info("Application start");
        CurrencyRatesAnalyzerFrame frame = new CurrencyRatesAnalyzerFrame();

        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }

        final PopupMenu popupMenu = new PopupMenu();
        final TrayIcon trayIcon =
                new TrayIcon(new ImageIcon(CurrencyRatesAnalyzer.class.getResource("/images/tray/tray.png")).getImage());
        final SystemTray tray = SystemTray.getSystemTray();

//        // Create a popup menu components
//        MenuItem aboutItem = new MenuItem("About");
//        CheckboxMenuItem cb1 = new CheckboxMenuItem("Set auto size");
//        CheckboxMenuItem cb2 = new CheckboxMenuItem("Set tooltip");
//        Menu displayMenu = new Menu("Display");
//        MenuItem errorItem = new MenuItem("Error");
//        MenuItem warningItem = new MenuItem("Warning");
//        MenuItem infoItem = new MenuItem("Info");
//        MenuItem noneItem = new MenuItem("None");
        MenuItem exitItem = new MenuItem("Exit");
//
//        //Add components to popup menu
//        popup.add(aboutItem);
//        popup.addSeparator();
//        popup.add(cb1);
//        popup.add(cb2);
//        popup.addSeparator();
//        popup.add(displayMenu);
//        displayMenu.add(errorItem);
//        displayMenu.add(warningItem);
//        displayMenu.add(infoItem);
//        displayMenu.add(noneItem);
        popupMenu.add(exitItem);
//
        trayIcon.setPopupMenu(popupMenu);

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
            return;
        }

        trayIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(frame.isVisible()){
                    frame.setVisible(true);
                }
            }
        });

        exitItem.addActionListener(e -> {
            tray.remove(trayIcon);
            DbFacade.getInstance().close();
            Logger.getRootLogger().info("Application end");
            System.exit(0);
        });

    }
}
