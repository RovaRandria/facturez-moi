package ca.ulaval.glo4002.billing.repository;

import java.io.IOException;
import java.net.URL;

import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;

import ca.ulaval.glo4002.billing.domain.products.Product;
import ca.ulaval.glo4002.billing.domain.products.ProductId;
import ca.ulaval.glo4002.billing.domain.products.ProductRepository;
import ca.ulaval.glo4002.billing.dto.ProductDto;
import ca.ulaval.glo4002.billing.exceptions.NotFoundException;
import ca.ulaval.glo4002.billing.interfaces.BillingProperties;

public class CrmProductRepository implements ProductRepository {

  private static final String CRM_PRODUCTS_URL = "crmProductsUrl";
  private ObjectMapper objectMapper;

  public CrmProductRepository() {
    objectMapper = new ObjectMapper();
    objectMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  @Override
  public Product getProduct(ProductId id) {
    ProductDto productDto = null;
    try {
      URL url = new URL(BillingProperties.getInstance().getProperty(CRM_PRODUCTS_URL) + id.toString());

      productDto = objectMapper.readValue(url, ProductDto.class);
    } catch (IOException e1) {
      throw new NotFoundException(Product.class.getSimpleName(), id.toString());
    }
    return new Product(id, productDto.getName(), productDto.getPrice(), productDto.getQuantity());
  }

}
