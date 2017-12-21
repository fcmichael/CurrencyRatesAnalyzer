package app.gui.settings;

import app.gui.BasicContentPanel;
import app.gui.settings.language.LanguageSelectPanel;
import app.gui.settings.plaf.PLAFSelectPanel;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends BasicContentPanel {

	private final JPanel contentPanel;
	private final JPanel languageSelectPanel;
	private final JPanel patternSelectPanel;

	public SettingsPanel() {
		this.contentPanel = new JPanel(new GridLayout(8, 1));
		this.languageSelectPanel = new LanguageSelectPanel();
		this.patternSelectPanel = new PLAFSelectPanel();

		contentPanel.add(languageSelectPanel);
		contentPanel.add(patternSelectPanel);
		addContentPanel(contentPanel);
	}
}
