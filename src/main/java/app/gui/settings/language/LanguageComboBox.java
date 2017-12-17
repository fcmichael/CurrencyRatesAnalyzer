package app.gui.settings.language;

import app.i18n.ApplicationLanguage;

import javax.swing.*;

public class LanguageComboBox extends JComboBox<ApplicationLanguage> {

    private DefaultComboBoxModel<ApplicationLanguage> model;

    public LanguageComboBox() {
        model = new DefaultComboBoxModel<>();
        addItemsToModel();
        setModel(model);
        setRenderer(new LanguageItemRenderer());
    }

    private void addItemsToModel() {
        for (ApplicationLanguage language : ApplicationLanguage.values()) {
            model.addElement(language);
        }
    }
}
