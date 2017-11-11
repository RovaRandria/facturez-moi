package ca.ulaval.glo4002.billing.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ca.ulaval.glo4002.billing.domain.products.ProductId;

@JsonIgnoreProperties(value = { "links" }, ignoreUnknown = true)
public class ProductDto {

	private BigDecimal price;
	private String name;
	private ProductId productId;
	private int quantity;

	public ProductDto() {
	}

	public ProductDto(BigDecimal price, String name, ProductId productId, int quantity) {
		this.price = price;
		this.name = name;
		this.productId = productId;
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	public ProductId getProductId() {
		return productId;
	}

	public int getQuantity() {
		return quantity;
	}

}
