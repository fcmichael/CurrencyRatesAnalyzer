package app.gui.favourite;


import app.gui.BasicContentPanel;
import app.gui.DataUpdater;

import javax.swing.*;
import java.awt.*;

public class FavouritePanel extends BasicContentPanel{

    private final JPanel contentPanel;

    public FavouritePanel() {
        super("Favourite");
        this.contentPanel = setContentPanel();
        addContentPanel(contentPanel);
    }

    private JPanel setContentPanel(){
        JPanel contentPanel = new JPanel();
        Label l = new Label("Favourite Panel");
        contentPanel.add(l);
        return contentPanel;
    }

    @Override
    public void updateData() {
        System.out.println("FAVOURITE update");
    }
}
