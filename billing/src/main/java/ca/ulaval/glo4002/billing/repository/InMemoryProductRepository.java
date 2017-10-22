package ca.ulaval.glo4002.billing.repository;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import ca.ulaval.glo4002.billing.domain.products.CrmProduct;
import ca.ulaval.glo4002.billing.domain.products.ProductId;
import ca.ulaval.glo4002.billing.domain.products.ProductRepository;
import ca.ulaval.glo4002.billing.interfaces.BillingProperties;

public class InMemoryProductRepository implements ProductRepository {

	private final String CRM_PRODUCTS_URL = "crmProductsUrl";

	@Override
	public CrmProduct getProduct(ProductId id) {
		Client client = Client.create();
		WebResource resource = client
				.resource(BillingProperties.getInstance().getProperty(CRM_PRODUCTS_URL) + id.getId());
		CrmProduct crmProduct = resource.type(MediaType.APPLICATION_JSON).get(CrmProduct.class);
		// TODO - Make sure it returns null if does not exist
		return crmProduct;
	}

}
