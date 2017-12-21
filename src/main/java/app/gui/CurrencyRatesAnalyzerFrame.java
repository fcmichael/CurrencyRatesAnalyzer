package app.gui;

import app.i18n.MessagesReader;
import app.util.PropertiesReader;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class CurrencyRatesAnalyzerFrame extends JFrame implements Observer{

    public CurrencyRatesAnalyzerFrame() {
        prepareFrameSizeAndTitle();
        setVisible(true);
        MessagesReader.getInstance().addObserver(this);
    }

    private void prepareFrameSizeAndTitle() {
        setTitle(MessagesReader.getInstance().getMessage("CurrencyRatesAnalyzerFrameName"));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
