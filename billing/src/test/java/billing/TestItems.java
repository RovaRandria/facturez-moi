package billing;

import java.math.BigDecimal;

import itemsManager.Cart;
import itemsManager.ItemForBill;
import junit.framework.Assert;
import junit.framework.TestCase;

public class TestItems extends TestCase {

	public void testoneCart_WhenAdd_MatchArgsAndTotal() {

		float magicFloat = (float) 5.00;
		String magicString = "rekt";
		int magicId = 3;
		int magicQuantity = 3;

		Cart cart = new Cart();

		ItemForBill itemForBill = new ItemForBill(magicFloat, magicString, magicId, magicQuantity);
		cart.addItem(itemForBill);
		ItemForBill itemForBillBack = cart.getItem(0);

		Assert.assertEquals(true, itemForBill.equals(itemForBillBack));
		Assert.assertEquals(new BigDecimal(magicFloat * magicQuantity).doubleValue(), cart.total.doubleValue());

	}

}
