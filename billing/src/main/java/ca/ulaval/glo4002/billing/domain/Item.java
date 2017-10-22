package ca.ulaval.glo4002.billing.domain;

import ca.ulaval.glo4002.billing.domain.products.ProductId;

public class Item {
	private float price;
	private String note;
	private ProductId productId;
	private int quantity;

	public Item(float price, String note, ProductId productId, int quantity) {
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
