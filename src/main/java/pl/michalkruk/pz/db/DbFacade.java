package pl.michalkruk.pz.db;

import pl.michalkruk.pz.nbp.analyse.CurrentRatesProvider;
import pl.michalkruk.pz.nbp.model.Rate;
import pl.michalkruk.pz.nbp.notification.NotificationFrequency;
import pl.michalkruk.pz.util.PropertiesReader;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DbFacade {

    private static final DbFacade instance = new DbFacade();
    private final EntityManagerFactory entityManagerFactory;
    private final String CurrencyRatesAnalyzerPersistenceUnit = "CRA";
    private final EntityManager entityManager;
    private boolean resetDbAtStart;

    private DbFacade() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory(CurrencyRatesAnalyzerPersistenceUnit);
        entityManager = entityManagerFactory.createEntityManager();
        resetDbAtStart = Boolean.parseBoolean(PropertiesReader.getProperty("database.reset", "false"));
        initNotificationFrequency();
    }

    public static DbFacade getInstance() {
        return instance;
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }

    private void initNotificationFrequency() {
        if (notificationFrequencyNotExists()) {
            NotificationFrequency notificationFrequency = new NotificationFrequency();
            entityManager.getTransaction().begin();
            entityManager.persist(notificationFrequency);
            entityManager.getTransaction().commit();
        }
    }

    private List<Rate> findAll() {
        entityManager.getTransaction().begin();
        List<Rate> ratesList = entityManager.createQuery("Select r from Rate r", Rate.class).getResultList();
        entityManager.getTransaction().commit();

        return ratesList.stream()
                .sorted(Comparator.comparing(Rate::getCode)).collect(Collectors.toList());
    }

    public List<String> findFavouriteCurrencyCodes() {
        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        entityManager1.getTransaction().begin();
        List<Rate> ratesList = entityManager1.createQuery("Select r from Rate r where r.favourite = true", Rate.class).getResultList();
        entityManager1.getTransaction().commit();
        entityManager1.close();
        return ratesList.stream().map(Rate::getCode).collect(Collectors.toList());
    }

    public List<Rate> findAndUpdate() {
        List<Rate> rates = CurrentRatesProvider.getActualRates();
        persistRateList(rates);

        return findAll();
    }

    private void persistRateList(List<Rate> list) {
        if (findAll().size() == 0 || resetDbAtStart) {
            resetDbAtStart = false;
            entityManager.getTransaction().begin();
            list.forEach(entityManager::merge);
            entityManager.getTransaction().commit();
        } else {
            list.forEach(rate -> {
                Rate current = findByCode(rate.getCode());
                current.setMid(rate.getMid());
                current.setChange(rate.getChange());
            });
        }
    }

    private Rate findByCode(String code) {
        entityManager.getTransaction().begin();
        Rate rate = entityManager.createQuery("Select r from Rate r where r.code = '" + code + "'", Rate.class).getSingleResult();
        entityManager.getTransaction().commit();
        return rate;
    }

    public int getNotificationFrequency() {
        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        entityManager1.getTransaction().begin();
        int frequency = entityManager1.createQuery("Select nf.frequency from NotificationFrequency nf where nf.Id=-1", Integer.class).getSingleResult();
        entityManager1.getTransaction().commit();
        return frequency;
    }

    public void updateNotificationFrequency(int frequency) {
        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        entityManager1.getTransaction().begin();
        NotificationFrequency nf = entityManager1.createQuery("Select nf from NotificationFrequency nf where nf.Id=-1", NotificationFrequency.class).getSingleResult();
        nf.setFrequency(frequency);
        entityManager1.getTransaction().commit();
        nf.updated();
    }

    private boolean notificationFrequencyNotExists() {
        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        entityManager1.getTransaction().begin();
        List<NotificationFrequency> frequency = entityManager1.createQuery("Select nf from NotificationFrequency nf where nf.Id=-1", NotificationFrequency.class).getResultList();
        entityManager1.getTransaction().commit();
        return frequency.isEmpty();
    }

    public NotificationFrequency getNotificationFrequencyObject() {
        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        entityManager1.getTransaction().begin();
        NotificationFrequency frequency = entityManager1.createQuery("Select nf from NotificationFrequency nf where nf.Id=-1", NotificationFrequency.class).getSingleResult();
        entityManager1.getTransaction().commit();
        return frequency;
    }

}
