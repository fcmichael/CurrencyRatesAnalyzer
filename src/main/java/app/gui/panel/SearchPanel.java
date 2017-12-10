package app.gui.panel;

import javax.swing.*;
import java.awt.*;

public class SearchPanel extends BasicContentPanel {

    public SearchPanel() {
        setContentPanel(prepareContentPanel());
    }

    private JPanel prepareContentPanel(){
        JPanel contentPanel = new JPanel();
        Label l = new Label("Search Panel");
        contentPanel.add(l);
        return contentPanel;
    }
}
