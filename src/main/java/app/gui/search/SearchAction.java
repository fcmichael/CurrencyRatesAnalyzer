package app.gui.search;

import app.i18n.I18nException;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SearchAction extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            validateInput(e);
        } catch (I18nException ex){
            Logger.getRootLogger().warn(ex.getMessage());
            showValidationError(e, ex.getLocalizedMessage());
        }
    }

    private void validateInput(ActionEvent e){
        JButton aClass = (JButton) e.getSource();
        SearchPanel searchPanel = (SearchPanel) aClass.getParent().getParent().getParent();
        searchPanel.validateInput();
    }

    private void showValidationError(ActionEvent e, String message){
        JButton aClass = (JButton) e.getSource();
        JRootPane rootPane = aClass.getRootPane();
        JOptionPane.showMessageDialog(rootPane, message, "", JOptionPane.ERROR_MESSAGE);
    }
}
