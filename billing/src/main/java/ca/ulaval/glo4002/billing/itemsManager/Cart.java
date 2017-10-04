package ca.ulaval.glo4002.billing.itemsManager;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;

public class Cart {

	public BigDecimal total = new BigDecimal(0);
	private ArrayList<ItemForBill> listItems = new ArrayList<ItemForBill>(); // ce n'est pas la structure de donnée la
																				// plus adaptée

	public void addItem(JsonNode _node) {
		float price = _node.path("price").floatValue();
		String note = _node.path("note").asText();
		int id = _node.path("productId").asInt();
		int quantity = _node.path("quantity").asInt();

		listItems.add(new ItemForBill(price, note, id, quantity));
		total = new BigDecimal(total.doubleValue() + price * quantity);
		// WARING : attention a bigdecimal, il faudrait demander des detail au prof mais
		// en regardant la javadoc j'ai vu poindre le truc bien capricieux, en tout cas
		// ce n'est pas comme ça qu'il faut faire
	}

	public void addItem(ItemForBill itemForBill) {
		listItems.add(itemForBill);
		total = new BigDecimal(total.doubleValue() + itemForBill.total().doubleValue());
	}

	public ItemForBill getItem(int indice) {
		return listItems.get(indice);
	}

}
