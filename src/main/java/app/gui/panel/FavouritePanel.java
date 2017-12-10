package app.gui.panel;

import javax.swing.*;
import java.awt.*;

public class FavouritePanel extends BasicContentPanel {

    public FavouritePanel() {
        setContentPanel(prepareContentPanel());
    }

    private JPanel prepareContentPanel(){
        JPanel contentPanel = new JPanel();
        Label l = new Label("Favourite Panel");
        contentPanel.add(l);
        return contentPanel;
    }
}
