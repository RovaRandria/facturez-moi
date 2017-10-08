package billing;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.billing.domain.IdBill;
import ca.ulaval.glo4002.billing.domain.client.Client;
import ca.ulaval.glo4002.billing.domain.client.DueTerm;
import ca.ulaval.glo4002.billing.domain.product.Product;
import ca.ulaval.glo4002.billing.domain.submission.BillFactory;
import ca.ulaval.glo4002.billing.dto.BillDto;
import ca.ulaval.glo4002.billing.itemsManager.ItemForBill;
import ca.ulaval.glo4002.billing.memory.MemoryClients;
import ca.ulaval.glo4002.billing.memory.MemoryProduct;
import junit.framework.Assert;

public class TestSubmission {

	private int WRONG_CLIENTID = 9999;
	private int RIGHT_CLIENTID = 1;

	private int WRONG_ITEMID = 9999;
	private int RIGHT_ITEMID = 1;

	private int QUANTITY = 10;

	private float WRONG_PRICE = -10;
	private float RIGHT_PRICE = 10;

	private String MODEL_DATE = "2017-08-21T16:59:20.142Z";

	private List<ItemForBill> NOT_AVAILABLE_ITEMS = new ArrayList<ItemForBill>();
	private List<ItemForBill> NEGATIVE_PRICE_ITEMS = new ArrayList<ItemForBill>();
	private List<ItemForBill> NNA_ITEMS = new ArrayList<ItemForBill>();
	private List<ItemForBill> RIGHT_ITEMS = new ArrayList<ItemForBill>();

	@Before
	public void init() {

		MemoryClients.saveClient(new Client(RIGHT_CLIENTID, null, null, null, null, null, null, null));
		MemoryProduct.saveProduct(new Product(RIGHT_ITEMID, null, null, null));

		ItemForBill item1 = new ItemForBill(RIGHT_PRICE, "note1", RIGHT_ITEMID, QUANTITY);
		ItemForBill item2 = new ItemForBill(WRONG_PRICE, "note2", RIGHT_ITEMID, QUANTITY);
		ItemForBill item3 = new ItemForBill(RIGHT_PRICE, "note3", WRONG_ITEMID, QUANTITY);
		ItemForBill item4 = new ItemForBill(WRONG_PRICE, "note4", WRONG_ITEMID, QUANTITY);

		RIGHT_ITEMS.add(item1);
		NOT_AVAILABLE_ITEMS.add(item3);
		NEGATIVE_PRICE_ITEMS.add(item2);
		NNA_ITEMS.add(item4);

	}

	@Test
	public void givenAllRightArguments_whenInstanciating_thenOutofFactoryGiveRightItem() throws Exception {
		BillFactory billFactory = new BillFactory(RIGHT_CLIENTID, MODEL_DATE, DueTerm.IMMEDIATE, RIGHT_ITEMS);

		BillDto billDto = new BillDto((new IdBill()).current() + 1, new BigDecimal(RIGHT_PRICE * QUANTITY),
				DueTerm.IMMEDIATE);

		Assert.assertEquals(true, billFactory.proccessing().equals(billDto));
	}
}
