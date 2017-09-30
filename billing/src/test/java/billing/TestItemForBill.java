package billing;

import java.math.BigDecimal;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import itemsManager.ItemForBill;
import junit.framework.Assert;

public class TestItemForBill {

	private float price;
	private int quantity;
	private String note;
	private int productID;

	@Before
	public void init() {
		Random rand = new Random();
		price = rand.nextFloat();
		quantity = rand.nextInt();
		note = "gg wp ez ff @ 20";
		productID = rand.nextInt();
	}

	@Test
	public void whenCreatingAnItem_thenTotalResultIsCorrect() {
		ItemForBill itemForBill = new ItemForBill(price, note, productID, quantity);
		Assert.assertEquals(itemForBill.total(), new BigDecimal(price * quantity));
	}

	@Test
	public void whenCreatingAnItem_thenItShouldBeEqualsToAnotherInstanceOfTheSameValues() {
		ItemForBill itemForBill = new ItemForBill(price, note, productID, quantity);
		ItemForBill itemForBill2 = new ItemForBill(price, note, productID, quantity);
		Assert.assertTrue(itemForBill.equals(itemForBill2));
	}

	@Test
	public void whenCreatingAnItem_thenItShouldNotBeEqualsToADifferentID() {
		ItemForBill itemForBill = new ItemForBill(price, note, productID, quantity);
		ItemForBill itemForBill2 = new ItemForBill(price, note, productID + 1, quantity);
		Assert.assertFalse(itemForBill.equals(itemForBill2));
	}

	@Test
	public void whenCreatingAnItem_thenItShouldNotBeEqualsToADifferentQuantity() {
		ItemForBill itemForBill = new ItemForBill(price, note, productID, quantity);
		ItemForBill itemForBill2 = new ItemForBill(price, note, productID, quantity + 1);
		Assert.assertFalse(itemForBill.equals(itemForBill2));
	}

	@Test
	public void whenCreatingAnItem_thenItShouldNotBeEqualsToADifferentPrice() {
		ItemForBill itemForBill = new ItemForBill(price, note, productID, quantity);
		ItemForBill itemForBill2 = new ItemForBill(price + 1, note, productID, quantity);
		Assert.assertFalse(itemForBill.equals(itemForBill2));
	}

	@Test
	public void whenCreatingAnItem_thenItShouldNotBeEqualsToADifferentNote() {
		ItemForBill itemForBill = new ItemForBill(price, note, productID, quantity);
		ItemForBill itemForBill2 = new ItemForBill(price, note + 1, productID, quantity);
		Assert.assertFalse(itemForBill.equals(itemForBill2));
	}

}
