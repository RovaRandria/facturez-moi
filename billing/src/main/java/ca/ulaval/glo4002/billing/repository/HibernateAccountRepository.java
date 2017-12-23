package ca.ulaval.glo4002.billing.repository;

import javax.persistence.EntityManager;

import ca.ulaval.glo4002.billing.domain.accounts.Account;
import ca.ulaval.glo4002.billing.domain.accounts.AccountId;
import ca.ulaval.glo4002.billing.domain.accounts.AccountRepository;
import ca.ulaval.glo4002.billing.entitymanager.EntityManagerProvider;

public class HibernateAccountRepository implements AccountRepository {

  private EntityManager entityManager;

  public HibernateAccountRepository() {
    this.entityManager = new EntityManagerProvider().getEntityManager();
  }

  @Override
  public void insert(Account account) {
    entityManager.getTransaction().begin();
    entityManager.persist(account);
    entityManager.getTransaction().commit();
  }

  @Override
  public Account find(AccountId accountId) {
    Account account = entityManager.find(Account.class, accountId);
    if (account == null) {
      account = new Account(new AccountId(0));
    }

    return account;
  }

  @Override
  public void update(Account account) {
    entityManager.getTransaction().begin();
    entityManager.merge(account);
    entityManager.getTransaction().commit();
  }

}
