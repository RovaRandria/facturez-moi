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
import ca.ulaval.glo4002.billing.itemsManager.ItemForSubmission;
import ca.ulaval.glo4002.billing.memory.MemoryClients;
import ca.ulaval.glo4002.billing.memory.MemoryProduct;
import ca.ulaval.glo4002.errorManager.ErrorClientNotFound;
import ca.ulaval.glo4002.errorManager.ErrorNegativeItemPrice;
import ca.ulaval.glo4002.errorManager.ErrorNegativeTotal;
import ca.ulaval.glo4002.errorManager.ErrorProductNotFound;
import ca.ulaval.glo4002.errorManager.ErrorStack;
import junit.framework.Assert;

public class TestSubmission {

	private List<ItemForSubmission> NOT_AVAILABLE_ITEMS = new ArrayList<ItemForSubmission>();
	private List<ItemForSubmission> NEGATIVE_PRICE_ITEMS = new ArrayList<ItemForSubmission>();
	private List<ItemForSubmission> NNA_ITEMS = new ArrayList<ItemForSubmission>();
	private List<ItemForSubmission> RIGHT_ITEMS = new ArrayList<ItemForSubmission>();

	@Before
	public void init() {

		MemoryClients.saveClient(new Client(TestV.RIGHT_CLIENTID, null, null, null, null, null, null, null));
		MemoryProduct.saveProduct(new Product(TestV.RIGHT_ITEMID, null, null, null));

		ItemForSubmission item1 = new ItemForSubmission(TestV.RIGHT_PRICE, "note1", TestV.RIGHT_ITEMID, TestV.QUANTITY);
		ItemForSubmission item2 = new ItemForSubmission(TestV.WRONG_PRICE, "note2", TestV.RIGHT_ITEMID, TestV.QUANTITY);
		ItemForSubmission item3 = new ItemForSubmission(TestV.RIGHT_PRICE, "note3", TestV.WRONG_ITEMID, TestV.QUANTITY);
		ItemForSubmission item4 = new ItemForSubmission(TestV.WRONG_PRICE, "note4", TestV.WRONG_ITEMID, TestV.QUANTITY);

		RIGHT_ITEMS.add(item1);
		NOT_AVAILABLE_ITEMS.add(item3);
		NEGATIVE_PRICE_ITEMS.add(item2);
		NNA_ITEMS.add(item4);

	}

	@Test
	public void givenAllRightArguments_whenInstanciating_thenOutofFactoryGiveCorrectlyManagedItem() throws Exception {
		BillFactory billFactory = new BillFactory(TestV.RIGHT_CLIENTID, TestV.MODEL_DATE, DueTerm.IMMEDIATE,
				RIGHT_ITEMS);

		BillDto billDto = new BillDto((new IdBill()).current() + 1, new BigDecimal(TestV.RIGHT_PRICE * TestV.QUANTITY),
				DueTerm.IMMEDIATE);

		Assert.assertEquals(true, billFactory.proccessing().equals(billDto));
	}

	@Test
	public void givenAllRightArguments_whenInstanciating_thenOutofFactoryGiveRightClass() throws Exception {
		BillFactory billFactory = new BillFactory(TestV.RIGHT_CLIENTID, TestV.MODEL_DATE, DueTerm.IMMEDIATE,
				RIGHT_ITEMS);

		Assert.assertEquals(true, (billFactory.wayOutFactory() instanceof BillDto));
	}

	@Test
	public void givenWrongClientID_whenInstanciating_thenOutofFactoryGiveCorrectlyManagedError() throws Exception {
		BillFactory billFactory = new BillFactory(TestV.WRONG_CLIENTID, TestV.MODEL_DATE, DueTerm.IMMEDIATE,
				RIGHT_ITEMS);

		ErrorClientNotFound errorClientNotFound = new ErrorClientNotFound(TestV.WRONG_CLIENTID);

		Assert.assertEquals(true, billFactory.errorReport().containsError(errorClientNotFound));
	}

