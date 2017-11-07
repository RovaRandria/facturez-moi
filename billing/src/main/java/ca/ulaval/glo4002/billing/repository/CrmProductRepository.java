package ca.ulaval.glo4002.billing.repository;

import java.io.IOException;
import java.net.URL;

import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;

import ca.ulaval.glo4002.billing.domain.products.CrmProduct;
import ca.ulaval.glo4002.billing.domain.products.ProductId;
import ca.ulaval.glo4002.billing.domain.products.ProductRepository;
import ca.ulaval.glo4002.billing.dto.ProductDto;
import ca.ulaval.glo4002.billing.interfaces.BillingProperties;

public class CrmProductRepository implements ProductRepository {

	private final String CRM_PRODUCTS_URL = "crmProductsUrl";

	@Override
	public CrmProduct getProduct(ProductId id) {
		try {
			URL url = new URL(BillingProperties.getInstance().getProperty(CRM_PRODUCTS_URL) + id.toString());

			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			ProductDto productDto = objectMapper.readValue(url, ProductDto.class);
			return new CrmProduct(id, productDto.getName(), productDto.getPrice());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return new CrmProduct(id);
	}

}
