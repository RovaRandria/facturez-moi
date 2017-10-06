package billing;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import ca.ulaval.glo4002.billing.itemsManager.Cart;
import ca.ulaval.glo4002.billing.itemsManager.ItemForBill;

public class TestItems {

	private Cart cart;
	private ItemForBill itemForBill;

	@Before
	public void init() {
		ObjectMapper objectMapper = new ObjectMapper();

		cart = new Cart();
		itemForBill = createItem();
		objectMapper.setVisibility(com.fasterxml.jackson.annotation.PropertyAccessor.FIELD,
				com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY);
	}

	@Test
	public void whenAddingAnItem_thenCartHasItem() {
		cart.addItem(itemForBill);
		assertEquals(cart.getItem(0), itemForBill);
	}

	@Test
	public void whenCarthHasAnItem_thenCartHasRightTotal() {
		cart.addItem(itemForBill);
		assertEquals(cart.getItem(0).total(), cart.getTotal());
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
