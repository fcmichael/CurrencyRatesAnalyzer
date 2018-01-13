package pl.michalkruk.pz.gui;

import org.apache.log4j.Logger;
import pl.michalkruk.pz.i18n.MessagesReader;
import pl.michalkruk.pz.util.PropertiesReader;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

public class CurrencyRatesAnalyzerFrame extends JFrame implements Observer{

    public CurrencyRatesAnalyzerFrame() {
        prepareFrameSizeAndTitle();
        setResizable(false);
        setVisible(true);
        MessagesReader.getInstance().addObserver(this);
    }

    private void prepareFrameSizeAndTitle() {
        setTitle(MessagesReader.getInstance().getMessage("CurrencyRatesAnalyzerFrameName"));
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                Logger.getRootLogger().info("Close frame");
            }
        });
        setSize(Integer.parseInt(PropertiesReader.getProperty("frame.size.width", "800")),
                Integer.parseInt(PropertiesReader.getProperty("frame.size.height", "600")));
        setLocation(Integer.parseInt(PropertiesReader.getProperty("frame.location.x", "0")),
                Integer.parseInt(PropertiesReader.getProperty("frame.location.y", "0")));
        prepareMainPanel();
    }

    private void prepareMainPanel(){
        add(new NavigationPanel());
    }

    @Override
    public void update(Observable o, Object arg) {
        setTitle(MessagesReader.getInstance().getMessage("CurrencyRatesAnalyzerFrameName"));
    }
}
