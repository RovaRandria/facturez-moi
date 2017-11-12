package ca.ulaval.glo4002.billing.domain.payments;

import ca.ulaval.glo4002.billing.domain.clients.Client;

import javax.persistence.*;

@Entity(name = "Payment")
public class Payment {
  @Id
  @EmbeddedId
  @GeneratedValue(strategy = GenerationType.AUTO)
  private PaymentId paymentId;
  @Column(name = "amount")
  private float amount;
  @JoinColumn(name = "CLIENT_ID", unique = true)
  @OneToOne
  private Client client;
  @JoinColumn(name = "PAYMENT_ID", unique = true)
  @OneToOne(cascade = CascadeType.ALL)
  private PaymentMethod paymentMethod;

  public Payment() {
  }

  public Payment(Client client, float amount, PaymentMethod paymentMethod) {
    this.paymentId = new PaymentId(0);
    this.client = client;
    this.amount = amount;
    this.paymentMethod = paymentMethod;
  }

  public PaymentId getPaymentId() {
    return this.paymentId;
  }

  public Client getClient() {
    return this.client;
  }

  public float getAmount() {
    return this.amount;
  }

  public PaymentMethod getPaymentMethod() {
    return this.paymentMethod;
  }
}
