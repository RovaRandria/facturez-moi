package billing;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import ca.ulaval.glo4002.billing.dto.OrderDto;
import ca.ulaval.glo4002.billing.interfaces.rest.BillResource;

public class TestBillResource {

	@Mock
	BillResource billResource;

	@Mock
	OrderDto orderDto;

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Test
	public void givenBillResource_whenCreate_thenCreateIsCalled() {
		billResource.create(orderDto);
		Mockito.verify(billResource).create(orderDto);
	}
}
