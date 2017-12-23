package ca.ulaval.glo4002.billing.domain.accounts;

public interface AccountRepository {

  void insert(Account account);

  Account find(AccountId accountId);

  void update(Account account);

}
