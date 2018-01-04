package app.i18n;

abstract public class I18nException extends RuntimeException {
    private String messageKey;

    public I18nException(String messageKey, String message) {
        super(message);
        this.messageKey = messageKey;
    }

    @Override
    public String getLocalizedMessage() {
        return MessagesReader.getInstance().getMessage(messageKey);
    }
}
