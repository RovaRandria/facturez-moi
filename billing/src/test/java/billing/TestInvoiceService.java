package billing;

import ca.ulaval.glo4002.billing.domain.bills.Bill;
import ca.ulaval.glo4002.billing.domain.bills.BillId;
import ca.ulaval.glo4002.billing.domain.clients.Client;
import ca.ulaval.glo4002.billing.domain.clients.ClientId;
import ca.ulaval.glo4002.billing.domain.clients.DueTerm;
import ca.ulaval.glo4002.billing.domain.invoices.Invoice;
import ca.ulaval.glo4002.billing.domain.invoices.InvoiceId;
import ca.ulaval.glo4002.billing.domain.products.Product;
import ca.ulaval.glo4002.billing.dto.InvoiceDto;
import ca.ulaval.glo4002.billing.exceptions.InvoiceNotFoundException;
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

  private static final int VALID_ID = 1;
  private static final int INVALID_ID = 0;

  private InvoiceService service;
  private BillId validBillId;
  private BillId invalidBillId;
  private Date toDaysDate;
  private ClientId clientId;
  private List<Product> products;
  private InvoiceId validInvoiceId;
  private InvoiceId invalidInvoiceId;
  private Invoice invoice;

  @Mock
  private HibernateInvoiceRepository invoiceRepository;
  @Mock
  private HibernateBillRepository billRepository;
  @Mock
  private Invoice existing_invoice;

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Before
  public void init() {
    service = new InvoiceService(invoiceRepository, billRepository);

    validBillId = new BillId(VALID_ID);
    invalidBillId = new BillId(INVALID_ID);

    validInvoiceId = new InvoiceId(VALID_ID);
    invalidInvoiceId = new InvoiceId(INVALID_ID);

    invoice = new Invoice();
    Mockito.when(existing_invoice.getId()).thenReturn(validInvoiceId);
    Mockito.when(invoiceRepository.find(validInvoiceId)).thenReturn(existing_invoice);

    clientId = new ClientId(VALID_ID);
    toDaysDate = new Date();
    products = new ArrayList<Product>();

  }

  @Test
  public void givenInvoiceId_whenInvoiceExistsIsCalled_thenInvoiceRepositoryFindMethodIsCalled() {
    service.invoiceExists(validInvoiceId);
    Mockito.verify(invoiceRepository).find(validInvoiceId);
  }

  @Test
  public void givenValidInvoiceId_whenInvoiceExistsIsCalled_thenReturnTrue() {
    Mockito.when(invoiceRepository.find(validInvoiceId)).thenReturn(invoice);
    assertTrue(service.invoiceExists(validInvoiceId));
  }

  @Test
  public void givenInvalidInvoiceId_whenInvoiceExistsIsCalled_thenReturnFalse() {
    Mockito.when(invoiceRepository.find(invalidInvoiceId)).thenReturn(null);
    assertFalse(service.invoiceExists(invalidInvoiceId));
  }

  @Test(expected = InvoiceNotFoundException.class)
  public void givenInvalidBillId_whenCreateInvoice_thenInvoiceNotFoundException() {
    Mockito.when(billRepository.find(invalidBillId)).thenReturn(null);
    service.create(invalidBillId);
  }

  @Test
  public void givenBillId_whenCreateInvoice_thenDueDateIsCorrect() {
    Client client = new Client();
    Bill bill = new Bill(validBillId, client, toDaysDate, DueTerm.DAYS30, products);
    Mockito.when(billRepository.find(validBillId)).thenReturn(bill);
    Mockito.when(invoiceRepository.insert(Mockito.any())).thenReturn(validInvoiceId);
    Date billCreationDate = bill.getCreationDate();
    InvoiceDto invoiceDto = service.create(validBillId);
    Date expectedDueDate = addDaysToDate(billCreationDate, DueTerm.toInt(bill.getDueTerm()));

    String expectedDueDateAsString = expectedDueDate.toInstant().toString();
    String obtainedDueDateAsString = invoiceDto.getExpectedPayment();
    assertTrue(expectedDueDateAsString.equals(obtainedDueDateAsString));
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
