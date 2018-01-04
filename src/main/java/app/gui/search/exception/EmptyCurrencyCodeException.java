package app.gui.search.exception;

import app.i18n.I18nException;

public class EmptyCurrencyCodeException extends I18nException{

    public EmptyCurrencyCodeException() {
        super("EmptyCurrencyCode", "Empty currency code");
    }
}
