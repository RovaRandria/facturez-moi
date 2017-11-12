package billing;

import ca.ulaval.glo4002.billing.repository.PaymentRepository;
import ca.ulaval.glo4002.billing.services.PaymentService;
import org.junit.Before;
import org.junit.Rule;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class TestPaymentService {

  private static final int VALID_CLIENT_ID = 1;

  private PaymentService paymentService;

  @Mock
  private PaymentRepository paymentRepository;

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Before
  public void init() {
    this.paymentService = new PaymentService();
  }

  /*@Test
  public void givenPaymentService_whenPaying_thenInsertIsCalled() {
    PaymentDto paymentDto = new PaymentDto();
    paymentService.pay(paymentDto);
    Mockito.verify(paymentRepository).insert(any());
  }*/
}
