package ca.ulaval.glo4002.billing.memory;

import java.util.ArrayList;

import ca.ulaval.glo4002.billing.domain.product.Product;

public class MemoryProduct {
	private static ArrayList<Product> listProducts = new ArrayList<Product>();

	public static void saveProduct(Product product) {
		listProducts.add(product);
	}

	public static Product getProductByID(long id) throws Exception {
		for (Product product : listProducts) {
			if (product.getId() == id) {
				return product;
			}
		}
		throw new Exception("Product " + id + " not found");
	}

	public static boolean productExists(long id) {
		boolean productExist = false;
		try {
			getProductByID(id);
			productExist = true;
		} catch (Exception ex) {
			productExist = false;
		}
		return productExist;
	}
}
