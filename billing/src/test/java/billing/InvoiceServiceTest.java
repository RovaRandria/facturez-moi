package billing;

import static junit.framework.TestCase.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import ca.ulaval.glo4002.billing.domain.accounts.Account;
import ca.ulaval.glo4002.billing.domain.accounts.AccountId;
import ca.ulaval.glo4002.billing.domain.bills.Bill;
import ca.ulaval.glo4002.billing.domain.bills.BillId;
import ca.ulaval.glo4002.billing.domain.clients.Client;
import ca.ulaval.glo4002.billing.domain.clients.DueTerm;
import ca.ulaval.glo4002.billing.domain.invoices.Invoice;
import ca.ulaval.glo4002.billing.domain.invoices.InvoiceId;
import ca.ulaval.glo4002.billing.domain.products.Product;
import ca.ulaval.glo4002.billing.dto.InvoiceDto;
import ca.ulaval.glo4002.billing.exceptions.InvoiceNotFoundException;
import ca.ulaval.glo4002.billing.repository.HibernateAccountRepository;
import ca.ulaval.glo4002.billing.repository.HibernateBillRepository;
import ca.ulaval.glo4002.billing.repository.HibernateInvoiceRepository;
import ca.ulaval.glo4002.billing.repository.HibernateTransactionRepository;
import ca.ulaval.glo4002.billing.services.InvoiceService;

public class InvoiceServiceTest {

  private static final int VALID_ID = 1;
  private static final int INVALID_ID = 0;
  private static final long ACCOUNT_ID = 0;

  private InvoiceService service;
  private InvoiceId validInvoiceId;
  private InvoiceId invalidInvoiceId;

  private final BillId VALID_BILL_ID = new BillId(VALID_ID);
  private final Client DEFAULT_CLIENT = new Client();
  private final Date TODAYS_DATE = new Date();
  private final List<Product> NO_PRODUCTS = new ArrayList<>();
  private final Bill VALID_BILL = new Bill(VALID_BILL_ID, DEFAULT_CLIENT, TODAYS_DATE, DueTerm.DAYS30, NO_PRODUCTS);
  private final BigDecimal INVOICE_AMOUNT_100 = new BigDecimal(100);

  @Mock
  private HibernateInvoiceRepository invoiceRepository;
  @Mock
  private HibernateBillRepository billRepository;
  @Mock
  private HibernateAccountRepository accountRepository;
  @Mock
  private HibernateTransactionRepository transactionRepository;
  @Mock
  private Invoice existing_invoice;
  @Mock
  private Account existing_account;

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Before
  public void init() {
    service = new InvoiceService(invoiceRepository, billRepository, accountRepository, transactionRepository);
    validInvoiceId = new InvoiceId(VALID_ID);
    invalidInvoiceId = new InvoiceId(INVALID_ID);

    Mockito.when(existing_invoice.getId()).thenReturn(validInvoiceId);
    Mockito.when(existing_invoice.getPaidAmount()).thenReturn(INVOICE_AMOUNT_100);
    Mockito.when(existing_invoice.getBill()).thenReturn(VALID_BILL);

    Mockito.when(invoiceRepository.find(validInvoiceId)).thenReturn(existing_invoice);
    Mockito.when(accountRepository.find(Mockito.any())).thenReturn(new Account(new AccountId(ACCOUNT_ID)));

  }

  @Test
  public void givenInvoiceId_whenInvoiceExistsIsCalled_thenInvoiceRepositoryFindMethodIsCalled() {
    service.invoiceExists(validInvoiceId);
    Mockito.verify(invoiceRepository).find(validInvoiceId);
  }

  @Test
  public void givenValidInvoiceId_whenInvoiceExistsIsCalled_thenReturnTrue() {
    final Invoice DEFAULT_INVOICE = new Invoice();
    Mockito.when(invoiceRepository.find(validInvoiceId)).thenReturn(DEFAULT_INVOICE);
    assertTrue(service.invoiceExists(validInvoiceId));
  }

  @Test
  public void givenInvalidInvoiceId_whenInvoiceExistsIsCalled_thenReturnFalse() {
    Mockito.when(invoiceRepository.find(invalidInvoiceId)).thenReturn(null);
    assertFalse(service.invoiceExists(invalidInvoiceId));
  }

  @Test(expected = InvoiceNotFoundException.class)
  public void givenInvalidBillId_whenCreateInvoice_thenInvoiceNotFoundException() {
    final BillId INVALID_BILL_ID = new BillId(INVALID_ID);
    Mockito.when(billRepository.find(INVALID_BILL_ID)).thenReturn(null);
    service.create(INVALID_BILL_ID);
  }

  @Test
  public void givenBillId_whenCreateInvoice_thenDueDateIsCorrect() {

    Mockito.when(billRepository.find(VALID_BILL_ID)).thenReturn(VALID_BILL);
    Mockito.when(invoiceRepository.insert(Mockito.any())).thenReturn(validInvoiceId);
    Date billCreationDate = VALID_BILL.getCreationDate();
    InvoiceDto invoiceDto = service.create(VALID_BILL_ID);
    Date expectedDueDate = addDaysToDate(billCreationDate, DueTerm.toInt(VALID_BILL.getDueTerm()));

    String expectedDueDateAsString = expectedDueDate.toInstant().toString();
    String obtainedDueDateAsString = invoiceDto.getExpectedPayment();
    assertEquals(expectedDueDateAsString, obtainedDueDateAsString);
  }

  @Test
  public void givenInvoiceId_whenDeleteInvoice_thenDelete() {
    service.delete(validInvoiceId);
    Mockito.verify(invoiceRepository).delete(existing_invoice);
  }

  @Test(expected = InvoiceNotFoundException.class)
  public void givenInvoiceId_whenDeleteInvoice_thenInvoiceNotFoundException() {
    service.delete(invalidInvoiceId);
  }

  private Date addDaysToDate(Date date, int days) {
    Calendar sumDate = Calendar.getInstance();
    sumDate.setTime(date);
    sumDate.add(Calendar.DATE, days);
    return sumDate.getTime();
  }
}
