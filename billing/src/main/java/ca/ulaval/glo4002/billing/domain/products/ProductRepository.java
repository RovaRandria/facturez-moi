package ca.ulaval.glo4002.billing.domain.products;

public interface ProductRepository {
	CrmProduct getProduct(ProductId id);
}
