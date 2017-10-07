package ca.ulaval.glo4002.billing.itemsManager;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ca.ulaval.glo4002.billing.application.ProductService;
import ca.ulaval.glo4002.billing.domain.submission.billFactory;

public class Cart {

	public BigDecimal total = new BigDecimal(0);
	@JsonSerialize
	private ArrayList<ItemForBill> listItems = new ArrayList<ItemForBill>();

	@JsonCreator
	public Cart() {
		super();
	}

	public void addItem(ItemForBill itemForBill) {
		listItems.add(itemForBill);
		total = new BigDecimal(total.doubleValue() + itemForBill.total().doubleValue());
	}

	public ItemForBill getItem(int indice) {
		return listItems.get(indice);
	}

	public void checkAllItems(billFactory billFactory) {
		ProductService productService = new ProductService();
		for (ItemForBill item : this.listItems) {
			item.check(productService, billFactory);
		}
	}

}
