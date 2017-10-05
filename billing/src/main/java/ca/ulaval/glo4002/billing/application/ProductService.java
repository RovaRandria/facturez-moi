package ca.ulaval.glo4002.billing.application;

import java.io.IOException;
import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import ca.ulaval.glo4002.billing.domain.product.Product;
import ca.ulaval.glo4002.billing.interfaces.Properties;
import ca.ulaval.glo4002.billing.memory.MemoryProduct;

public class ProductService {
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
		WebResource resource = client.resource(Properties.getInstance().getProperty("crmProductsUrl"));
		ClientResponse response = resource.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		if (response.getStatus() == 200) {
			String output = response.getEntity(String.class);
			String[] outputSplit = output.split("\"products\" : ");
			output = outputSplit[1].substring(0, outputSplit[1].length() - 1);
			try {
				JavaType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Product.class);
				ArrayList<Product> products = mapper.readValue(output, type);
				for (int i = 0; i < products.size(); i++) {
					memoryProducts.saveProduct(products.get(i));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private Product getProductById(long id) throws Exception {
		ArrayList<Product> products = memoryProducts.getProducts();

		for (Product product : products) {
			if (product.getId() == id) {
				return product;
			}
		}
		throw new Exception("Product not found");
	}
}
