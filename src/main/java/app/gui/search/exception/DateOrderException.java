package app.gui.search.exception;

import app.i18n.I18nException;

public class DateOrderException extends I18nException {

    public DateOrderException() {
        super("DataOrderException", "Start date must predate end date");
    }
}
