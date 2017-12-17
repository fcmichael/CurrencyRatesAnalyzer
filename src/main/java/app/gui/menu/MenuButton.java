package app.gui.menu;

import javax.swing.*;

public class MenuButton extends JButton {

    public MenuButton(String text, JPanel cardPanel) {
        super(text);
        this.addActionListener(new BookmarkChange(text, cardPanel));
    }
}
