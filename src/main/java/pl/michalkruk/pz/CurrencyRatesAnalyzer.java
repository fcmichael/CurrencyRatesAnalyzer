package pl.michalkruk.pz;

import org.apache.log4j.Logger;
import pl.michalkruk.pz.db.DbFacade;
import pl.michalkruk.pz.gui.CurrencyRatesAnalyzerFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

        MenuItem aboutItem = new MenuItem("About");
        MenuItem exitItem = new MenuItem("Exit");

        popupMenu.add(aboutItem);
        popupMenu.addSeparator();
        popupMenu.add(exitItem);

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
                if(!frame.isVisible()){
                    frame.setVisible(true);
                }
            }
        });

        aboutItem.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        null,
                        "Author: Michał Kruk I5B4S1",
                        "",
                        JOptionPane.INFORMATION_MESSAGE));

        exitItem.addActionListener(e -> {
            tray.remove(trayIcon);
            DbFacade.getInstance().close();
            Logger.getRootLogger().info("Application end");
            System.exit(0);
        });

    }
}
