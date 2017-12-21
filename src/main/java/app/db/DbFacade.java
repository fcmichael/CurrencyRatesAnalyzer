package app.db;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DbFacade {

    private static final DbFacade instance = new DbFacade();
    private final EntityManagerFactory entityManagerFactory;
    private final String CurrencyRatesAnalyzerPersistenceUnit = "CRA";

    private DbFacade() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory(CurrencyRatesAnalyzerPersistenceUnit);
    }

    public static DbFacade getInstance() {
        return instance;
    }
}
