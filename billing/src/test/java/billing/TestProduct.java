package billing;

import ca.ulaval.glo4002.billing.domain.product.Product;
import ca.ulaval.glo4002.billing.memory.MemoryProduct;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestProduct {

  private MemoryProduct memoryProduct;
  private Product product;

  @Before
  public void init() {
    memoryProduct = new MemoryProduct();
    product = new Product(TestV.PRODUCT_ID, TestV.PRODUCT_NAME, TestV.PRODUCT_PRICE, TestV.PRODUCT_LINKS);
  }

  @Test
  public void givenAProduct_whenIsSaved_thenGetProductByIDReturnsRightProduct() {
    memoryProduct.saveProduct(product);
    Product expectedProduct;
    try {
      expectedProduct = memoryProduct.getProductByID(TestV.PRODUCT_ID);
    } catch (Exception e) {
      expectedProduct = new Product(null, null, null, null);
    }
    assertEquals(product, expectedProduct);
  }

  @Test
  public void givenAWrongProductID_whenIsSaved_thenGetProductByIDReturnsWrongProduct() {
    memoryProduct.saveProduct(product);
    Product expectedProduct;
    try {
      expectedProduct = memoryProduct.getProductByID(TestV.WRONG_PRODUCT_ID);
    } catch (Exception e) {
      expectedProduct = new Product(null, null, null, null);
    }
    assertNotEquals(product, expectedProduct);
  }

  @Test
  public void givenAProduct_whenIsSaved_thenItExists() {
    memoryProduct.saveProduct(product);
    assertTrue(memoryProduct.productExists(TestV.PRODUCT_ID));
  }

  @Test
  public void givenAWrongProductID_whenIsSaved_thenDoesNotExists() {
    memoryProduct.saveProduct(product);
    assertFalse(memoryProduct.productExists(TestV.WRONG_PRODUCT_ID));
  }
}
