package billing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import ca.ulaval.glo4002.billing.domain.clients.ClientId;
import ca.ulaval.glo4002.billing.domain.clients.CrmClient;
import ca.ulaval.glo4002.billing.domain.clients.CrmDueTerm;
import ca.ulaval.glo4002.billing.domain.products.CrmProduct;
import ca.ulaval.glo4002.billing.domain.products.ProductId;
import ca.ulaval.glo4002.billing.dto.ProductDto;
import ca.ulaval.glo4002.billing.repository.CrmClientRepository;
import ca.ulaval.glo4002.billing.repository.CrmProductRepository;
import ca.ulaval.glo4002.billing.repository.InMemoryBillRepository;
import ca.ulaval.glo4002.billing.services.BillService;

public class TestBillService {

	private BillService service;
	private CrmClient client;
	private final int GOOD_ID = 1;
	private final int WRONG_ID = -1;
	private List<ProductDto> productDtos;

	@Mock
	private CrmClientRepository crmClientRepository;

	@Mock
	private CrmProductRepository crmProductRepository;

	@Mock
	private InMemoryBillRepository inMemoryBillRepository;

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Before
	public void init() {
		service = new BillService(crmClientRepository, crmProductRepository, inMemoryBillRepository);
		productDtos = new ArrayList<>();
	}

	@Test
	public void givenClientId_whenClientExists_thenReturnTrue() {
		ClientId clientId = new ClientId(GOOD_ID);
		client = new CrmClient(clientId);
		Mockito.when(crmClientRepository.getClient(clientId)).thenReturn(client);
		boolean returnedValue = service.clientExists(clientId);
		assertTrue(returnedValue);
	}

	@Test
	public void givenClientId_whenClientDoesNotExists_thenReturnFalse() {
		ClientId badClientId = new ClientId(WRONG_ID);
		Mockito.when(crmClientRepository.getClient(badClientId)).thenReturn(null);
		boolean returnedValue = service.clientExists(badClientId);
		assertFalse(returnedValue);
	}

	@Test
	public void givenOrder_whenDueTermIsValid_thenReturnTrue() {
		boolean dueTerm = service.dueTermIsValid(CrmDueTerm.IMMEDIATE);
		assertTrue(dueTerm);
	}

	@Test
	public void givenOrder_whenDueTermIsNotValid_thenReturnFalse() {
		final CrmDueTerm INVALID_DUE_TERM = null;
		boolean dueTerm = service.dueTermIsValid(INVALID_DUE_TERM);
		assertFalse(dueTerm);
	}

	@Test
	public void givenOrder_whenDueTermIsAbsent_thenUseClientDueTerm() {
		ClientId goodClientId = new ClientId(GOOD_ID);
		client = new CrmClient(goodClientId, null, null, CrmDueTerm.DAYS30, "John Doe", "john.doe@example.com", null);
		Mockito.when(crmClientRepository.getClient(goodClientId)).thenReturn(client);
		CrmDueTerm clientDueTerm = service.useClientDueTerm(goodClientId);
		assertEquals(CrmDueTerm.DAYS30, clientDueTerm);
	}

	@Test
	public void givenOrder_whenProductIsValid_thenReturnTrue() {
		ProductId goodProductId = new ProductId(GOOD_ID);
		CrmProduct product = new CrmProduct(goodProductId);
		Mockito.when(crmProductRepository.getProduct(goodProductId)).thenReturn(product);
		boolean productExists = service.productExists(goodProductId);
		assertTrue(productExists);
	}

	@Test
	public void givenOrder_whenProductIsNotValid_thenReturnFalse() {
		ProductId wrongProductId = new ProductId(WRONG_ID);
		Mockito.when(crmProductRepository.getProduct(wrongProductId)).thenReturn(null);
		boolean productExists = service.productExists(wrongProductId);
		assertFalse(productExists);
	}

}
