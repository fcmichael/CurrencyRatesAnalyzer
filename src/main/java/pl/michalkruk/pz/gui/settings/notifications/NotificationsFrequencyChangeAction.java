package pl.michalkruk.pz.gui.settings.notifications;

import org.apache.log4j.Logger;
import pl.michalkruk.pz.nbp.notification.NotificationFrequency;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class NotificationsFrequencyChangeAction extends AbstractAction{

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox languageComboBox = (JComboBox) e.getSource();
        Integer selectedValue = (Integer) languageComboBox.getSelectedItem();
        if(selectedValue != null){
            NotificationFrequency.getInstance().setFrequency(selectedValue);
            Logger.getRootLogger().info("Notifications frequency - change to: " + selectedValue);
        }
    }
}
