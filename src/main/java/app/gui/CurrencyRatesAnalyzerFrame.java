package app.gui;

import app.db.DbFacade;
import app.i18n.MessagesReader;
import app.util.PropertiesReader;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

public class CurrencyRatesAnalyzerFrame extends JFrame implements Observer{

    private final DbFacade dbFacade;

    public CurrencyRatesAnalyzerFrame() {
        dbFacade = new DbFacade();
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
//                dbFacade.findAll();
//                dbFacade.close();
                Logger.getRootLogger().info("Application end");
                System.exit(0);
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
