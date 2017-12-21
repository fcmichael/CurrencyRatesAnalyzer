package app.gui.settings.plaf;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
class PlafRadioButton extends JRadioButton {

	private LookAndFeel lookAndFeel;

	PlafRadioButton(String text, LookAndFeel lookAndFeel, Dimension maximumSize) {
		super(text);
		this.lookAndFeel = lookAndFeel;
		setMaximumSize(maximumSize);
		addActionListener(new PlafChangeAction());
	}
}
