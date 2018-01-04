package app.gui.search.exception;

import app.i18n.I18nException;

public class DaysLimitExceedException extends I18nException {

    public DaysLimitExceedException() {
        super("DaysLimitExceed", "Limit of 93 days has been exceeded");
    }
}
