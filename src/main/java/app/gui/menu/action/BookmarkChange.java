package app.gui.menu.action;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BookmarkChange extends AbstractAction{

    private JPanel cardPanel;
    private String bookMarkName;

    public BookmarkChange(String bookMarkName, JPanel cardPanel){
        super();
        this.bookMarkName = bookMarkName;
        this.cardPanel = cardPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Logger.getRootLogger().info("Bookmark event - change to: " + this.bookMarkName);
        CardLayout cl = (CardLayout)(cardPanel.getLayout());
        String changeToPanel = this.bookMarkName;
        cl.show(cardPanel, changeToPanel);
    }
}
