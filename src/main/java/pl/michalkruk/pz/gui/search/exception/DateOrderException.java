package pl.michalkruk.pz.gui.search.exception;

import pl.michalkruk.pz.i18n.I18nException;

public class DateOrderException extends I18nException {

    public DateOrderException() {
        super("DataOrderException", "Start date must predate end date");
    }
}
