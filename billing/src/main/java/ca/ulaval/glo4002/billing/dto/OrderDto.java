package ca.ulaval.glo4002.billing.dto;

import java.util.Date;
import java.util.List;

import ca.ulaval.glo4002.billing.domain.clients.ClientId;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import ca.ulaval.glo4002.billing.domain.clients.CrmDueTerm;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class OrderDto {

	@JsonSerialize
	private ClientId clientId;

    @JsonSerialize
	private Date creationDate;

    @JsonSerialize
	private CrmDueTerm dueTerm;

    @JsonProperty("items")
	private List<ProductDto> productDtos;

	@JsonCreator
	public OrderDto(@JsonProperty("clientId") ClientId clientId, @JsonProperty("creationDate") Date creationDate,
					@JsonProperty("dueTerm") CrmDueTerm dueTerm, @JsonProperty("items") List<ProductDto> productDtos) {
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

	public CrmDueTerm getDueTerm() {
		return dueTerm;
	}

	public List<ProductDto> getProductDtos() {
		return productDtos;
	}
}
