package ca.ulaval.glo4002.billing.dto;

import java.util.Date;
import java.util.List;

import ca.ulaval.glo4002.billing.domain.clients.ClientID;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import ca.ulaval.glo4002.billing.domain.Item;
import ca.ulaval.glo4002.billing.domain.clients.DueTerm;

public class OrderDto {

	@JsonProperty("clientId")
	ClientID clientId;

	@JsonProperty("creationDate")
	Date creationDate;

	@JsonProperty("dueTerm")
	DueTerm dueTerm;

	@JsonProperty("items")
	List<Item> items;

	@JsonCreator
	public OrderDto(ClientID clientId, Date creationDate, DueTerm dueTerm, List<Item> items) {
		this.clientId = clientId;
		this.creationDate = creationDate;
		this.dueTerm = dueTerm;
		this.items = items;
	}
}
