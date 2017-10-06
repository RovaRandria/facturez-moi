package ca.ulaval.glo4002.billing.itemsManager;

import java.math.BigDecimal;
import java.util.ArrayList;

import ca.ulaval.glo4002.billing.application.ProductService;
import ca.ulaval.glo4002.billing.domain.submission.SubmissionFactory;

public class Cart {

	public BigDecimal total = new BigDecimal(0);
	private ArrayList<ItemForBill> listItems = new ArrayList<ItemForBill>();

	public void addItem(ItemForBill itemForBill) {
		listItems.add(itemForBill);
		total = new BigDecimal(total.doubleValue() + itemForBill.total().doubleValue());
	}

	public ItemForBill getItem(int indice) {
		return listItems.get(indice);
	}

	public void checkAllItems(ProductService productService, SubmissionFactory billFactory) {
		for (ItemForBill item : this.listItems) {
			item.check(productService, billFactory);
		}

	}

}
