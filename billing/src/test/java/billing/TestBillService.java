package billing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import ca.ulaval.glo4002.billing.domain.clients.ClientId;
import ca.ulaval.glo4002.billing.domain.clients.CrmClient;
import ca.ulaval.glo4002.billing.repository.InMemoryClientRepository;
import ca.ulaval.glo4002.billing.services.BillService;

public class TestBillService {

	private BillService service;
	private CrmClient client;
	private final int GOOD_CLIENT = 1;
	private final int WRONG_CLIENT = 0;

	@Mock
	private InMemoryClientRepository inMemoryClientRepository;

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Before
	public void init() {
		service = new BillService(inMemoryClientRepository);
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
	public void givenClientId_whenClientExists_thenReturnFalse() {
		ClientId goodClientId = new ClientId(GOOD_CLIENT);
		ClientId badClientId = new ClientId(WRONG_CLIENT);
		client = new CrmClient(goodClientId);
		Mockito.when(inMemoryClientRepository.getClient(badClientId)).thenReturn(client);
		boolean returnedValue = service.clientExist(badClientId);
		assertFalse(returnedValue);
	}
}
