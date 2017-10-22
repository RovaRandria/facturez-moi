package billing;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import ca.ulaval.glo4002.billing.domain.Item;
import ca.ulaval.glo4002.billing.domain.clients.ClientId;
import ca.ulaval.glo4002.billing.domain.clients.CrmClient;
import ca.ulaval.glo4002.billing.repository.InMemoryClientRepository;
import ca.ulaval.glo4002.billing.services.BillService;

public class TestBillService {

	private BillService service;
	private CrmClient client;
	private final int GOOD_CLIENT = 1;
	private final int WRONG_CLIENT = 0;
	private List<Item> items;

	@Mock
	private InMemoryClientRepository inMemoryClientRepository;

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Before
	public void init() {
		service = new BillService(inMemoryClientRepository);
		items = new ArrayList<>();
	}

	@Test
	public void givenClientId_whenClientExists_thenReturnTrue() {
		ClientId clientId = new ClientId(GOOD_CLIENT);
		client = new CrmClient(clientId);
		Mockito.when(inMemoryClientRepository.getClient(clientId)).thenReturn(client);
		boolean returnedValue = service.clientExist(clientId);
		assertTrue(returnedValue);
	}

	@Test
	public void givenClientId_whenClientDoesNotExists_thenReturnFalse() {
		ClientId goodClientId = new ClientId(GOOD_CLIENT);
		ClientId badClientId = new ClientId(WRONG_CLIENT);
		client = new CrmClient(goodClientId);
		Mockito.when(inMemoryClientRepository.getClient(badClientId)).thenReturn(client);
		boolean returnedValue = service.clientExist(badClientId);
		assertFalse(returnedValue);
	}

	@Test
	public void givenOrder_whenGetTotal_thenPriceIsRight() {
		final int NB_ITEMS = 2;
		final int EXPECTED_TOTAL = 13;
		fillItems(NB_ITEMS);

		BigDecimal total = service.getTotal(items);
		BigDecimal expectedTotal = new BigDecimal(EXPECTED_TOTAL);
		assertEquals(total, expectedTotal);
	}

	private void fillItems(int nbItems) {
		float price = 1;
		String note = "note";
		int productId = 1;
		int quantity = 1;
		for (int i = 0; i < nbItems; i++) {
			price++;
			quantity++;
			Item item = new Item(price, note, productId, quantity);
			items.add(item);
		}
	}
}
