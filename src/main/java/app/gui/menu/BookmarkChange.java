package app.gui.menu;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

class BookmarkChange extends AbstractAction{

    @Override
    public void actionPerformed(ActionEvent e) {
        MenuButton menuButton = (MenuButton) e.getSource();
        JPanel cardPanel = menuButton.getCardPanel();
        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
        String changeToPanel = menuButton.getCardName();

        Logger.getRootLogger().info("Bookmark event - change to: " + changeToPanel);

        cardLayout.show(cardPanel, changeToPanel);
    }
}
