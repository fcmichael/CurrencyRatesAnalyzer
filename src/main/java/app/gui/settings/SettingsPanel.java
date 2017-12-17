package app.gui.settings;


import app.gui.BasicContentPanel;
import app.gui.settings.language.LanguageComboBox;
import app.gui.settings.language.LanguageItemRenderer;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class SettingsPanel extends BasicContentPanel {

    private JPanel contentPanel;

    public SettingsPanel() {
        contentPanel = new JPanel();
        contentPanel.add(createLanguageSelectBox());

        addContentPanel(contentPanel);
    }

    private JComboBox createLanguageSelectBox() {
        LanguageComboBox languageComboBox = new LanguageComboBox();
        languageComboBox.setPreferredSize(new Dimension(120, 30));

        return languageComboBox;
    }
}
