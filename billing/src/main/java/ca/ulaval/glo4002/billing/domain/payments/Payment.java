package ca.ulaval.glo4002.billing.domain.payments;

import ca.ulaval.glo4002.billing.domain.clients.Client;

import javax.persistence.*;

@Entity(name = "Payment")
public class Payment {

  // Same as Client for the id.
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Embedded
  @Column(name = "PAYMENT_ID")
  private PaymentId paymentId;

  @Column(name = "amount")
  private float amount;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "CLIENT_ID")
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

  public int getId() {
    return this.id;
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
