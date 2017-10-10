package ca.ulaval.glo4002.billing.memory;

import ca.ulaval.glo4002.billing.domain.product.Product;

import java.util.ArrayList;

public class MemoryProduct {
  private ArrayList<Product> listProducts = new ArrayList<Product>();

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

  public boolean productExists(long id) {
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
