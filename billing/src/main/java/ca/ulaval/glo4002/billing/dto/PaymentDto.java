package ca.ulaval.glo4002.billing.dto;

import ca.ulaval.glo4002.billing.domain.clients.ClientId;

public class PaymentDto {
  private ClientId clientId;
  private float amount;

  private PaymentMethodDto paymentMethod;

  public PaymentDto() {
  }

  public PaymentDto(ClientId clientId, float amount, PaymentMethodDto paymentMethod) {
    this.clientId = clientId;
    this.amount = amount;
    this.paymentMethod = paymentMethod;
  }

  public ClientId getClientId() {
    return this.clientId;
  }

  public float getAmount() {
    return this.amount;
  }

  public PaymentMethodDto getPaymentMethod() {
    return this.paymentMethod;
  }

  public class PaymentMethodDto {
    private String account;
    private String source;

    public PaymentMethodDto() {
    }

    public PaymentMethodDto(String account, String source) {
      this.account = account;
      this.source = source;
    }

    public String getAccount() {
      return this.account;
    }

    public String getSource() {
      return this.source;
    }
  }
}
