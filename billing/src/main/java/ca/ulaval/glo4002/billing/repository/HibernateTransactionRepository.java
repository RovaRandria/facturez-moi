package ca.ulaval.glo4002.billing.repository;

import javax.persistence.EntityManager;

import ca.ulaval.glo4002.billing.domain.transactions.Transaction;
import ca.ulaval.glo4002.billing.domain.transactions.TransactionRepository;
import ca.ulaval.glo4002.billing.entitymanager.EntityManagerProvider;

public class HibernateTransactionRepository implements TransactionRepository {

  private EntityManager entityManager;

  public HibernateTransactionRepository() {
    this.entityManager = new EntityManagerProvider().getEntityManager();
  }

  @Override
  public void insert(Transaction transaction) {
    entityManager.getTransaction().begin();
    entityManager.persist(transaction);
    entityManager.getTransaction().commit();
  }

  @Override
  public Transaction find(int transactionId) {
    return entityManager.find(Transaction.class, transactionId);
  }
}
