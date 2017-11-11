package billing;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import ca.ulaval.glo4002.billing.domain.bills.BillId;
import ca.ulaval.glo4002.billing.dto.OrderDto;
import ca.ulaval.glo4002.billing.interfaces.rest.BillResource;

public class TestBillResource {

	private static final long BILL_ID = 1;

	@Mock
	BillResource billResource;

	@Mock
	OrderDto orderDto;

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Test
	public void givenBillResource_whenCreateBill_thenCreateIsCalled() {
		billResource.create(orderDto);
		Mockito.verify(billResource).create(orderDto);
	}

	@Test
	public void givenBillResource_whenCreateInvoice_thenCreateInvoiceIsCalled() {
		BillId billId = new BillId(BILL_ID);
		billResource.createInvoice(billId);
		Mockito.verify(billResource).createInvoice(billId);
	}
}
