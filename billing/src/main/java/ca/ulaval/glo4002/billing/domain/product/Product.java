package ca.ulaval.glo4002.billing.domain.product;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Product {
	@JsonSerialize
	private Integer id;
	@JsonSerialize
	private String name;
	@JsonSerialize
	private BigDecimal unitPrice;
	@JsonSerialize
	private Object links;

	@JsonCreator
	public Product(@JsonProperty("id") Integer id, @JsonProperty("name") String name,
			@JsonProperty("unitPrice") BigDecimal unitPrice, @JsonProperty("_links") Object _links) {
		this.id = id;
		this.name = name;
		this.unitPrice = unitPrice;
		this.links = _links;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
}
