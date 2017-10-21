package ca.ulaval.glo4002.billing.dto;

import java.util.Date;
import java.util.List;

import ca.ulaval.glo4002.billing.domain.clients.ClientId;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import ca.ulaval.glo4002.billing.domain.Item;
import ca.ulaval.glo4002.billing.domain.clients.CrmDueTerm;

public class OrderDto {

	@JsonProperty("clientId")
	private ClientId clientId;

	@JsonProperty("creationDate")
	private Date creationDate;

	@JsonProperty("dueTerm")
	private CrmDueTerm dueTerm;

	@JsonProperty("items")
	private List<Item> items;

	@JsonCreator
	public OrderDto(ClientId clientId, Date creationDate, CrmDueTerm dueTerm, List<Item> items) {
		this.clientId = clientId;
		this.creationDate = creationDate;
		this.dueTerm = dueTerm;
		this.items = items;
	}

	public ClientId getClientId() {
		return clientId;
	}

	public Date getDate() {
		return creationDate;
	}

	public CrmDueTerm getDueTerm() {
		return dueTerm;
	}

	public List<Item> getItems() {
		return items;
	}
}
