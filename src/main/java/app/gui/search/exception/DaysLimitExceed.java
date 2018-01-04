package app.gui.search.exception;

import app.i18n.I18nException;

public class DaysLimitExceed extends I18nException {

    public DaysLimitExceed() {
        super("DaysLimitExceed", "Limit of 93 days has been exceeded");
    }
}
