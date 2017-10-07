package ca.ulaval.glo4002.billing.domain.submission;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ca.ulaval.glo4002.billing.application.ClientService;
import ca.ulaval.glo4002.billing.domain.client.DueTerm;
import ca.ulaval.glo4002.billing.itemsManager.Cart;
import ca.ulaval.glo4002.billing.itemsManager.ItemForBill;

public class Submission {

	@JsonSerialize
	private long clientId;
	@JsonSerialize
	private String creationDate;
	@JsonSerialize
	private Cart items;
	@JsonSerialize
	private DueTerm dueTerm;

	@JsonCreator
	public Submission(@JsonProperty("clientId") long clientId, @JsonProperty("creationDate") String creationDate,
			@JsonProperty("dueTerm") DueTerm dueTerm, @JsonProperty("items") List<ItemForBill> items) {
		this.clientId = clientId;
		this.creationDate = creationDate;
		this.items = new Cart();
		for (ItemForBill item : items)
			this.items.addItem(item);
		this.dueTerm = dueTerm;
	}

	public long getClientId() {
		return clientId;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public DueTerm getDueTerm() {
		return dueTerm;
	}

	public void action(billFactory billFactory) {
		(new ClientService()).checkClientExists(this.clientId, billFactory);
		this.items.checkAllItems(billFactory);
		billFactory.configureBill(this);
	}

	public BigDecimal total() {
		return this.items.total;
	}
}
