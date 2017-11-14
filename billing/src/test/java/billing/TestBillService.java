package billing;

import ca.ulaval.glo4002.billing.domain.clients.Client;
import ca.ulaval.glo4002.billing.domain.clients.ClientId;
import ca.ulaval.glo4002.billing.domain.clients.DueTerm;
import ca.ulaval.glo4002.billing.domain.products.Product;
import ca.ulaval.glo4002.billing.domain.products.ProductId;
import ca.ulaval.glo4002.billing.dto.BillDto;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class TestBillService {

  private BillService service;
  private final int GOOD_ID = 1;
  private final int BAD_ID = -1;
  private final boolean GOOD_ENTITY_FLAG = true;
  private final boolean BAD_ENTITY_FLAG = false;
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
    productDtos.add(createProductDto(GOOD_ENTITY_FLAG));

    validProductId = new ProductId(GOOD_ID);
    invalidProductId = new ProductId(BAD_ID);
    validProduct = new Product(validProductId, NAME, PRICE, QUANTITY);
    invalidProduct = null;

    validClientId = new ClientId(GOOD_ID);
    invalidClientId = new ClientId(BAD_ID);
    validClient = new Client(validClientId);
    invalidClient = null;

    Mockito.when(crmProductRepository.getProduct(validProductId)).thenReturn(validProduct);
    Mockito.when(crmProductRepository.getProduct(invalidProductId)).thenReturn(invalidProduct);
    Mockito.when(crmClientRepository.getClient(validClientId)).thenReturn(validClient);
    Mockito.when(crmClientRepository.getClient(validClientId)).thenReturn(validClient);
  }

  @Test
  public void givenClientId_whenClientExists_thenReturnClient() {
    Client returnedClient = service.getClient(validClientId);
    assertNotNull(returnedClient);
  }

  @Test
  public void givenClientId_whenClientDoesNotExists_thenReturnNull() {
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
    final DueTerm NULL_DUE_TERM = null;
    ClientId goodClientId = new ClientId(GOOD_ID);
    Client client = new Client(goodClientId, null, null, DueTerm.DAYS30, "", "", null);
    Mockito.when(crmClientRepository.getClient(goodClientId)).thenReturn(client);
    DueTerm dueTerm = service.chooseDueTerm(client.getDefaultTerm(), NULL_DUE_TERM);
    assertEquals(DueTerm.DAYS30, dueTerm);
  }

  @Test
  public void givenOrder_whenProductIsValid_thenReturnProduct() {
    Product returnedProduct = service.getProduct(validProductId);
    assertNotNull(returnedProduct);
  }

  @Test
  public void givenOrder_whenProductIsNotValid_thenReturnFalse() {
    Product returnedProduct = service.getProduct(invalidProductId);
    assertNull(returnedProduct);
  }

  @Test(expected = NegativeException.class)
  public void givenProducts_whenQuantityHasNegativeValue_thenNegativeException() {
    final int NEGATIVE_VALUE = -1;
    final boolean validDto = true;
    ProductDto productDto = createProductDto(GOOD_ENTITY_FLAG);
    productDto.setQuantity(NEGATIVE_VALUE);
    order = createOrderDto(validDto);
    order.getProductDtos().add(productDto);
    service.hasNegativeValues(order);
  }

  @Test(expected = NegativeException.class)
  public void givenProducts_whenTotalHasNegativeValue_thenNegativeException() {
    final int NEGATIVE_VALUE = -100;
    final boolean VALID_DTO_FLAG = true;
    ProductDto productDto = createProductDto(GOOD_ENTITY_FLAG);
    productDto.setPrice(new BigDecimal(NEGATIVE_VALUE));
    order = createOrderDto(VALID_DTO_FLAG);
    order.getProductDtos().add(productDto);
    service.hasNegativeValues(order);
  }

  @Test
  public void givenValidOrderDto_whenOrderIsValid_thenReturnTrue() {
    final boolean VALID_DTO_FLAG = true;
    order = createOrderDto(VALID_DTO_FLAG);
    assertTrue(service.orderIsValid(order, validClient));
  }

  @Test
  public void givenInvalidOrderDto_whenOrderIsNotValid_thenReturnFalse() {
    final boolean INVALID_DTO_FLAG = false;
    order = createOrderDto(INVALID_DTO_FLAG);
    assertFalse(service.orderIsValid(order, invalidClient));
  }

  @Test
  public void givenProductList_whenProductDoesNotExist_thenReturnFalse() {
    final List<ProductDto> PRODUCT_DTOS_WITH_INVALID = new ArrayList<>(
        Arrays.asList(createProductDto(GOOD_ENTITY_FLAG), createProductDto(BAD_ENTITY_FLAG)));

    boolean allProductDtosAreValid = service.eachProductsExist(PRODUCT_DTOS_WITH_INVALID);
    assertFalse(allProductDtosAreValid);
  }

  @Test
  public void givenValidOrderDto_whenCreate_thenBillCreationSuccess() {
    final boolean VALID_DTO_FLAG = true;
    order = createOrderDto(VALID_DTO_FLAG);
    BillDto createdBillDto = service.create(order);
    assertNotNull(createdBillDto);
  }

  private ProductDto createProductDto(boolean isValid) {
    final int QUANTITY = 1;
    final BigDecimal PRICE = new BigDecimal(1);
    final String NAME = "name";
    final ProductId PRODUCT_ID = new ProductId(isValid ? GOOD_ID : BAD_ID);
    ProductDto productDto = new ProductDto(PRICE, NAME, PRODUCT_ID, QUANTITY);
    return productDto;
  }

  private OrderDto createOrderDto(boolean isValid) {
    productDtos = new ArrayList<>();
    productDtos.add(createProductDto(GOOD_ENTITY_FLAG));
    ClientId clientId = new ClientId(isValid ? GOOD_ID : BAD_ID);
    order = new OrderDto(clientId, new Date(), DueTerm.DAYS30, productDtos);
    return order;
  }
}
