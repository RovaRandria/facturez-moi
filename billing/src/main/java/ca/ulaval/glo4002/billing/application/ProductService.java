package ca.ulaval.glo4002.billing.application;

import java.io.IOException;
import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import ca.ulaval.glo4002.billing.domain.product.Product;
import ca.ulaval.glo4002.billing.interfaces.Properties;
import ca.ulaval.glo4002.billing.memory.MemoryProduct;

public class ProductService {
	private long BEGIN = 1;
	private MemoryProduct memoryProducts;

	public ProductService() {
		memoryProducts = new MemoryProduct();
		saveProducts();
	}

	public boolean productExists(long id) {
		boolean productExist = false;
		try {
			getProductById(id);
			productExist = true;
		} catch (Exception ex) {
			productExist = false;
		}
		return productExist;
	}

	private void saveProducts() {
		ObjectMapper mapper = new ObjectMapper();
		Client client = Client.create();
		long i = this.BEGIN;
		WebResource resource = client.resource(Properties.getInstance().getProperty("crmProductsUrl") + "/" + i);
		ClientResponse response = resource.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		while (response.getStatus() == 200) {
			String output = response.getEntity(String.class);
			try {
				Product product = mapper.readValue(output, Product.class);
				memoryProducts.saveProduct(product);
			} catch (IOException e) {
				e.printStackTrace();
			}
			i++;
			resource = client.resource(Properties.getInstance().getProperty("crmProductsUrl") + "/" + i);
			response = resource.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		}
	}

	private Product getProductById(long id) throws Exception {
		ArrayList<Product> products = memoryProducts.getProducts();

		for (Product product : products) {
			if (product.getId() == id) {
				return product;
			}
		}
		throw new Exception("Product " + id + " not found");
	}
}
