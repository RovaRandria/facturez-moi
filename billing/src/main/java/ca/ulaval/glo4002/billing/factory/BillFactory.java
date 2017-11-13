package ca.ulaval.glo4002.billing.factory;

import ca.ulaval.glo4002.billing.domain.bills.Bill;
import ca.ulaval.glo4002.billing.domain.clients.Client;
import ca.ulaval.glo4002.billing.domain.products.Product;
import ca.ulaval.glo4002.billing.domain.products.ProductId;
import ca.ulaval.glo4002.billing.dto.OrderDto;
import ca.ulaval.glo4002.billing.dto.ProductDto;
import ca.ulaval.glo4002.billing.repository.BillIdGenerator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BillFactory {

  public Bill create(OrderDto order, Client client) {
    BillIdGenerator billIdGenerator = BillIdGenerator.getInstance();

    return new Bill(billIdGenerator.getId(), client, order.getDate(), order.getDueTerm(),
        createProducts(order.getProductDtos()));
  }

  private List<Product> createProducts(List<ProductDto> productDtos) {
    List<Product> products = new ArrayList<>();

    for (ProductDto productDto : productDtos) {
      ProductId productId = productDto.getProductId();
      String name = productDto.getName();
      BigDecimal unitPrice = productDto.getPrice();
      int quantity = productDto.getQuantity();

      Product product = new Product(productId, name, unitPrice, quantity);
      products.add(product);
    }

    return products;
  }

}
