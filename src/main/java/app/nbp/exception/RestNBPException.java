package app.nbp.exception;

import app.i18n.I18nException;

public class RestNBPException extends I18nException{

    public RestNBPException() {
        super("DataLoadException", "Couldn't load data");
    }
}
