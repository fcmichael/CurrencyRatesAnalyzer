package app.gui.panel;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends BasicContentPanel {

    public SettingsPanel() {
        setContentPanel(prepareContentPanel());
    }

    private JPanel prepareContentPanel(){
        JPanel contentPanel = new JPanel();
        Label l = new Label("Settings Panel");
        contentPanel.add(l);
        return contentPanel;
    }
}
