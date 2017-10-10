package billing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import ca.ulaval.glo4002.billing.domain.submission.BillFactory;
import ca.ulaval.glo4002.billing.itemsManager.Cart;
import ca.ulaval.glo4002.billing.itemsManager.ItemForSubmission;
import ca.ulaval.glo4002.billing.memory.MemoryProduct;
import ca.ulaval.glo4002.errorManager.ErrorNegativeItemPrice;
import ca.ulaval.glo4002.errorManager.ErrorNegativeTotal;
import ca.ulaval.glo4002.errorManager.ErrorProductNotFound;
import ca.ulaval.glo4002.errorManager.ErrorStack;

public class TestItemForBill {

  private ItemForSubmission RIGHT_ITEM;
  private ItemForSubmission WRONG_PRICE_ITEM;
  private ItemForSubmission NOT_EXISTING_ITEM;
  private ItemForSubmission WPNE_ITEM;

  private String average_note = "standard";
  private ErrorStack errorlist;
  private Cart testCart;

  @Mock
  MemoryProduct memoryProduct;

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Before
  public void init() {
    errorlist = new ErrorStack();
    testCart = new Cart();

		BillFactory.memoryProduct = memoryProduct;
		Mockito.when(memoryProduct.productExists(TestV.RIGHT_ITEMID)).thenReturn(true);
		Mockito.when(memoryProduct.productExists(TestV.WRONG_ITEMID)).thenReturn(false);

    RIGHT_ITEM = new ItemForSubmission(TestV.RIGHT_PRICE, average_note, TestV.RIGHT_ITEMID, TestV.QUANTITY);
    WRONG_PRICE_ITEM = new ItemForSubmission(TestV.NEGATIVE_PRICE, average_note, TestV.RIGHT_ITEMID,
            TestV.QUANTITY);
    NOT_EXISTING_ITEM = new ItemForSubmission(TestV.RIGHT_PRICE, average_note, TestV.WRONG_ITEMID, TestV.QUANTITY);
    WPNE_ITEM = new ItemForSubmission(TestV.NEGATIVE_PRICE, average_note, TestV.WRONG_ITEMID, TestV.QUANTITY);
  }

  @Test
  public void givenItemWithSameProperties_whenAlreadyHaveOne_thenEquals() {
    assertEquals(true, RIGHT_ITEM
            .equals(new ItemForSubmission(TestV.RIGHT_PRICE, average_note, TestV.RIGHT_ITEMID, TestV.QUANTITY)));
  }

  @Test
  public void givenAnyItem_whenAlways_thenReturnRightTotal() {
    assertTrue(TestV.RIGHT_PRICE * TestV.QUANTITY == RIGHT_ITEM.total().floatValue());
  }

  @Test
  public void givenAnyItem_whenAlways_thenReturnRightPrice() {
    assertTrue(TestV.RIGHT_PRICE == RIGHT_ITEM.price().floatValue());
  }

  @Test
  public void givenRightItem_whenChecked_thenReturnTrue() {
    assertTrue(TestV.RIGHT_PRICE == RIGHT_ITEM.price().floatValue());
  }

  @Test
  public void givenRightItem_whenChecked_thenDontFillErrorList() {
    RIGHT_ITEM.check(errorlist);
    assertEquals(true, errorlist.empty());
  }

  @Test
  public void givenNotExisting_whenChecked_thenFillErrorList() {
    NOT_EXISTING_ITEM.check(errorlist);
    assertEquals(false, errorlist.empty());
  }

  @Test
  public void givenRightItem_whenGivenToCart_thenDontFillErrorList() {
    testCart.addItem(RIGHT_ITEM, errorlist);
    assertEquals(true, errorlist.empty());
  }

  @Test
  public void givenNegativePriceItem_whenGivenToCart_thenFillErrorList() {
    testCart.addItem(WRONG_PRICE_ITEM, errorlist);
    assertEquals(false, errorlist.empty());
  }

  @Test
  public void givenNotExistentItem_whenGeneralCheck_thenFillErrorList() {
    testCart.addItem(NOT_EXISTING_ITEM, errorlist);
    testCart.checkAllItems(errorlist);
    assertEquals(false, errorlist.empty());
  }

  @Test
  public void givenWPNE_whenGivenAndGeneralCheck_thenTwoErrors() {
    testCart.addItem(WPNE_ITEM, errorlist);
    testCart.checkAllItems(errorlist);
    assertEquals(true, errorlist.containsError(new ErrorNegativeItemPrice(TestV.NEGATIVE_PRICE)));
    assertEquals(true, errorlist.containsError(new ErrorProductNotFound(TestV.WRONG_ITEMID)));
  }

  @Test
  public void givenNegativePriceItem_whenCheckTotal_thenFindNTotalError() {
    testCart.addItem(WRONG_PRICE_ITEM, errorlist);
    testCart.total(errorlist);
    assertEquals(true, errorlist.containsError(new ErrorNegativeTotal(TestV.NEGATIVE_PRICE * TestV.QUANTITY)));
  }

}
