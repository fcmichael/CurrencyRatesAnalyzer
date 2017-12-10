package app.gui;

import app.gui.panel.MainPanel;
import app.util.PropertiesReader;

import javax.swing.*;

public class CurrencyRatesAnalyzerFrame extends JFrame {

    JPanel mainPanel;

    public CurrencyRatesAnalyzerFrame() {
        prepareFrameSizeAndTitle();
        // set all
        // frame.getContentPane().add(...);
        setVisible(true);

    }

    private void prepareFrameSizeAndTitle() {
        setTitle("Currency rates");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(Integer.parseInt(PropertiesReader.getProperty("frame.size.width", "800")),
                Integer.parseInt(PropertiesReader.getProperty("frame.size.height", "600")));
        setLocation(Integer.parseInt(PropertiesReader.getProperty("frame.location.x", "0")),
                Integer.parseInt(PropertiesReader.getProperty("frame.location.y", "0")));
        prepareMainPanel();
    }

    private void prepareMainPanel(){
        add(new MainPanel());
    }
}
