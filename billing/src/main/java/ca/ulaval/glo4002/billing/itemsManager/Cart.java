package ca.ulaval.glo4002.billing.itemsManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

import ca.ulaval.glo4002.billing.application.ProductService;
import ca.ulaval.glo4002.billing.domain.submission.SubmissionFactory;

public class Cart {

	private BigDecimal total = new BigDecimal(0);
	private ArrayList<ItemForBill> listItems = new ArrayList<ItemForBill>();

	public void addItem(ItemForBill itemForBill) {
		listItems.add(itemForBill);
		total = new BigDecimal(total.doubleValue() + itemForBill.total().doubleValue());
	}

	public ItemForBill getItem(int indice) {
		return listItems.get(indice);
	}

	public void check(ProductService productService, SubmissionFactory billFactory) {
		Iterator<ItemForBill> itItems = this.listItems.iterator();
		while (itItems.hasNext()) {
			itItems.next().check(productService, billFactory);
		}
	}

	public BigDecimal getTotal() {
		return this.total;
	}

}
