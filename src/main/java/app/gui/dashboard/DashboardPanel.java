package app.gui.dashboard;


import app.gui.BasicContentPanel;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends BasicContentPanel {

    private final JPanel contentPanel;

    public DashboardPanel() {
        this.contentPanel = setContentPanel();
        addContentPanel(contentPanel);
    }

    private JPanel setContentPanel(){
        JPanel contentPanel = new JPanel();
        Label l = new Label("Dashboard Panel");
        contentPanel.add(l);
        return contentPanel;
    }
}
