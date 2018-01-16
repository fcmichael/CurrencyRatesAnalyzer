package pl.michalkruk.pz.gui.tray.action;

import pl.michalkruk.pz.i18n.MessagesReader;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AboutAction extends AbstractAction {

    private final String ABOUT_MESSAGE_KEY = "About";
    private final String APPLICATION_AUTHOR_MESSAGE_KEY = "ApplicationAuthor";

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(
                null,
                MessagesReader.getInstance().getMessage(APPLICATION_AUTHOR_MESSAGE_KEY),
                MessagesReader.getInstance().getMessage(ABOUT_MESSAGE_KEY),
                JOptionPane.INFORMATION_MESSAGE);
    }
}
