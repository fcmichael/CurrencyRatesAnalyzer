package app.gui.panel;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends BasicPanel {

    public MainPanel() {
        setContentPanel(prepareContentPanel());
    }

    private JPanel prepareContentPanel(){
        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        return contentPanel;
    }
}
