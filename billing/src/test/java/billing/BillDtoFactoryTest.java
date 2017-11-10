package billing;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import ca.ulaval.glo4002.billing.domain.bills.Bill;
import ca.ulaval.glo4002.billing.domain.bills.BillId;
import ca.ulaval.glo4002.billing.domain.clients.ClientId;
import ca.ulaval.glo4002.billing.domain.clients.CrmDueTerm;
import ca.ulaval.glo4002.billing.domain.products.ProductId;
import ca.ulaval.glo4002.billing.dto.BillDto;
import ca.ulaval.glo4002.billing.dto.ProductDto;
import ca.ulaval.glo4002.billing.repository.BillIdGenerator;
import ca.ulaval.glo4002.billing.services.BillDtoFactory;

public class BillDtoFactoryTest {

	Bill bill;
	CrmDueTerm dueTerm;
	List<ProductDto> productDtos;
	ProductDto productDto;
	BillDtoFactory billDtoFactory;

	private long id = 1;
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Before
	public void init() {
		final BigDecimal PRICE = new BigDecimal(5);
		final String NAME = "";
		ProductId productId = new ProductId(1);
		final int QUANTITY = 3;
		BillId billId = BillIdGenerator.getInstance().getId();
		ClientId clientId = new ClientId(id);
		Date creationDate = new Date();
		productDto = new ProductDto(PRICE, NAME, productId, QUANTITY);
		fillItems(QUANTITY);
		bill = new Bill(billId, clientId, creationDate, CrmDueTerm.DAYS30, productDtos);
		billDtoFactory = new BillDtoFactory();
	}

	@Test
	public void givenOrder_whenCreateBillDto_thenBillDtoIsValid() {
		BillDto billDto = billDtoFactory.createBillDto(bill);
		assertTrue(validDto(billDto));
	}

	@Test
	public void givenOrder_whenGetTotal_thenPriceIsRight() {
		final int EXPECTED_TOTAL = 9;
		BigDecimal expectedTotal = new BigDecimal(EXPECTED_TOTAL);
		BigDecimal total = billDtoFactory.getTotal(productDtos);
		assertEquals(expectedTotal, total);
	}

	private boolean validDto(BillDto billDto) {
		boolean dtoIsValid = true;
		BigDecimal total = billDtoFactory.getTotal(productDtos);
		if (billDto.getTotal() != total) {
			dtoIsValid = false;
		}
		if (billDto.getId() != bill.getBillId()) {
			dtoIsValid = false;
		}
		if (billDto.getDueTerm() != CrmDueTerm.DAYS30) {
			dtoIsValid = false;
		}
		if (!billDto.getUrl().equals("/bills/" + bill.getBillId().toString())) {
			dtoIsValid = false;
		}
		return dtoIsValid;
	}

	private void fillItems(int nbItems) {
		BigDecimal price = new BigDecimal(1);
		String note = "note";
		ProductId productId = new ProductId(1);
		int quantity = 1;
		productDtos = new ArrayList<>();
		for (int i = 0; i < nbItems; i++) {
			price.add(new BigDecimal(1));
			quantity++;
			ProductDto productDto = new ProductDto(price, note, productId, quantity);
			productDtos.add(productDto);
		}
	}
}
