package app.gui.menu;

import app.gui.BasicContentPanel;
import app.i18n.MessagesReader;
import lombok.Getter;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

@Getter
public class MenuButton extends JButton implements Observer {

	private final JPanel cardPanel;
	private final BasicContentPanel contentPanel;

	public MenuButton(String text, JPanel cardPanel, BasicContentPanel contentPanel, Integer mnemonic) {
		super(text);
		this.cardPanel = cardPanel;
		this.contentPanel = contentPanel;
		this.addActionListener(new BookmarkChange());
		setMnemonic(mnemonic);
		MessagesReader.getInstance().addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		setText(MessagesReader.getInstance().getMessage(contentPanel.getName()));
	}
}
