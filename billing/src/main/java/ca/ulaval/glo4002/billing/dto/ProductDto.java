package ca.ulaval.glo4002.billing.dto;

import ca.ulaval.glo4002.billing.domain.products.ProductId;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class ProductDto {
	@JsonSerialize
	private float price;

	@JsonSerialize
	private String note;

	@JsonSerialize
	private ProductId productId;

	@JsonSerialize
	private int quantity;

	@JsonCreator
	public ProductDto(@JsonProperty("price") float price, @JsonProperty("note") String note,
					  @JsonProperty("productId") ProductId productId, @JsonProperty("quantity") int quantity) {
		this.price = price;
		this.note = note;
		this.productId = productId;
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public String getNote() {
		return note;
	}

	public ProductId getProductId() {
		return productId;
	}

	public int getQuantity() {
		return quantity;
	}
}
