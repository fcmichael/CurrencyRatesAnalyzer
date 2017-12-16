package app.gui.favourite;


import app.gui.BasicContentPanel;

import javax.swing.*;
import java.awt.*;

public class FavouritePanel extends BasicContentPanel {

    private JPanel contentPanel;

    public FavouritePanel() {
        this.contentPanel = setContentPanel();
        addContentPanel(contentPanel);
    }

    private JPanel setContentPanel(){
        JPanel contentPanel = new JPanel();
        Label l = new Label("Favourite Panel");
        contentPanel.add(l);
        return contentPanel;
    }
}
