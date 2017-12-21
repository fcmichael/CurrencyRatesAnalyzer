package app.gui.menu;

import app.i18n.MessagesReader;
import lombok.Getter;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

@Getter
public class MenuButton extends JButton implements Observer{

    private final String cardName;
    private final JPanel cardPanel;

    public MenuButton(String text, String cardName, JPanel cardPanel) {
        super(text);
        this.cardName = cardName;
        this.cardPanel = cardPanel;
        this.addActionListener(new BookmarkChange());
        MessagesReader.getInstance().addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        setText(MessagesReader.getInstance().getMessage(this.cardName));
    }
}
