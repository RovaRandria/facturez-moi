package billing;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import itemsManager.Cart;
import itemsManager.ItemForBill;
import junit.framework.Assert;

public class TestItems {

	private Cart cart;
	private ItemForBill itemForBill;
	private JsonNode jsonItemForBill;

	@Before
	public void init() {
		ObjectMapper objectMapper = new ObjectMapper();

		cart = new Cart();
		itemForBill = createItem();
		objectMapper.setVisibility(com.fasterxml.jackson.annotation.PropertyAccessor.FIELD,
				com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY);
		jsonItemForBill = objectMapper.convertValue(itemForBill, JsonNode.class);
	}

	@Test
	public void whenAddingAnItem_thenCartHasItem() {
		cart.addItem(itemForBill);
		Assert.assertEquals(cart.getItem(0), itemForBill);
	}

	@Test
	public void whenAddingAJsonItem_thenCartHasItem() {
		cart.addItem(jsonItemForBill); // JSON object is transformed to ItemForBill object, so we have to compare with
										// the object and not the JSON.
		Assert.assertTrue(cart.getItem(0).equals(itemForBill));
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
