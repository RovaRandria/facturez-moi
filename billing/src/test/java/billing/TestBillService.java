package billing;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import ca.ulaval.glo4002.billing.domain.clients.Client;
import ca.ulaval.glo4002.billing.domain.clients.ClientId;
import ca.ulaval.glo4002.billing.domain.clients.DueTerm;
import ca.ulaval.glo4002.billing.domain.products.Product;
import ca.ulaval.glo4002.billing.domain.products.ProductId;
import ca.ulaval.glo4002.billing.dto.OrderDto;
import ca.ulaval.glo4002.billing.dto.ProductDto;
import ca.ulaval.glo4002.billing.exceptions.NegativeException;
import ca.ulaval.glo4002.billing.repository.CrmClientRepository;
import ca.ulaval.glo4002.billing.repository.CrmProductRepository;
import ca.ulaval.glo4002.billing.repository.InMemoryBillRepository;
import ca.ulaval.glo4002.billing.services.BillService;

public class TestBillService {

	private BillService service;
	private Client client;
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
		client = new Client(clientId);
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
		boolean dueTerm = service.dueTermIsValid(DueTerm.IMMEDIATE);
		assertTrue(dueTerm);
	}

	@Test
	public void givenOrder_whenDueTermIsNotValid_thenReturnFalse() {
		final DueTerm INVALID_DUE_TERM = null;
		boolean dueTerm = service.dueTermIsValid(INVALID_DUE_TERM);
		assertFalse(dueTerm);
	}

	@Test
	public void givenOrder_whenDueTermIsAbsent_thenUseClientDueTerm() {
		ClientId goodClientId = new ClientId(GOOD_ID);
		client = new Client(goodClientId, null, null, DueTerm.DAYS30, "John Doe", "john.doe@example.com", null);
		Mockito.when(crmClientRepository.getClient(goodClientId)).thenReturn(client);
		DueTerm clientDueTerm = service.useClientDueTerm(goodClientId);
		assertEquals(DueTerm.DAYS30, clientDueTerm);
	}

	@Test
	public void givenOrder_whenProductIsValid_thenReturnTrue() {
		ProductId goodProductId = new ProductId(GOOD_ID);
		Product product = new Product(goodProductId);
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

	@Test(expected = NegativeException.class)
	public void givenProducts_whenQuantityHasNegativeValue_thenNegativeExceptionIsThrown() {
		final int NEGATIVE_VALUE = -1;
		ProductDto productDto = createProductDto();
		productDto.setQuantity(NEGATIVE_VALUE);
		productDtos.add(productDto);
		OrderDto order = new OrderDto(new ClientId(GOOD_ID), new Date(), DueTerm.DAYS30, productDtos);
		service.hasNegativeValues(order);
	}

	@Test(expected = NegativeException.class)
	public void givenProducts_whenPriceHasNegativeValue_thenNegativeExceptionIsThrown() {
		final int NEGATIVE_VALUE = -1;
		ProductDto productDto = createProductDto();
		productDto.setPrice(new BigDecimal(NEGATIVE_VALUE));
		productDtos.add(productDto);
		OrderDto order = new OrderDto(new ClientId(GOOD_ID), new Date(), DueTerm.DAYS30, productDtos);
		service.hasNegativeValues(order);
	}

	private ProductDto createProductDto() {
		final int QUANTITY = 1;
		final BigDecimal PRICE = new BigDecimal(1);
		final String NAME = "name";
		final ProductId PRODUCT_ID = new ProductId(GOOD_ID);
		ProductDto productDto = new ProductDto(PRICE, NAME, PRODUCT_ID, QUANTITY);
		return productDto;
	}
}
