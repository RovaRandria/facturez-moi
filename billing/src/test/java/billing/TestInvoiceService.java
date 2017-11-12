package billing;

import ca.ulaval.glo4002.billing.domain.bills.Bill;
import ca.ulaval.glo4002.billing.domain.bills.BillId;
import ca.ulaval.glo4002.billing.domain.clients.ClientId;
import ca.ulaval.glo4002.billing.domain.clients.DueTerm;
import ca.ulaval.glo4002.billing.domain.invoices.Invoice;
import ca.ulaval.glo4002.billing.domain.invoices.InvoiceId;
import ca.ulaval.glo4002.billing.domain.products.Product;
import ca.ulaval.glo4002.billing.dto.InvoiceDto;
import ca.ulaval.glo4002.billing.repository.HibernateBillRepository;
import ca.ulaval.glo4002.billing.repository.HibernateInvoiceRepository;
import ca.ulaval.glo4002.billing.services.InvoiceService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.*;

public class TestInvoiceService {

  private static final int VALID_BILL_ID = 1;
  private static final int INVALID_BILL_ID = 0;
  private static final int VALID_CLIENT_ID = 1;

  private InvoiceService service;
  private BillId validBillId;
  private BillId invalidBillId;
  private Date toDaysDate;
  private ClientId clientId;
  private List<Product> products;

  @Mock
  private HibernateInvoiceRepository invoiceRepository;
  @Mock
  private HibernateBillRepository billRepository;

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Before
  public void init() {
    service = new InvoiceService(invoiceRepository, billRepository);
    validBillId = new BillId(VALID_BILL_ID);
    invalidBillId = new BillId(INVALID_BILL_ID);
    clientId = new ClientId(VALID_CLIENT_ID);
    toDaysDate = new Date();
    products = new ArrayList<Product>();

  }

  @Test
  public void givenBillId_whenInvoiceExists_thenReturnTrue() {
    final InvoiceId INVOICE_ID = new InvoiceId(validBillId);
    final Invoice INVOICE = new Invoice(INVOICE_ID, toDaysDate, DueTerm.DAYS30);
    Mockito.when(invoiceRepository.find(validBillId)).thenReturn(INVOICE);
    boolean invoiceExists = service.invoiceExists(validBillId);
    assertTrue(invoiceExists);
  }

  @Test
  public void givenBillId_whenInvoiceDoesNotExist_thenReturnFalse() {
    boolean invoiceExists = service.invoiceExists(invalidBillId);
    assertFalse(invoiceExists);
  }

  @Test
  public void givenBillId_whenCreatingInvoice_thenDueDateIsCorrect() {
    Bill bill = new Bill(validBillId, clientId, toDaysDate, DueTerm.DAYS30, products);
    Mockito.when(billRepository.find(validBillId)).thenReturn(bill);
    Date billCreationDate = bill.getCreationDate();
    InvoiceDto invoiceDto = service.create(validBillId);
    Date expectedDueDate = addDaysToDate(billCreationDate, DueTerm.convertToInt(bill.getDueTerm()));
    assertTrue(expectedDueDate.equals(invoiceDto.getExpectedPayment()));
  }

  private Date addDaysToDate(Date date, int days) {
    Calendar sumDate = Calendar.getInstance();
    sumDate.setTime(date);
    sumDate.add(Calendar.DATE, days);
    return sumDate.getTime();
  }
}
