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

	@JsonCreator
	public Product(@JsonProperty("id") Integer id, @JsonProperty("name") String name,
			@JsonProperty("unitPrice") BigDecimal unitPrice) {
		this.id = id;
		this.name = name;
		this.unitPrice = unitPrice;
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
