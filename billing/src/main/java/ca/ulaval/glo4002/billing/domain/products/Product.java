package ca.ulaval.glo4002.billing.domain.products;

import java.math.BigDecimal;

public class Product {
	private ProductId id;
	private String name;
	private BigDecimal unitPrice;
	private int quantity;

	public Product(ProductId id) {
		this.id = id;
		this.name = "";
		this.unitPrice = new BigDecimal(0);
		this.quantity = 0;
	}

	public Product(ProductId id, String name, BigDecimal unitPrice, int quantity) {
		this.id = id;
		this.name = name;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
	}

	public ProductId getProductId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}
}
