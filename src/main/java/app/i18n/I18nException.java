package app.i18n;

import javax.swing.*;

abstract public class I18nException extends RuntimeException {
    private final String messageKey;

    protected I18nException(String messageKey, String message) {
        super(message);
        this.messageKey = messageKey;
    }

    @Override
    public String getLocalizedMessage() {
        return MessagesReader.getInstance().getMessage(messageKey);
    }

    public void displayMessageDialog(JComponent component){
        JRootPane rootPane = component.getRootPane();
        JOptionPane.showMessageDialog(rootPane, getLocalizedMessage(), "", JOptionPane.ERROR_MESSAGE);
    }
}
