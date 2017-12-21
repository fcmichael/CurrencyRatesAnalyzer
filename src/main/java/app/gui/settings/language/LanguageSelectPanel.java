package app.gui.settings.language;

import app.i18n.MessagesReader;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class LanguageSelectPanel extends JPanel implements Observer {

	private final JLabel jLabel = new JLabel(MessagesReader.getInstance().getMessage("SettingsLanguage"));

	public LanguageSelectPanel() {
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.jLabel.setMaximumSize(new Dimension(250, 400));
		this.jLabel.setHorizontalAlignment(SwingConstants.CENTER);

		add(jLabel);
		add(Box.createRigidArea(new Dimension(328, 0)));
		add(new LanguageComboBox());
		MessagesReader.getInstance().addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		this.jLabel.setText(MessagesReader.getInstance().getMessage("SettingsLanguage"));
	}
}
