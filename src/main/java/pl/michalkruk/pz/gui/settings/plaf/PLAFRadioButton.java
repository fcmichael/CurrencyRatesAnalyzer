package pl.michalkruk.pz.gui.settings.plaf;

import lombok.Getter;

import javax.swing.*;

@Getter
class PLAFRadioButton extends JRadioButton {

	private final LookAndFeel lookAndFeel;

	PLAFRadioButton(String text, LookAndFeel lookAndFeel) {
		super(text);
		this.lookAndFeel = lookAndFeel;
		addActionListener(new PLAFChangeAction());
	}
}
