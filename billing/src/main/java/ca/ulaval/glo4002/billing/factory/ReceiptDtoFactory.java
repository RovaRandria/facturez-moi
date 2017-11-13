package ca.ulaval.glo4002.billing.factory;

import ca.ulaval.glo4002.billing.domain.payments.PaymentId;
import ca.ulaval.glo4002.billing.dto.ReceiptDto;

public class ReceiptDtoFactory {

  public ReceiptDto create(PaymentId paymentId) {
    return new ReceiptDto(paymentId.toString(), "payments/" + paymentId.toString());
  }
}
