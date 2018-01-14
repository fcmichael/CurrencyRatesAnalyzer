package pl.michalkruk.pz.gui.tray.action;

import pl.michalkruk.pz.i18n.MessagesReader;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AboutAction extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(
                null,
                MessagesReader.getInstance().getMessage("ApplicationAuthor"),
                MessagesReader.getInstance().getMessage("About"),
                JOptionPane.INFORMATION_MESSAGE);
    }
}
