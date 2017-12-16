package app.gui.settings;


import app.gui.BasicContentPanel;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends BasicContentPanel {

    private JPanel contentPanel;

    public SettingsPanel() {
        this.contentPanel = setContentPanel();
        addContentPanel(contentPanel);
    }

    private JPanel setContentPanel(){
        JPanel contentPanel = new JPanel();
        Label l = new Label("Settings Panel");
        contentPanel.add(l);
        return contentPanel;
    }
}
