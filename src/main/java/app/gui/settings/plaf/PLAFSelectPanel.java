package app.gui.settings.plaf;

import app.i18n.MessagesReader;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

public class PLAFSelectPanel extends JPanel implements Observer {

	private final JLabel jLabel = new JLabel(MessagesReader.getInstance().getMessage("SettingsPattern"));

	private final PLAFConfiguration plafConfiguration;

	public PLAFSelectPanel() {
		plafConfiguration = PLAFConfiguration.getInstance();
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

		this.jLabel.setMaximumSize(new Dimension(250, 400));
		this.jLabel.setHorizontalAlignment(SwingConstants.CENTER);

		List<PLAFRadioButton> PLAFRadioButtons = plafConfiguration.getLOOK_AND_FEEL_MAP().entrySet().stream()
				.map(entry -> {
					PLAFRadioButton button = new PLAFRadioButton(entry.getKey(), entry.getValue(), new Dimension(200, 40));
					if(button.getLookAndFeel().equals(plafConfiguration.getCurrentLookAndFeel())){
						button.setSelected(true);
					}
					return button;
				})
				.collect(Collectors.toList());

		ButtonGroup group = new ButtonGroup();
		PLAFRadioButtons.forEach(group::add);

		add(jLabel);
		add(Box.createRigidArea(new Dimension(200, 0)));
		PLAFRadioButtons.forEach(this::add);
		MessagesReader.getInstance().addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		jLabel.setText(MessagesReader.getInstance().getMessage("SettingsPattern"));
	}
}
