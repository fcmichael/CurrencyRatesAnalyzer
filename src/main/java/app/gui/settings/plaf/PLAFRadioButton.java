package app.gui.settings.plaf;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
class PLAFRadioButton extends JRadioButton {

	private final LookAndFeel lookAndFeel;

	PLAFRadioButton(String text, LookAndFeel lookAndFeel, Dimension maximumSize) {
		super(text);
		this.lookAndFeel = lookAndFeel;
		setMaximumSize(maximumSize);
		addActionListener(new PLAFChangeAction());
	}
}
