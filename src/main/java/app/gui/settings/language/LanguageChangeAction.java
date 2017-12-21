package app.gui.settings.language;

import app.i18n.ApplicationLanguage;
import app.i18n.MessagesReader;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LanguageChangeAction extends AbstractAction {

	@Override
	public void actionPerformed(ActionEvent e) {
		LanguageComboBox languageComboBox = (LanguageComboBox) e.getSource();
		ApplicationLanguage applicationLanguage = (ApplicationLanguage) languageComboBox.getSelectedItem();
		Logger.getRootLogger().info("Language event - change to: " + applicationLanguage.getName());
		MessagesReader.getInstance().changeLanguage(applicationLanguage);
	}
}
