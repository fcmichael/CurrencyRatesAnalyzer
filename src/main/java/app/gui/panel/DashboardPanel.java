package app.gui.panel;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends BasicContentPanel {

    public DashboardPanel() {
        setContentPanel(prepareContentPanel());
    }

    private JPanel prepareContentPanel(){
        JPanel contentPanel = new JPanel();
        Label l = new Label("Dashboard Panel");
        contentPanel.add(l);
        return contentPanel;
    }
}
