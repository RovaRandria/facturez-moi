package billing;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import ca.ulaval.glo4002.billing.dto.RequestBillDto;
import ca.ulaval.glo4002.billing.interfaces.rest.BillResource;

public class TestBillResource {

	@Mock
	BillResource billResource;

	@Mock
	RequestBillDto requestBillDto;

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Test
	public void givenBillResource_whenCreate_thenCreateIsCalled() {
		billResource.create(requestBillDto);
		Mockito.verify(billResource).create(requestBillDto);
	}
}
