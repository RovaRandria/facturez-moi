package ca.ulaval.glo4002.billing.services;

import ca.ulaval.glo4002.billing.assembler.AccountAssembler;
import ca.ulaval.glo4002.billing.domain.accounts.Account;
import ca.ulaval.glo4002.billing.domain.accounts.AccountId;
import ca.ulaval.glo4002.billing.domain.accounts.AccountRepository;
import ca.ulaval.glo4002.billing.domain.clients.ClientRepository;
import ca.ulaval.glo4002.billing.dto.AccountDto;
import ca.ulaval.glo4002.billing.repository.HibernateAccountRepository;

public class AccountService extends BillingService {

  private AccountRepository accountRepository;
  private AccountAssembler accountAssembler;
  private static final long ACCOUNT_ID = 0;

  public AccountService() {
    prepareDatabase();
    this.accountRepository = new HibernateAccountRepository();
    this.accountAssembler = new AccountAssembler();
  }

  public AccountService(ClientRepository clientRepository, AccountRepository accountRepository,
      AccountAssembler accountAssembler) {
    this.accountRepository = accountRepository;
    this.accountAssembler = accountAssembler;
  }

  public AccountDto retrieveEntries(int startMonth, int endMonth, int year) {
    Account account = accountRepository.find(new AccountId(ACCOUNT_ID));
    AccountDto accountDto = null;
    if (startMonth == 0 || endMonth == 0) {
      accountDto = accountAssembler.create(account.getAccountId(), account.getEntriesForGivenYear(year));
    } else if ((startMonth > 0 && startMonth < 13) && (endMonth > startMonth && endMonth < 13)) {
      accountDto = accountAssembler.create(account.getAccountId(),
          account.getEntriesForGivenPeriodOfYear(startMonth, endMonth, year));
    }
    return accountDto;
  }

}
