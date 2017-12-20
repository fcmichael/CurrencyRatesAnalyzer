package app.gui.settings;


import app.gui.BasicContentPanel;
import app.gui.settings.language.LanguageComboBox;
import app.gui.settings.language.LanguageItemRenderer;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class SettingsPanel extends BasicContentPanel {

    private final JPanel contentPanel;
    private final JPanel languageSelectPanel;

    public SettingsPanel() {
        contentPanel = new JPanel(new GridLayout(4,1));
        languageSelectPanel = new JPanel(new GridLayout());

        JLabel jLabel = new JLabel("Language");
        jLabel.setPreferredSize(new Dimension(200,200));
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton save = new JButton("Save");
        save.setPreferredSize(new Dimension(50,50));
        save.setHorizontalAlignment(SwingConstants.CENTER);

        JComboBox jComboBox = createLanguageSelectBox();
        jComboBox.setPreferredSize(new Dimension(200,200));

        languageSelectPanel.add(jLabel);
        languageSelectPanel.add(jComboBox);
        languageSelectPanel.add(save);

        contentPanel.add(languageSelectPanel);
        addContentPanel(contentPanel);
    }

    private JComboBox createLanguageSelectBox() {
        LanguageComboBox languageComboBox = new LanguageComboBox();
        languageComboBox.setPreferredSize(new Dimension(120, 30));

        return languageComboBox;
    }
}
