package ca.ulaval.glo4002.billing.domain.submission;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ca.ulaval.glo4002.billing.application.ClientService;
import ca.ulaval.glo4002.billing.application.ProductService;
import ca.ulaval.glo4002.billing.domain.client.DueTerm;
import ca.ulaval.glo4002.billing.itemsManager.ItemForBill;

public class Submission {

	@JsonSerialize
	private long clientId;
	@JsonSerialize
	private String creationDate;
	@JsonSerialize
	private List<ItemForBill> items;
	@JsonSerialize
	private DueTerm dueTerm;

	@JsonCreator
	public Submission(@JsonProperty("clientId") long clientId, @JsonProperty("creationDate") String creationDate,
			@JsonProperty("dueTerm") DueTerm dueTerm, @JsonProperty("items") List<ItemForBill> items) {
		this.clientId = clientId;
		this.creationDate = creationDate;
		this.items = items;
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

	public void action(ClientService clientService, ProductService productService, billFactory billFactory) {
		clientService.checkClientExists(this.clientId, billFactory);
		this.checkAllItems(productService, billFactory);
		billFactory.configureBill(this);

	}

	private void checkAllItems(ProductService productService, billFactory billFactory) {
		for (ItemForBill item : this.items) {
			item.check(productService, billFactory);
		}
	}

	public BigDecimal total() {
		BigDecimal sum = new BigDecimal(0);
		for (ItemForBill item : this.items) {
			sum = sum.add(item.total());
		}
		return sum;
	}
}
