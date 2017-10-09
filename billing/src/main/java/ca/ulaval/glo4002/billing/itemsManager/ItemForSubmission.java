package ca.ulaval.glo4002.billing.itemsManager;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import ca.ulaval.glo4002.billing.interfaces.rest.BillResource;
import ca.ulaval.glo4002.errorManager.ErrorProductNotFound;
import ca.ulaval.glo4002.errorManager.ErrorStack;

public class ItemForSubmission {

	private float price;
	private String note;
	private int productId;
	private int quantity;

	@JsonCreator
	public ItemForSubmission(@JsonProperty("price") float price, @JsonProperty("note") String note,
			@JsonProperty("productId") int productId, @JsonProperty("quantity") int quantity) {
		super();
		this.price = price;
		this.note = note;
		this.productId = productId;
		this.quantity = quantity;
	}

	public boolean equals(ItemForSubmission itemForSubmission) {
		return (this.note == itemForSubmission.note && this.price == itemForSubmission.price
				&& this.productId == itemForSubmission.productId && this.quantity == itemForSubmission.quantity);
	}

	public BigDecimal total() {
		return new BigDecimal(price * quantity);
	}

	public BigDecimal price() {
		return new BigDecimal(price);
	}

	public void check(ErrorStack errorList) {
		if (!BillResource.memoryProduct.productExists(this.productId)) {
			errorList.addError(new ErrorProductNotFound(this.productId));
		}
	}

}
