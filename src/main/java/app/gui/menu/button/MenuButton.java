package app.gui.menu.button;

import javax.swing.*;
import java.awt.*;

public class MenuButton extends JButton {

    public MenuButton(String text, JPanel cardPanel) {
        super(text);
        addActionListener(e -> {
            CardLayout cl = (CardLayout)(cardPanel.getLayout());
            String changeToPanel = this.getText();
            cl.show(cardPanel, changeToPanel);
        });
    }
}
