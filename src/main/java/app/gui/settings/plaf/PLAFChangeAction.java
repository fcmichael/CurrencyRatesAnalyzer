package app.gui.settings.plaf;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;

class PLAFChangeAction extends AbstractAction {

	@Override
	public void actionPerformed(ActionEvent e) {
		PLAFRadioButton PLAFRadioButton = (PLAFRadioButton) e.getSource();
		Logger.getRootLogger().info("PLAF event - change to: " + PLAFRadioButton.getText());
		try {
			UIManager.setLookAndFeel(PLAFRadioButton.getLookAndFeel());
			SwingUtilities.updateComponentTreeUI(PLAFRadioButton.getRootPane());
		} catch (UnsupportedLookAndFeelException ex) {
			Logger.getRootLogger().warn("LookAndFeel not found", ex);
		}
	}
}
