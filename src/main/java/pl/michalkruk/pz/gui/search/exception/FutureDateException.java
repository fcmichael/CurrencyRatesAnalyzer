package pl.michalkruk.pz.gui.search.exception;

import pl.michalkruk.pz.i18n.I18nException;

public class FutureDateException extends I18nException {

    public FutureDateException() {
        super("FutureDateException", "End date must be less or equal today");
    }
}
