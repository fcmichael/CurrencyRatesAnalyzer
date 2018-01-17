package pl.michalkruk.pz.gui.dashboard;

import pl.michalkruk.pz.gui.tray.CurrencyRatesAnalyzerTrayIcon;
import pl.michalkruk.pz.i18n.MessagesReader;
import pl.michalkruk.pz.util.PropertiesReader;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

class DashboardLabelPanel extends JPanel implements Observer {

    DashboardLabelPanel() {
        setPreferredSize(new Dimension(Integer.parseInt(PropertiesReader.getProperty("frame.size.width", "1152")), 60));
        MessagesReader.getInstance().addObserver(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        JFrame frame = (JFrame) SwingUtilities.getRoot(this);
        int width = frame.getWidth();
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        Image image = new ImageIcon(CurrencyRatesAnalyzerTrayIcon.class.getResource("/images/tray/tray.png")).getImage();

        // left image
        g2d.drawRect(10, 10, 40, 40);
        g2d.drawImage(image, 12,12,36,36, null);

        // right image
        g2d.drawRect(width-50, 10, 40, 40);
        g2d.drawImage(image, width-48,12,36,36, null);

        // label
        g2d.drawRect(60, 10, width-120, 40);
        String message = MessagesReader.getInstance().getMessage("DashboardTableHeader");
        g2d.drawString(message, (int) (((width-120)/2)-(message.length()*2.2)), 35);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
