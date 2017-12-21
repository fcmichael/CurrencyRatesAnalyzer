package app.gui.settings;

import app.gui.BasicContentPanel;
import app.gui.settings.language.LanguageComboBox;
import app.gui.settings.plaf.PlafSelectPanel;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends BasicContentPanel {

    private JPanel contentPanel;
    private JPanel languageSelectPanel;
    private JPanel patternSelectPanel;

    public SettingsPanel() {
        this.contentPanel = setContentPanel();
        this.languageSelectPanel = setLanguageSelectPanel();
        this.patternSelectPanel = new PlafSelectPanel();

        contentPanel.add(languageSelectPanel);
        contentPanel.add(patternSelectPanel);
        addContentPanel(contentPanel);
    }

    private JPanel setContentPanel(){
        return new JPanel(new GridLayout(8,1));
    }

    private JPanel setLanguageSelectPanel(){
        JPanel languageSelectPanel = new JPanel();
        languageSelectPanel.setLayout(new BoxLayout(languageSelectPanel, BoxLayout.LINE_AXIS));

        JLabel jLabel = new JLabel("Language");
        jLabel.setMaximumSize(new Dimension(250,400));
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JComboBox jComboBox = new LanguageComboBox();
        jComboBox.setMaximumSize(new Dimension(200,40));

        languageSelectPanel.add(jLabel);
        languageSelectPanel.add(Box.createRigidArea(new Dimension(228,0)));
        languageSelectPanel.add(jComboBox);
        return languageSelectPanel;
    }
}
