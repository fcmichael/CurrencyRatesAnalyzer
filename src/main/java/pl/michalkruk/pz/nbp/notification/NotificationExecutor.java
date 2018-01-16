package pl.michalkruk.pz.nbp.notification;

import pl.michalkruk.pz.db.DbFacade;
import pl.michalkruk.pz.nbp.analyse.AnalyzeThread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

public class NotificationExecutor {

    private static ScheduledExecutorService ses;
    private static ScheduledFuture<?> notificationHandle;
    private static AnalyzeThread analyzeThread;
    private static boolean analyzeStarted = false;

    public static void analyze(AnalyzeThread at) {
        ses = Executors.newSingleThreadScheduledExecutor();
        NotificationFrequency frequency = DbFacade.getInstance().getNotificationFrequencyObject();
        analyzeThread = at;
        notificationHandle = ses.scheduleAtFixedRate(
                analyzeThread,
                frequency.getInitialDelay(),
                frequency.getFrequency(),
                frequency.getTimeUnit());
        analyzeStarted = true;
    }

    public static void update() {
        if (analyzeStarted) {
            NotificationFrequency frequency = DbFacade.getInstance().getNotificationFrequencyObject();
            notificationHandle.cancel(true);
            notificationHandle = ses.scheduleAtFixedRate(
                    analyzeThread,
                    frequency.getFrequency(),
                    frequency.getFrequency(),
                    frequency.getTimeUnit());
        }
    }
}
