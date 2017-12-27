package app.i18n;

import lombok.Getter;

import java.util.Locale;

@Getter
public enum ApplicationLanguage {
    PL(new Locale("pl"), "Polski", "pl", "/messages/messages_pl.xml", "/images/language/pl.png"),
    EN(Locale.ENGLISH, "English", "en", "/messages/messages_en.xml", "/images/language/en.png");

    private final Locale locale;
    private final String name;
    private final String shortcut;
    private final String messagesPath;
    private final String imagePath;

    ApplicationLanguage(Locale locale, String name, String shortcut, String messagesPath, String imagePath) {
        this.locale = locale;
        this.name = name;
        this.shortcut = shortcut;
        this.messagesPath = messagesPath;
        this.imagePath = imagePath;
    }
}
