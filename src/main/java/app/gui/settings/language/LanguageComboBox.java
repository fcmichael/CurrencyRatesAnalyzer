package app.gui.settings.language;

import app.i18n.ApplicationLanguage;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

class LanguageComboBox extends JComboBox<ApplicationLanguage> {

    private DefaultComboBoxModel<ApplicationLanguage> model;

    LanguageComboBox() {
        model = new DefaultComboBoxModel<>();
        addItemsToModel();
        setModel(model);
        setMaximumSize(new Dimension(200, 40));
        addActionListener(new LanguageChangeAction());
        setRenderer(new LanguageItemRenderer());
    }

    private void addItemsToModel() {
        for (ApplicationLanguage language : ApplicationLanguage.values()) {
            model.addElement(language);
        }
    }
}
