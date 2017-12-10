package app.gui;

import app.util.PropertiesReader;

import javax.swing.*;

public class CurrencyRatesAnalyzerFrame extends JFrame {

    public CurrencyRatesAnalyzerFrame() {
        super("Currency rates");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(Integer.parseInt(PropertiesReader.getProperty("frame.size.width", "800")),
                Integer.parseInt(PropertiesReader.getProperty("frame.size.height", "600")));
        setLocation(Integer.parseInt(PropertiesReader.getProperty("frame.location.x", "0")),
                Integer.parseInt(PropertiesReader.getProperty("frame.location.y", "0")));
        // set all
        // frame.getContentPane().add(...);
        setVisible(true);

    }
}
