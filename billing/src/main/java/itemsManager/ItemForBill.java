package itemsManager;

import java.math.BigDecimal;

public class ItemForBill {

	private float price;
	private String note;
	private int productId;
	private int quantity;

	public ItemForBill(float __price, String __note, int __productId, int __quantity) {
		super();
		this.price = __price;
		this.note = __note;
		this.productId = __productId;
		this.quantity = __quantity;
	}

	public boolean equals(ItemForBill itemForBill) {
		return (this.note == itemForBill.note && this.price == itemForBill.price
				&& this.productId == itemForBill.productId && this.quantity == itemForBill.quantity);
	}

	public BigDecimal total() {
		return new BigDecimal(price * quantity);
	}

}
