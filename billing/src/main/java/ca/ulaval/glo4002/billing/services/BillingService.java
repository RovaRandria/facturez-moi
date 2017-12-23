package ca.ulaval.glo4002.billing.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import ca.ulaval.glo4002.billing.entitymanager.EntityManagerFactoryProvider;
import ca.ulaval.glo4002.billing.entitymanager.EntityManagerProvider;

abstract class BillingService {

  public void prepareDatabase() {
    EntityManagerFactory entityManagerFactory = EntityManagerFactoryProvider.getFactory();
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityManagerProvider.setEntityManager(entityManager);
  }
}
