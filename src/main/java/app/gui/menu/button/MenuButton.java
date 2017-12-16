package app.gui.menu.button;

import app.gui.menu.action.BookmarkChange;

import javax.swing.*;

public class MenuButton extends JButton {

    public MenuButton(String text, JPanel cardPanel) {
        super(text);
        this.addActionListener(new BookmarkChange(text, cardPanel));
    }
}
