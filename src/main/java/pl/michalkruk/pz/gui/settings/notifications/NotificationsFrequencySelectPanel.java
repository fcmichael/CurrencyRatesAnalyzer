package pl.michalkruk.pz.gui.settings.notifications;

import pl.michalkruk.pz.i18n.MessagesReader;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class NotificationsFrequencySelectPanel extends JPanel implements Observer {

    private final String NOTIFICATION_FREQUENCY_MESSAGE_KEY = "SettingsNotificationFrequency";
    private final String MINUTES_MESSAGE_KEY = "SettingsNotificationFrequencyMinutes";
    private final JLabel notificationFrequencyLabel;
    private final JLabel notificationFrequencyMinutesLabel;
    private final Integer[] frequencyArray = {10, 30, 60, 120, 240, 360, 600};

    public NotificationsFrequencySelectPanel() {
        setLayout(new GridLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        JPanel labelPanel = new JPanel(new BorderLayout());
        notificationFrequencyLabel = new JLabel(MessagesReader.getInstance().getMessage(NOTIFICATION_FREQUENCY_MESSAGE_KEY));
        notificationFrequencyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        labelPanel.add(notificationFrequencyLabel, BorderLayout.CENTER);
        add(labelPanel);

        JPanel comboBoxPanel = new JPanel(new GridBagLayout());
        JComboBox<Integer> frequencyComboBox = new JComboBox<>(frequencyArray);
        frequencyComboBox.addActionListener(new NotificationsFrequencyChangeAction());
        comboBoxPanel.add(frequencyComboBox);
        notificationFrequencyMinutesLabel = new JLabel("  " + MessagesReader.getInstance().getMessage(MINUTES_MESSAGE_KEY));
        comboBoxPanel.add(notificationFrequencyMinutesLabel);

        add(comboBoxPanel);
        MessagesReader.getInstance().addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        notificationFrequencyLabel.setText(MessagesReader.getInstance().getMessage(NOTIFICATION_FREQUENCY_MESSAGE_KEY));
        notificationFrequencyMinutesLabel.setText("  " + MessagesReader.getInstance().getMessage(MINUTES_MESSAGE_KEY));
    }
}
