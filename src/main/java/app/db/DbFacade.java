package app.db;

import app.nbp.model.Rate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DbFacade {

//    private final EntityManagerFactory entityManagerFactory;
//    private final String CurrencyRatesAnalyzerPersistenceUnit = "CRA";
//    private final EntityManager entityManager;
//
//    public DbFacade() {
//        this.entityManagerFactory = Persistence.createEntityManagerFactory(CurrencyRatesAnalyzerPersistenceUnit);
//        entityManager = entityManagerFactory.createEntityManager();
////        Rate a = new Rate();
////
////        a.setCode("AA");
//
//        entityManager.getTransaction().begin();
//
//        findAll();
//
//        entityManager.getTransaction().commit();
//
//    }
//
//    public void close(){
//        entityManager.close();
//        entityManagerFactory.close();
//    }
//
//    public void findAll(){
//        System.out.println(entityManager.createQuery("Select r from Rate r").getResultList());
//
//    }
}
