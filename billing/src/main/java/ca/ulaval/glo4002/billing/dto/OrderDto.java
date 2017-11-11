package ca.ulaval.glo4002.billing.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ca.ulaval.glo4002.billing.domain.clients.ClientId;
import ca.ulaval.glo4002.billing.domain.clients.DueTerm;

public class OrderDto {

	@JsonSerialize
	private ClientId clientId;

	@JsonSerialize
	private Date creationDate;

	@JsonSerialize
	private DueTerm dueTerm;

	@JsonProperty("items")
	private List<ProductDto> productDtos;

	@JsonCreator
	public OrderDto(@JsonProperty("clientId") ClientId clientId, @JsonProperty("creationDate") Date creationDate,
			@JsonProperty("dueTerm") DueTerm dueTerm, @JsonProperty("items") List<ProductDto> productDtos) {
		this.clientId = clientId;
		this.creationDate = creationDate;
		this.dueTerm = dueTerm;
		this.productDtos = productDtos;
	}

	public ClientId getClientId() {
		return clientId;
	}

	public Date getDate() {
		return creationDate;
	}

	public DueTerm getDueTerm() {
		return dueTerm;
	}

	public List<ProductDto> getProductDtos() {
		return productDtos;
	}

	public void setDueTerm(DueTerm dueTerm) {
		this.dueTerm = dueTerm;
	}
}
