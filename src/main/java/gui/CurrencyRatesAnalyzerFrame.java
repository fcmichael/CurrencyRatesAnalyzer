package gui;

import javax.swing.*;

public class CurrencyRatesAnalyzerFrame extends JFrame {

    public CurrencyRatesAnalyzerFrame(int width, int height, int x, int y) {
        super("Currency rates");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocation(x, y);
        // set all
        // frame.getContentPane().add(...);
        setVisible(true);
    }
}
