package billing;

import ca.ulaval.glo4002.billing.domain.bills.BillId;
import ca.ulaval.glo4002.billing.domain.invoices.Invoice;
import ca.ulaval.glo4002.billing.repository.HibernateInvoiceRepository;
import ca.ulaval.glo4002.billing.services.InvoiceService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static junit.framework.TestCase.*;

public class TestInvoiceService {

  private static final int VALID_BILL_ID = 1;
  private static final int INVALID_BILL_ID = 0;

  private InvoiceService service;
  private BillId validBillId;
  private BillId invalidBillId;

  @Mock
  private HibernateInvoiceRepository invoiceRepository;

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Before
  public void init() {
    service = new InvoiceService(invoiceRepository);
    validBillId = new BillId(VALID_BILL_ID);
    invalidBillId = new BillId(INVALID_BILL_ID);
  }

  @Test
  public void givenBillId_whenInvoiceExists_thenReturnTrue() {
    final Invoice INVOICE = new Invoice();
    Mockito.when(invoiceRepository.findInvoice(validBillId)).thenReturn(INVOICE);
    boolean invoiceExists = service.invoiceExists(validBillId);
    assertTrue(invoiceExists);
  }

  @Test
  public void givenBillId_whenInvoiceDoesNotExist_thenReturnFalse() {
    boolean invoiceExists = service.invoiceExists(invalidBillId);
    assertFalse(invoiceExists);
  }

  /*@Test
  public void givenBillId_whenNoInvoiceExists_thenCreateInvoice() {
    BillId billId = new BillId(1);
    boolean invoiceExists = service.invoiceExists(billId);

  }*/
}
