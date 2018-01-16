package pl.michalkruk.pz.gui.settings.language;

import pl.michalkruk.pz.i18n.MessagesReader;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class LanguageSelectPanel extends JPanel implements Observer {

    private final String SETTINGS_MESSAGE_KEY = "SettingsLanguage";
	private final JLabel jLabel = new JLabel(MessagesReader.getInstance().getMessage(SETTINGS_MESSAGE_KEY));

    public LanguageSelectPanel() {
        setLayout(new GridLayout());
        setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        JPanel labelPanel = new JPanel(new BorderLayout());
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        labelPanel.add(jLabel, BorderLayout.CENTER);
        add(labelPanel);

        JPanel comboBoxPanel = new JPanel(new GridBagLayout());
        LanguageComboBox languageComboBox = new LanguageComboBox();
        comboBoxPanel.add(languageComboBox);

        add(comboBoxPanel);
        MessagesReader.getInstance().addObserver(this);
    }

	@Override
	public void update(Observable o, Object arg) {
		this.jLabel.setText(MessagesReader.getInstance().getMessage(SETTINGS_MESSAGE_KEY));
	}
}
