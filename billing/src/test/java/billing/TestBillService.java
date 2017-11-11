package billing;

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
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class TestBillService {

  private BillService service;
  private final int GOOD_ID = 1;
  private final int WRONG_ID = -1;
  private List<ProductDto> productDtos;

  private OrderDto order;

  private ProductId validProductId;
  private Product validProduct;

  private ProductId invalidProductId;
  private Product invalidProduct;

  private ClientId validClientId;
  private Client validClient;

  private ClientId invalidClientId;
  private Client invalidClient;

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
    final String NAME = "name";
    final BigDecimal PRICE = new BigDecimal(1);
    final int QUANTITY = 1;

    service = new BillService(crmClientRepository, crmProductRepository, inMemoryBillRepository);
    productDtos = new ArrayList<>();
    productDtos.add(createProductDto());

    validProductId = new ProductId(GOOD_ID);
    invalidProductId = new ProductId(WRONG_ID);
    validProduct = new Product(validProductId, NAME, PRICE, QUANTITY);
    invalidProduct = null;

    validClientId = new ClientId(GOOD_ID);
    invalidClientId = new ClientId(WRONG_ID);
    validClient = new Client(validClientId);
    invalidClient = null;

    Mockito.when(crmProductRepository.getProduct(validProductId)).thenReturn(validProduct);
    Mockito.when(crmProductRepository.getProduct(invalidProductId)).thenReturn(invalidProduct);
    Mockito.when(crmClientRepository.getClient(validClientId)).thenReturn(validClient);
    Mockito.when(crmClientRepository.getClient(validClientId)).thenReturn(validClient);
  }

  @Test
  public void givenClientId_whenClientExists_thenClientIsNotNull() {
    Client returnedClient = service.getClient(validClientId);
    assertNotNull(returnedClient);
  }

  @Test
  public void givenClientId_whenClientDoesNotExists_thenClientIsNull() {
    Client client = service.getClient(invalidClientId);
    assertNull(client);
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
    Client client = new Client(goodClientId, null, null, DueTerm.DAYS30, "", "", null);
    Mockito.when(crmClientRepository.getClient(goodClientId)).thenReturn(client);
    DueTerm nullDueTerm = null;
    DueTerm dueTerm = service.chooseDueTerm(client.getDefaultTerm(), nullDueTerm);
    assertEquals(DueTerm.DAYS30, dueTerm);
  }

  @Test
  public void givenOrder_whenProductIsValid_thenProductIsNotNull() {
    Product returnedProduct = service.getProduct(validProductId);
    assertNotNull(returnedProduct);
  }

  @Test
  public void givenOrder_whenProductIsNotValid_thenReturnFalse() {
    Product returnedProduct = service.getProduct(invalidProductId);
    assertNull(returnedProduct);
  }

  @Test(expected = NegativeException.class)
  public void givenProducts_whenQuantityHasNegativeValue_thenNegativeExceptionIsThrown() {
    final int NEGATIVE_VALUE = -1;
    final boolean validDto = true;
    ProductDto productDto = createProductDto();
    productDto.setQuantity(NEGATIVE_VALUE);
    order = createOrderDto(validDto);
    order.getProductDtos().add(productDto);
    service.hasNegativeValues(order);
  }

  @Test(expected = NegativeException.class)
  public void givenProducts_whenTotalHasNegativeValue_thenNegativeExceptionIsThrown() {
    final int NEGATIVE_VALUE = -100;
    final boolean validDto = true;
    ProductDto productDto = createProductDto();
    productDto.setPrice(new BigDecimal(NEGATIVE_VALUE));
    order = createOrderDto(validDto);
    order.getProductDtos().add(productDto);
    service.hasNegativeValues(order);
  }

  @Test
  public void givenValidOrderDto_whenValidating_thenReturnTrue() {
    final boolean validDto = true;
    order = createOrderDto(validDto);
    assertTrue(service.orderIsValid(order));
  }

  @Test
  public void givenUnvalidOrderDto_whenValidating_thenReturnFalse() {
    final boolean validDto = false;
    order = createOrderDto(validDto);
    assertFalse(service.orderIsValid(order));
  }

  private ProductDto createProductDto() {
    final int QUANTITY = 1;
    final BigDecimal PRICE = new BigDecimal(1);
    final String NAME = "name";
    final ProductId PRODUCT_ID = new ProductId(GOOD_ID);
    ProductDto productDto = new ProductDto(PRICE, NAME, PRODUCT_ID, QUANTITY);
    return productDto;
  }

  private OrderDto createOrderDto(boolean isValid) {
    productDtos = new ArrayList<>();
    productDtos.add(createProductDto());
    ClientId clientId = new ClientId(isValid ? GOOD_ID : WRONG_ID);
    order = new OrderDto(clientId, new Date(), DueTerm.DAYS30, productDtos);
    return order;
  }
}
