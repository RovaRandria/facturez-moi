package ca.ulaval.glo4002.billing.domain.payments;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PaymentId implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "PAYMENT_ID")
  private long payment;

  public PaymentId() {
    // For Hibernate. Do not use.
  }

  public PaymentId(long id) {
    this.payment = id;
  }

  @Override
  public String toString() {
    return Long.toString(payment);
  }

  public long getId() {
    return this.payment;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    PaymentId invoiceId = (PaymentId) o;

    return this.payment == invoiceId.payment;
  }

  @Override
  public int hashCode() {
    return (int) (payment ^ (payment >>> Integer.SIZE));
  }
}