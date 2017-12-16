package app.gui.menu.action;

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
        CardLayout cl = (CardLayout)(cardPanel.getLayout());
        String changeToPanel = this.bookMarkName;
        cl.show(cardPanel, changeToPanel);
    }
}
