package billing;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import ca.ulaval.glo4002.billing.assembler.AccountAssembler;
import ca.ulaval.glo4002.billing.domain.accounts.Account;
import ca.ulaval.glo4002.billing.domain.accounts.AccountId;
import ca.ulaval.glo4002.billing.domain.accounts.AccountRepository;
import ca.ulaval.glo4002.billing.domain.clients.Client;
import ca.ulaval.glo4002.billing.domain.clients.ClientRepository;
import ca.ulaval.glo4002.billing.domain.transactions.Transaction;
import ca.ulaval.glo4002.billing.domain.transactions.TransactionType;
import ca.ulaval.glo4002.billing.domain.transactions.TypeOperation;
import ca.ulaval.glo4002.billing.dto.AccountDto;
import ca.ulaval.glo4002.billing.services.AccountService;

public class AccountServiceTest {

  private AccountService service;

  private static final AccountId ACCOUNT_ID = new AccountId(0);

  private Account account;

  private AccountAssembler accountAssembler = new AccountAssembler();

  private final static Client DEFAULT_CLIENT = new Client();

  private final static Date HALLOWEEN = new GregorianCalendar(2017, 9, 31).getTime();

  private final static Date CHRISTMAS = new GregorianCalendar(2017, 11, 25).getTime();

  @Mock
  private AccountRepository accountRepository;

  @Mock
  private ClientRepository clientRepository;

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  public static final Transaction INVOICE_TRANSACTION = new Transaction(HALLOWEEN, TransactionType.INVOICE, 100,
      TypeOperation.CREDIT, DEFAULT_CLIENT, ACCOUNT_ID);

  public static final Transaction PAYMENT_TRANSACTION = new Transaction(CHRISTMAS, TransactionType.PAYMENT, 100,
      TypeOperation.DEBIT, DEFAULT_CLIENT, ACCOUNT_ID);

  @Before
  public void init() {
    service = new AccountService(clientRepository, accountRepository, accountAssembler);

    account = new Account(ACCOUNT_ID);
    account.addTransaction(INVOICE_TRANSACTION);
    account.addTransaction(PAYMENT_TRANSACTION);
    Mockito.when(accountRepository.find(Mockito.any())).thenReturn(account);
  }

  @Test
  public void GivenYearThenAllEntriesAreRetrieved() {
    Account annualAccount = new Account(ACCOUNT_ID);
    annualAccount.addTransaction(INVOICE_TRANSACTION);
    annualAccount.addTransaction(PAYMENT_TRANSACTION);

    AccountDto accountDto = service.retrieveEntries(0, 0, 2017);

    assertEquals(accountAssembler.create(ACCOUNT_ID, annualAccount.getEntries()), accountDto);
  }

  @Test
  public void GivenBeforeChristmasPeriodThenEntriesBeforeChristmasAreRetrieved() {
    Account entriesBeforeChristmas = new Account(ACCOUNT_ID);
    entriesBeforeChristmas.addTransaction(INVOICE_TRANSACTION);

    AccountDto accountDto = service.retrieveEntries(1, 11, 2017);

    assertEquals(accountAssembler.create(ACCOUNT_ID, entriesBeforeChristmas.getEntries()), accountDto);
  }
}
