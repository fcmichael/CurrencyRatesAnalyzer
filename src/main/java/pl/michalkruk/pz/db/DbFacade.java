package pl.michalkruk.pz.db;

import pl.michalkruk.pz.nbp.analyse.CurrentRatesProvider;
import pl.michalkruk.pz.nbp.model.Rate;
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
    }

    public static DbFacade getInstance() {
        return instance;
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }

    private List<Rate> findAll() {
        entityManager.getTransaction().begin();
        List<Rate> ratesList = entityManager.createQuery("Select r from Rate r", Rate.class).getResultList();
        entityManager.getTransaction().commit();

        return ratesList.stream()
                .sorted(Comparator.comparing(Rate::getCode)).collect(Collectors.toList());
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
        }
    }

}
