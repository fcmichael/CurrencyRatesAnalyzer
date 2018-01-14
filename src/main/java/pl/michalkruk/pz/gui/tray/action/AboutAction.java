package pl.michalkruk.pz.gui.tray.action;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AboutAction extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(
                null,
                "Author: Michał Kruk I5B4S1",
                "",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
