package app.db;

import app.i18n.ApplicationLanguage;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AppConfiguration {

    @Id
    private Integer id = 1;

    private ApplicationLanguage applicationLanguage;
}
