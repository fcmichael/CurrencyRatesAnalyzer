package pl.michalkruk.pz.gui.settings.language;

import pl.michalkruk.pz.i18n.ApplicationLanguage;
import pl.michalkruk.pz.i18n.MessagesReader;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;

class LanguageChangeAction extends AbstractAction {

	@Override
	public void actionPerformed(ActionEvent e) {
		LanguageComboBox languageComboBox = (LanguageComboBox) e.getSource();
		ApplicationLanguage applicationLanguage = (ApplicationLanguage) languageComboBox.getSelectedItem();
		if(applicationLanguage != null){
            MessagesReader.getInstance().changeLanguage(applicationLanguage);
            Logger.getRootLogger().info("Language event - change to: " + applicationLanguage.getName());
        }
	}
}
