package payment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import ca.ulaval.glo4002.billing.domain.accounts.Account;
import ca.ulaval.glo4002.billing.domain.accounts.AccountId;
import ca.ulaval.glo4002.billing.domain.clients.Client;
import ca.ulaval.glo4002.billing.domain.clients.ClientId;
import ca.ulaval.glo4002.billing.domain.invoices.Invoice;
import ca.ulaval.glo4002.billing.domain.payments.PaymentId;
import ca.ulaval.glo4002.billing.dto.PaymentDto;
import ca.ulaval.glo4002.billing.dto.PaymentMethodDto;
import ca.ulaval.glo4002.billing.repository.CrmClientRepository;
import ca.ulaval.glo4002.billing.repository.HibernateAccountRepository;
import ca.ulaval.glo4002.billing.repository.HibernateInvoiceRepository;
import ca.ulaval.glo4002.billing.repository.HibernatePaymentRepository;
import ca.ulaval.glo4002.billing.services.PaymentService;

public class PaymentServiceTest {

  private PaymentService paymentService;

  @Mock
  private HibernatePaymentRepository paymentRepository;

  @Mock
  private HibernateInvoiceRepository invoiceRepository;

  @Mock
  private HibernateAccountRepository accountRepository;

  @Mock
  private CrmClientRepository clientRepository;

  @Mock
  private Invoice invoice;

  private static long ACCOUNT_ID = 0;

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Before
  public void init() {
    this.paymentService = new PaymentService(paymentRepository, invoiceRepository, clientRepository, accountRepository);
    Mockito.when(paymentRepository.insert(Mockito.any())).thenReturn(new PaymentId(0));
    Mockito.when(invoiceRepository.findLast(Mockito.any())).thenReturn(new Invoice());
    Mockito.when(clientRepository.getClient(Mockito.any())).thenReturn(new Client());
    Mockito.when(accountRepository.find(new AccountId(ACCOUNT_ID))).thenReturn(new Account());
  }

  @Test
  public void givenPaymentService_whenPay_thenInsertIsCalled() {
    final ClientId CLIENT_ID = new ClientId(0);
    final float AMOUNT = 0;
    final String EMPTY_STRING = "";
    final PaymentMethodDto PAYMENT_METHOD = new PaymentMethodDto(EMPTY_STRING, EMPTY_STRING);

    PaymentDto paymentDto = new PaymentDto(CLIENT_ID, AMOUNT, PAYMENT_METHOD);

    Mockito.when(invoiceRepository.findLast(paymentDto.getClientId())).thenReturn(invoice);
    paymentService.pay(paymentDto);
    Mockito.verify(paymentRepository).insert(Mockito.any());
  }
}
