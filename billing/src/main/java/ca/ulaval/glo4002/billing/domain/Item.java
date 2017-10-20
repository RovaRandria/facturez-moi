package ca.ulaval.glo4002.billing.domain;

public class Item {
	private float price;
	private String note;
	private int productId;
	private int quantity;

	public Item(float price, String note, int productId, int quantity) {
		this.price = price;
		this.note = note;
		this.productId = productId;
		this.quantity = quantity;
	}
}
