package pl.michalkruk.pz.gui.tray.action;

import pl.michalkruk.pz.gui.CurrencyRatesAnalyzerFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TrayIconClick extends MouseAdapter {

    private final CurrencyRatesAnalyzerFrame frame;

    public TrayIconClick(CurrencyRatesAnalyzerFrame frame) {
        this.frame = frame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!frame.isVisible()) {
            frame.setVisible(true);
        }
    }
}
