package app.gui.settings.plaf;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PlafChangeAction extends AbstractAction {

	@Override
	public void actionPerformed(ActionEvent e) {
		PlafRadioButton plafRadioButton = (PlafRadioButton) e.getSource();
		Logger.getRootLogger().info("PLAF event - change to: " + plafRadioButton.getText());
		try {
			UIManager.setLookAndFeel(plafRadioButton.getLookAndFeel());
			SwingUtilities.updateComponentTreeUI(plafRadioButton.getRootPane());
		} catch (UnsupportedLookAndFeelException ex) {
			Logger.getRootLogger().warn("LookAndFeel not found", ex);
		}
	}
}
