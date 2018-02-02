package pl.michalkruk.pz.nbp.notification;

import lombok.Getter;
import lombok.Setter;
import pl.michalkruk.pz.util.PropertiesReader;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.concurrent.TimeUnit;

@Entity
@Getter
public class NotificationFrequency{

    @Id
    private Integer Id = -1;

    private int initialDelay = 0;

    @Setter
    private int frequency;

    @Enumerated(EnumType.STRING)
    private TimeUnit timeUnit = TimeUnit.MINUTES;

    public NotificationFrequency() {
        frequency = Integer.parseInt(PropertiesReader.getProperty("analyze.period.minutes", "360"));
    }

    public void updated() {
        NotificationExecutor.update();
    }
}
