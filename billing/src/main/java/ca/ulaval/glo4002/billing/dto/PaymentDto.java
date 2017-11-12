package ca.ulaval.glo4002.billing.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import ca.ulaval.glo4002.billing.domain.clients.ClientId;

public class PaymentDto {
	private ClientId clientId;
	private float amount;

	private PaymentMethod paymentMethod;

	public PaymentDto() {
	}

	@JsonCreator
	public PaymentDto(@JsonProperty("clientId") ClientId clientId, @JsonProperty("amount") float amount,
			@JsonProperty("paymentMethod") PaymentMethod paymentMethod) {
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

	public PaymentMethod getPaymentMethod() {
		return this.paymentMethod;
	}
}

class PaymentMethod {
	private String account;
	private String source;

	public PaymentMethod() {
	}

	@JsonCreator
	public PaymentMethod(@JsonProperty("account") String account, @JsonProperty("source") String source) {
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