	@Test
	public void givenWrongClientID_whenInstanciating_thenOutofFactoryGiveRightClass() throws Exception {
		BillFactory billFactory = new BillFactory(TestV.WRONG_CLIENTID, TestV.MODEL_DATE, DueTerm.IMMEDIATE,
				RIGHT_ITEMS);

		Assert.assertEquals(true, (billFactory.wayOutFactory() instanceof ErrorStack));
	}

	@Test
	public void givenWrongPriceItems_whenInstanciating_thenOutofFactoryGiveCorrectlyManagedError() throws Exception {
		BillFactory billFactory = new BillFactory(TestV.RIGHT_CLIENTID, TestV.MODEL_DATE, DueTerm.IMMEDIATE,
				NEGATIVE_PRICE_ITEMS);

		ErrorNegativeItemPrice errorNegativeItemPrice = new ErrorNegativeItemPrice(TestV.WRONG_PRICE);
		ErrorNegativeTotal errorNegativeTotal = new ErrorNegativeTotal(TestV.WRONG_PRICE * TestV.QUANTITY);

		Assert.assertEquals(true, billFactory.errorReport().containsError(errorNegativeItemPrice));
		Assert.assertEquals(true, billFactory.errorReport().containsError(errorNegativeTotal));
	}

	@Test
	public void givenWrongPriceItems_whenInstanciating_thenOutofFactoryGiveRightClass() throws Exception {
		BillFactory billFactory = new BillFactory(TestV.RIGHT_CLIENTID, TestV.MODEL_DATE, DueTerm.IMMEDIATE,
				NEGATIVE_PRICE_ITEMS);

		Assert.assertEquals(true, (billFactory.wayOutFactory() instanceof ErrorStack));
	}

	@Test
	public void givenWrongItems_whenInstanciating_thenOutofFactoryGiveCorrectlyManagedError() throws Exception {
		BillFactory billFactory = new BillFactory(TestV.RIGHT_CLIENTID, TestV.MODEL_DATE, DueTerm.IMMEDIATE,
				NOT_AVAILABLE_ITEMS);

		ErrorProductNotFound errorProductNotFound = new ErrorProductNotFound(TestV.WRONG_ITEMID);

		Assert.assertEquals(true, billFactory.errorReport().containsError(errorProductNotFound));
	}

	@Test
	public void givenWrongItems_whenInstanciating_thenOutofFactoryGiveRightClass() throws Exception {
		BillFactory billFactory = new BillFactory(TestV.RIGHT_CLIENTID, TestV.MODEL_DATE, DueTerm.IMMEDIATE,
				NOT_AVAILABLE_ITEMS);

		Assert.assertEquals(true, (billFactory.wayOutFactory() instanceof ErrorStack));
	}

	@Test
	public void givenWrongItemsWithNegativePrices_whenInstanciating_thenOutofFactoryGiveCorrectlyManagedError()
			throws Exception {
		BillFactory billFactory = new BillFactory(TestV.RIGHT_CLIENTID, TestV.MODEL_DATE, DueTerm.IMMEDIATE, NNA_ITEMS);

		ErrorProductNotFound errorProductNotFound = new ErrorProductNotFound(TestV.WRONG_ITEMID);
		ErrorNegativeItemPrice errorNegativeItemPrice = new ErrorNegativeItemPrice(TestV.WRONG_PRICE);
		ErrorNegativeTotal errorNegativeTotal = new ErrorNegativeTotal(TestV.WRONG_PRICE * TestV.QUANTITY);

		Assert.assertEquals(true, billFactory.errorReport().containsError(errorProductNotFound));
		Assert.assertEquals(true, billFactory.errorReport().containsError(errorNegativeItemPrice));
		Assert.assertEquals(true, billFactory.errorReport().containsError(errorNegativeTotal));
	}

	@Test
	public void givenWrongItemsWithNegativePrices_whenInstanciating_thenOutofFactoryGiveRightClass() throws Exception {
		BillFactory billFactory = new BillFactory(TestV.RIGHT_CLIENTID, TestV.MODEL_DATE, DueTerm.IMMEDIATE,
				NOT_AVAILABLE_ITEMS);

		Assert.assertEquals(true, (billFactory.wayOutFactory() instanceof ErrorStack));
	}
}
