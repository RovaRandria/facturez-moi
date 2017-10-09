package ca.ulaval.glo4002.billing.itemsManager;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ca.ulaval.glo4002.errorManager.ErrorNegativeItemPrice;
import ca.ulaval.glo4002.errorManager.ErrorNegativeTotal;
import ca.ulaval.glo4002.errorManager.ErrorStack;

public class Cart {

	public BigDecimal total = new BigDecimal(0);
	@JsonSerialize
	private ArrayList<ItemForSubmission> listItems = new ArrayList<ItemForSubmission>();

	@JsonCreator
	public Cart() {
		super();
	}

	public void addItem(ItemForSubmission itemForSubmission, ErrorStack errorList) {
		listItems.add(itemForSubmission);
		if (itemForSubmission.price().doubleValue() < 0)
			errorList.addError(new ErrorNegativeItemPrice(itemForSubmission.price().doubleValue()));

		total = new BigDecimal(total.doubleValue() + itemForSubmission.total().doubleValue());
	}

	public void checkAllItems(ErrorStack errorList) {
		for (ItemForSubmission item : this.listItems) {
			item.check(errorList);
		}
	}

	public BigDecimal total(ErrorStack errorList) {
		if (this.total.doubleValue() < 0)
			errorList.addError(new ErrorNegativeTotal(this.total.doubleValue()));
		return this.total;
	}

}
