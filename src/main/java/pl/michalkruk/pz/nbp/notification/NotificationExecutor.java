package pl.michalkruk.pz.nbp.notification;

import pl.michalkruk.pz.nbp.analyse.AnalyzeThread;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

public class NotificationExecutor implements Observer {

    private final ScheduledExecutorService ses;
    private ScheduledFuture<?> notificationHandle;
    private final AnalyzeThread analyzeThread;

    public NotificationExecutor(AnalyzeThread analyzeThread) {
        NotificationFrequency.getInstance().addObserver(this);
        ses = Executors.newSingleThreadScheduledExecutor();
        this.analyzeThread = analyzeThread;
        NotificationFrequency frequency = NotificationFrequency.getInstance();
        notificationHandle = ses.scheduleAtFixedRate(
                this.analyzeThread,
                frequency.getInitialDelay(),
                frequency.getFrequency(),
                frequency.getTimeUnit());
    }

    @Override
    public void update(Observable o, Object arg) {
        notificationHandle.cancel(true);
        NotificationFrequency frequency = (NotificationFrequency) o;
        notificationHandle = ses.scheduleAtFixedRate(
                this.analyzeThread,
                frequency.getInitialDelay(),
                frequency.getFrequency(),
                frequency.getTimeUnit());
    }
}
