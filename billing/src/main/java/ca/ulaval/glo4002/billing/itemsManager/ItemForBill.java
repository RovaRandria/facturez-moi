package ca.ulaval.glo4002.billing.itemsManager;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemForBill {

	private float price;
	private String note;
	private int productId;
	private int quantity;

	@JsonCreator
	public ItemForBill(@JsonProperty("price") float price, @JsonProperty("note") String note,
			@JsonProperty("productId") int productId, @JsonProperty("quantity") int quantity) {
		super();
		this.price = price;
		this.note = note;
		this.productId = productId;
		this.quantity = quantity;
	}

	public boolean equals(ItemForBill itemForBill) {
		return (this.note == itemForBill.note && this.price == itemForBill.price
				&& this.productId == itemForBill.productId && this.quantity == itemForBill.quantity);
	}

	public BigDecimal total() {
		return new BigDecimal(price * quantity);
	}

}
