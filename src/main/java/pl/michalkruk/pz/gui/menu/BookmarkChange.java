package pl.michalkruk.pz.gui.menu;

import pl.michalkruk.pz.gui.BasicContentPanel;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

class BookmarkChange extends AbstractAction{

    @Override
    public void actionPerformed(ActionEvent e) {
        MenuButton menuButton = (MenuButton) e.getSource();
        JPanel cardPanel = menuButton.getCardPanel();
        BasicContentPanel contentPanel = menuButton.getContentPanel();
        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
        String changeToPanel = contentPanel.getName();

        Logger.getRootLogger().info("Bookmark event - change to: " + changeToPanel);

        cardLayout.show(cardPanel, changeToPanel);
        contentPanel.updateData();
    }
}
