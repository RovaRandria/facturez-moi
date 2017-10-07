package ca.ulaval.glo4002.billing.itemsManager;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ca.ulaval.glo4002.billing.application.ProductService;
import ca.ulaval.glo4002.billing.domain.submission.BillFactory;
import ca.ulaval.glo4002.errorManager.ErrorNegativeItemPrice;
import ca.ulaval.glo4002.errorManager.ErrorNegativeTotal;
import ca.ulaval.glo4002.errorManager.ErrorStack;

public class Cart {

	public BigDecimal total = new BigDecimal(0);
	@JsonSerialize
	private ArrayList<ItemForBill> listItems = new ArrayList<ItemForBill>();

	@JsonCreator
	public Cart() {
		super();
	}

	public void addItem(ItemForBill itemForBill, BillFactory billFactory) {
		listItems.add(itemForBill);
		if (itemForBill.price().doubleValue() < 0)
			billFactory.sendError(new ErrorNegativeItemPrice(itemForBill.price().doubleValue()));

		total = new BigDecimal(total.doubleValue() + itemForBill.total().doubleValue());
	}

	public void checkAllItems(ErrorStack errorList) {
		ProductService productService = new ProductService();
		for (ItemForBill item : this.listItems) {
			item.check(productService, errorList);
		}
	}

	public BigDecimal total(ErrorStack errorList) {
		if (this.total.doubleValue() < 0)
			errorList.addError(new ErrorNegativeTotal(this.total.doubleValue()));
		return this.total;
	}

}
