package pl.michalkruk.pz.gui.settings.plaf;

import pl.michalkruk.pz.i18n.MessagesReader;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

public class PLAFSelectPanel extends JPanel implements Observer {

	private final String SETTINGS_PATTERN_MESSAGE_KEY = "SettingsPattern";
	private final JLabel jLabel = new JLabel(MessagesReader.getInstance().getMessage(SETTINGS_PATTERN_MESSAGE_KEY));

	private final PLAFConfiguration plafConfiguration;

	public PLAFSelectPanel() {
		plafConfiguration = PLAFConfiguration.getInstance();
		setLayout(new GridLayout());
        setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
		jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel labelPanel = new JPanel(new BorderLayout());
        labelPanel.add(jLabel, BorderLayout.CENTER);
        add(labelPanel);

		List<PLAFRadioButton> PLAFRadioButtons = plafConfiguration.getLOOK_AND_FEEL_MAP().entrySet().stream()
				.map(entry -> {
					PLAFRadioButton button = new PLAFRadioButton(entry.getKey(), entry.getValue());
					if(button.getLookAndFeel().equals(plafConfiguration.getCurrentLookAndFeel())){
						button.setSelected(true);
					}
					return button;
				})
				.collect(Collectors.toList());

		ButtonGroup group = new ButtonGroup();
		PLAFRadioButtons.forEach(group::add);

		JPanel buttonPanel = new JPanel(new GridBagLayout());
        JPanel buttonGroupPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 75, 0));
		PLAFRadioButtons.forEach(buttonGroupPanel::add);
		buttonPanel.add(buttonGroupPanel);
		add(buttonPanel);
		MessagesReader.getInstance().addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		jLabel.setText(MessagesReader.getInstance().getMessage(SETTINGS_PATTERN_MESSAGE_KEY));
	}
}
