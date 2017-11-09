package billing;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import ca.ulaval.glo4002.billing.domain.bills.Bill;
import ca.ulaval.glo4002.billing.domain.bills.BillId;
import ca.ulaval.glo4002.billing.domain.clients.ClientId;
import ca.ulaval.glo4002.billing.domain.clients.CrmDueTerm;
import ca.ulaval.glo4002.billing.dto.ProductDto;
import ca.ulaval.glo4002.billing.services.BillDtoFactory;

public class BillDtoFactoryTest {

	Bill bill;

	@Mock
	BillId billId;
	@Mock
	ClientId clientId;
	@Mock
	Date creationDate;
	CrmDueTerm dueTerm;
	List<ProductDto> productDtos;
	@Mock
	ProductDto productDto;

	private long idAverage = 1;

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Before
	public void init() {
		Mockito.when(billId.getId()).thenReturn(idAverage);
	}

	@Test
	public void testCreationBillDto() {
		BillDtoFactory billDtoFactory = new BillDtoFactory(bill);

	}

}
