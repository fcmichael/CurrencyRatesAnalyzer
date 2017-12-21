package app.i18n;

import lombok.Getter;

@Getter
public enum ApplicationLanguage {
    PL("Polski", "pl", "/messages/messages_pl.xml", "/images/language/pl.png"),
    EN("English", "en", "/messages/messages_en.xml", "/images/language/en.png");

    private final String name;
    private final String shortcut;
    private final String messagesPath;
    private final String imagePath;

    ApplicationLanguage(String name, String shortcut, String messagesPath, String imagePath) {
        this.name = name;
        this.shortcut = shortcut;
        this.messagesPath = messagesPath;
        this.imagePath = imagePath;
    }
}
