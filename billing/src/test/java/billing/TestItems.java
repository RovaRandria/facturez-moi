package billing;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import itemsManager.Cart;
import itemsManager.ItemForBill;
import junit.framework.Assert;

public class TestItems {

	public Cart cart;
	public ItemForBill itemForBill;

	@Before
	public void init() {
		cart = new Cart();
		itemForBill = createItem();
	}

	@Test
	public void whenAddingAnItem_thenCartHasItem() {
		cart.addItem(itemForBill);
		Assert.assertEquals(cart.getItem(0), itemForBill);
	}

	@Test
	public void whenCarthHasAnItem_thenCartHasRightTotal() {
		cart.addItem(itemForBill);
		Assert.assertEquals(cart.getItem(0).total().doubleValue(), cart.total.doubleValue());
	}

	private ItemForBill createItem() {
		Random rand = new Random();

		float magicFloat = rand.nextFloat();
		String magicString = "magic";
		int magicId = rand.nextInt();
		int magicQuantity = rand.nextInt();

		return new ItemForBill(magicFloat, magicString, magicId, magicQuantity);
	}
}
