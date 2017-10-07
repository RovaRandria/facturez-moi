package ca.ulaval.glo4002.billing.memory;

import java.util.ArrayList;

import ca.ulaval.glo4002.billing.domain.product.Product;

public class MemoryProduct {
	private static ArrayList<Product> listProducts = new ArrayList<Product>();

	public void saveProduct(Product product) {
		listProducts.add(product);
	}

	public Product getProductByID(long id) throws Exception {
		for (Product product : listProducts) {
			if (product.getId() == id) {
				return product;
			}
		}
		throw new Exception("Product " + id + " not found");
	}
}
