package payment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import ca.ulaval.glo4002.billing.dto.PaymentDto;
import ca.ulaval.glo4002.billing.repository.HibernatePaymentRepository;
import ca.ulaval.glo4002.billing.services.PaymentService;

public class TestPaymentService {

  private PaymentService paymentService;

  @Mock
  private HibernatePaymentRepository paymentRepository;

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Before
  public void init() {
    this.paymentService = new PaymentService();
  }

  @Test
  public void givenPaymentService_whenPaying_thenInsertIsCalled() {
    PaymentDto paymentDto = new PaymentDto();
    paymentService.pay(paymentDto);
    Mockito.verify(paymentRepository).insert(Mockito.any());
  }
}
