package pl.michalkruk.pz.gui.search.exception;

import pl.michalkruk.pz.i18n.I18nException;

public class EmptyCurrencyCodeException extends I18nException {

    public EmptyCurrencyCodeException() {
        super("EmptyCurrencyCode", "Empty currency code");
    }
}
