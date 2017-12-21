package app.gui.settings.language;

import app.CurrencyRatesAnalyzer;
import app.i18n.ApplicationLanguage;

import javax.swing.*;
import java.awt.*;

class LanguageItemRenderer extends JPanel implements ListCellRenderer<ApplicationLanguage> {

    private final JLabel languageLabel = new JLabel();

    LanguageItemRenderer() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.insets = new Insets(2, 2, 2, 2);

        languageLabel.setOpaque(true);
        languageLabel.setHorizontalAlignment(JLabel.LEFT);
        add(languageLabel, constraints);
        setBackground(Color.LIGHT_GRAY);
    }

    @Override
    public Component getListCellRendererComponent(JList list, ApplicationLanguage value, int index, boolean isSelected, boolean cellHasFocus) {
        languageLabel.setText(value.getName());
        languageLabel.setIcon(new ImageIcon(CurrencyRatesAnalyzer.class.getResource(value.getImagePath())));

        if (isSelected) {
            languageLabel.setBackground(Color.BLUE);
            languageLabel.setForeground(Color.YELLOW);
        } else {
            languageLabel.setForeground(Color.BLACK);
            languageLabel.setBackground(Color.LIGHT_GRAY);
        }

        return this;
    }
}
