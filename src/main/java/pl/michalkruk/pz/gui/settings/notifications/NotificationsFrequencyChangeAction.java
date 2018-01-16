package pl.michalkruk.pz.gui.settings.notifications;

import org.apache.log4j.Logger;
import pl.michalkruk.pz.db.DbFacade;

import javax.swing.*;
import java.awt.event.ActionEvent;

class NotificationsFrequencyChangeAction extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox languageComboBox = (JComboBox) e.getSource();
        Integer selectedValue = (Integer) languageComboBox.getSelectedItem();
        if (selectedValue != null) {

            SwingWorker swingWorker = new SwingWorker() {

                @Override
                protected Void doInBackground() {
                    DbFacade.getInstance().updateNotificationFrequency(selectedValue);
                    return null;
                }
            };

            swingWorker.execute();

            Logger.getRootLogger().info("Notifications frequency - change to: " + selectedValue);
        }
    }
}
