package billing;

import java.math.BigDecimal;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.billing.itemsManager.ItemForSubmission;
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
		ItemForSubmission itemForBill = new ItemForSubmission(price, note, productID, quantity);
		Assert.assertEquals(itemForBill.total(), new BigDecimal(price * quantity));
	}

	@Test
	public void whenCreatingAnItem_thenItShouldBeEqualsToAnotherInstanceOfTheSameValues() {
		ItemForSubmission itemForBill = new ItemForSubmission(price, note, productID, quantity);
		ItemForSubmission itemForBill2 = new ItemForSubmission(price, note, productID, quantity);
		Assert.assertTrue(itemForBill.equals(itemForBill2));
	}

	@Test
	public void whenCreatingAnItem_thenItShouldNotBeEqualsToADifferentID() {
		ItemForSubmission itemForBill = new ItemForSubmission(price, note, productID, quantity);
		ItemForSubmission itemForBill2 = new ItemForSubmission(price, note, productID + 1, quantity);
		Assert.assertFalse(itemForBill.equals(itemForBill2));
	}

	@Test
	public void whenCreatingAnItem_thenItShouldNotBeEqualsToADifferentQuantity() {
		ItemForSubmission itemForBill = new ItemForSubmission(price, note, productID, quantity);
		ItemForSubmission itemForBill2 = new ItemForSubmission(price, note, productID, quantity + 1);
		Assert.assertFalse(itemForBill.equals(itemForBill2));
	}

	@Test
	public void whenCreatingAnItem_thenItShouldNotBeEqualsToADifferentPrice() {
		ItemForSubmission itemForBill = new ItemForSubmission(price, note, productID, quantity);
		ItemForSubmission itemForBill2 = new ItemForSubmission(price + 1, note, productID, quantity);
		Assert.assertFalse(itemForBill.equals(itemForBill2));
	}

	@Test
	public void whenCreatingAnItem_thenItShouldNotBeEqualsToADifferentNote() {
		ItemForSubmission itemForBill = new ItemForSubmission(price, note, productID, quantity);
		ItemForSubmission itemForBill2 = new ItemForSubmission(price, note + 1, productID, quantity);
		Assert.assertFalse(itemForBill.equals(itemForBill2));
	}

}
