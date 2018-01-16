package pl.michalkruk.pz.gui.settings;

import pl.michalkruk.pz.gui.BasicContentPanel;
import pl.michalkruk.pz.gui.settings.language.LanguageSelectPanel;
import pl.michalkruk.pz.gui.settings.notifications.NotificationsFrequencySelectPanel;
import pl.michalkruk.pz.gui.settings.plaf.PLAFSelectPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class SettingsPanel extends BasicContentPanel {

    public SettingsPanel() {
		super("Settings", KeyEvent.VK_U);
        JPanel contentPanel = new JPanel(new GridLayout(8, 1));

		contentPanel.add(new LanguageSelectPanel());
		contentPanel.add(new PLAFSelectPanel());
		contentPanel.add(new NotificationsFrequencySelectPanel());
		addContentPanel(contentPanel);
	}

	@Override
	public void updateData() {

	}
}
