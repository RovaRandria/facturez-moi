package billing;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.billing.domain.bills.Bill;
import ca.ulaval.glo4002.billing.domain.bills.BillId;
import ca.ulaval.glo4002.billing.domain.clients.Client;
import ca.ulaval.glo4002.billing.domain.clients.ClientId;
import ca.ulaval.glo4002.billing.domain.clients.DueTerm;
import ca.ulaval.glo4002.billing.domain.products.Product;
import ca.ulaval.glo4002.billing.domain.products.ProductId;
import ca.ulaval.glo4002.billing.exceptions.NegativeException;

public class BillTest {

  private final int GOOD_ID = 1;
  private final BillId VALID_BILL_ID = new BillId(GOOD_ID);
  private final ClientId VALID_CLIENT_ID = new ClientId(GOOD_ID);
  private final Client VALID_CLIENT = new Client(VALID_CLIENT_ID);
  private final ProductId VALID_PRODUCT_ID = new ProductId(GOOD_ID);

  private final String PRODUCT_NAME = "name";
  private final BigDecimal VALID_PRODUCT_PRICE = new BigDecimal(1);
  private final int VALID_PRODUCT_QUANTITY = 1;

  private final Product VALID_PRODUCT = new Product(VALID_PRODUCT_ID, PRODUCT_NAME, VALID_PRODUCT_PRICE,
      VALID_PRODUCT_QUANTITY);
  private final int NEGATIVE_VALUE = -100;
  private final int NEGATIVE_QUANTITY = NEGATIVE_VALUE;
  private final BigDecimal NEGATIVE_PRICE = new BigDecimal(NEGATIVE_VALUE);

  private final Product NEGATIVE_QUANTITY_PRODUCT = new Product(VALID_PRODUCT_ID, PRODUCT_NAME, VALID_PRODUCT_PRICE,
      NEGATIVE_QUANTITY);

  private final Product NEGATIVE_TOTAL_PRODUCT = new Product(VALID_PRODUCT_ID, PRODUCT_NAME, NEGATIVE_PRICE,
      VALID_PRODUCT_QUANTITY);

  private final Date TODAY = new Date();
  private final DueTerm immediateDueTerm = DueTerm.IMMEDIATE;

  private Bill validBill;
  private Bill billWithNegativeQuantity;
  private Bill billWithNegativeTotal;

  @Before
  public void init() {
    List<Product> validproducts = new ArrayList<Product>();
    validproducts.add(VALID_PRODUCT);
    validBill = new Bill(VALID_BILL_ID, VALID_CLIENT, TODAY, immediateDueTerm, validproducts);

    List<Product> negativeQuantityProducts = new ArrayList<Product>();
    negativeQuantityProducts.add(NEGATIVE_QUANTITY_PRODUCT);
    billWithNegativeQuantity = new Bill(VALID_BILL_ID, VALID_CLIENT, TODAY, immediateDueTerm, negativeQuantityProducts);

    List<Product> negativPriceProducts = new ArrayList<Product>();
    negativPriceProducts.add(NEGATIVE_TOTAL_PRODUCT);
    billWithNegativeTotal = new Bill(VALID_BILL_ID, VALID_CLIENT, TODAY, immediateDueTerm, negativPriceProducts);
  }

  @Test
  public void givenValidBill_whenCheckingNegativeValues_thenReturnFalse() {
    assertFalse(validBill.ifHasNegativeValuesThenThrowNegativeException());
  }

  @Test(expected = NegativeException.class)
  public void givenBillWithNegativeQuantity_whenCheckingNegativeValues_thenReturnTrue() {
    assertTrue(billWithNegativeQuantity.ifHasNegativeValuesThenThrowNegativeException());
  }

  @Test(expected = NegativeException.class)
  public void givenBillWithNegativeTotal_whenCheckingNegativeValues_thenReturnTrue() {
    assertTrue(billWithNegativeTotal.ifHasNegativeValuesThenThrowNegativeException());
  }

}
