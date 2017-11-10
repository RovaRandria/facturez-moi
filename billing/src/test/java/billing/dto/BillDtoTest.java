package billing.dto;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import ca.ulaval.glo4002.billing.domain.bills.BillId;
import ca.ulaval.glo4002.billing.domain.clients.DueTerm;
import ca.ulaval.glo4002.billing.dto.BillDto;

public class BillDtoTest {

	private long idAverage = 1;
	private BigDecimal totalAverage = new BigDecimal(1);
	private DueTerm dueTermAverage = DueTerm.IMMEDIATE;
	private String stringAverage = "THE WORLD";

	@Mock
	BillId billId;

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Before
	public void init() {
		Mockito.when(billId.getId()).thenReturn(idAverage);
	}

	@Test
	public void testCreationBillDto() {
		BillDto billDto = new BillDto(billId.toString(), totalAverage, dueTermAverage, stringAverage);
		// assertTrue(billId.getId().equals(billDto.getId()));
		assertTrue(totalAverage.equals(billDto.getTotal()));
		assertTrue(dueTermAverage.equals(billDto.getDueTerm()));
		assertTrue(stringAverage.equals(billDto.getUrl()));

	}

}
