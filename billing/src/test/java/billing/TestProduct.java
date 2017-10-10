package billing;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.billing.domain.product.Product;
import ca.ulaval.glo4002.billing.memory.MemoryProduct;

public class TestProduct {

	private MemoryProduct memoryProduct;
	private Product product;

	@Before
	public void init() {
		memoryProduct = new MemoryProduct();
		product = new Product(1, "bla", new BigDecimal(2), null);
	}

	@Test
	public void givenAProduct_whenIsSaved_thenGetProductByIDReturnsRightProduct() {
		memoryProduct.saveProduct(product);
		Product expectedProduct;
		try {
			expectedProduct = memoryProduct.getProductByID(1);
		} catch (Exception e) {
			expectedProduct = new Product(null, null, null, null);
		}
		assertEquals(product, expectedProduct);
	}
}
