package pl.michalkruk.pz.gui;

import org.apache.log4j.Logger;
import pl.michalkruk.pz.db.DbFacade;
import pl.michalkruk.pz.i18n.MessagesReader;
import pl.michalkruk.pz.util.PropertiesReader;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

public class CurrencyRatesAnalyzerFrame extends JFrame implements Observer {

    private final String FRAME_NAME_MESSAGE_KEY = "CurrencyRatesAnalyzerFrameName";
    private final boolean systemTraySupported;

    public CurrencyRatesAnalyzerFrame(boolean systemTraySupported) {
        this.systemTraySupported = systemTraySupported;
        prepareFrameSizeAndTitle();
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
        MessagesReader.getInstance().addObserver(this);
    }

    private void prepareFrameSizeAndTitle() {
        setTitle(MessagesReader.getInstance().getMessage(FRAME_NAME_MESSAGE_KEY));
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (systemTraySupported) {
                    setVisible(false);
                    Logger.getRootLogger().info("Close frame");
                } else {
                    DbFacade.getInstance().close();
                    Logger.getRootLogger().info("Application end");
                    System.exit(0);
                }
            }
        });
        setSize(Integer.parseInt(PropertiesReader.getProperty("frame.size.width", "800")),
                Integer.parseInt(PropertiesReader.getProperty("frame.size.height", "600")));
        prepareMainPanel();
    }

    private void prepareMainPanel() {
        add(new NavigationPanel());
    }

    @Override
    public void update(Observable o, Object arg) {
        setTitle(MessagesReader.getInstance().getMessage(FRAME_NAME_MESSAGE_KEY));
    }
}
