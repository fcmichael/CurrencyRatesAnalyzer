package app.gui.settings.language;

import app.i18n.ApplicationLanguage;
import app.i18n.MessagesReader;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

class LanguageComboBox extends JComboBox<ApplicationLanguage> {

    private final DefaultComboBoxModel<ApplicationLanguage> model;

    LanguageComboBox() {
        model = new DefaultComboBoxModel<>();
        addItemsToModel();
        setModel(model);
        setMaximumSize(new Dimension(200, 40));
        addActionListener(new LanguageChangeAction());
        setRenderer(new LanguageItemRenderer());
    }

    private void addItemsToModel() {
        List<ApplicationLanguage> applicationLanguages = Arrays.asList(ApplicationLanguage.values());
        ApplicationLanguage currentLanguage = MessagesReader.getInstance().getCurrentLanguage();
        model.addElement(currentLanguage);

        applicationLanguages
                .stream()
                .filter(l -> !(l.getShortcut().equalsIgnoreCase(currentLanguage.getShortcut())))
                .forEach(model::addElement);
    }
}
