package pl.michalkruk.pz.nbp.notification;

import lombok.Getter;

import java.util.Observable;
import java.util.concurrent.TimeUnit;

@Getter
public class NotificationFrequency extends Observable {

    private static final NotificationFrequency instance = new NotificationFrequency();
    private int initialDelay = 0;
    private int frequency = 3;
    private TimeUnit timeUnit = TimeUnit.SECONDS;

    public static NotificationFrequency getInstance(){
        return instance;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
        setChanged();
        notifyObservers();
    }
}
