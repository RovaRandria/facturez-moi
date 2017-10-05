package ca.ulaval.glo4002.billing.domain.bill;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ca.ulaval.glo4002.billing.itemsManager.ItemForBill;

public class Submission {

	@JsonSerialize
	private long clientId;
	@JsonSerialize
	private String creationDate;
	@JsonSerialize
	private List<ItemForBill> items;
	@JsonSerialize
	private String dueTerm;

	@JsonCreator
	public Submission(@JsonProperty("clientId") long clientId, @JsonProperty("creationDate") String creationDate,
			@JsonProperty("dueTerm") String dueTerm, @JsonProperty("items") List<ItemForBill> items) {
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

	public List<ItemForBill> getItems() {
		return items;
	}

	public String getDueTerm() {
		return dueTerm;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public void setDueTerm(String dueTerm) {
		this.dueTerm = dueTerm;
	}

	public void setItems(List<ItemForBill> items) {
		this.items = items;
	}
}
