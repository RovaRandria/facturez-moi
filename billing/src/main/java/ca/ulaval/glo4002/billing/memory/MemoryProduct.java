package ca.ulaval.glo4002.billing.memory;

import java.util.ArrayList;

import ca.ulaval.glo4002.billing.domain.product.Product;

public class MemoryProduct {
	private ArrayList<Product> listProducts = new ArrayList<Product>();

	public void saveProduct(Product product) {
		listProducts.add(product);
	}

	public ArrayList<Product> getProducts() {
		return listProducts;
	}
}
