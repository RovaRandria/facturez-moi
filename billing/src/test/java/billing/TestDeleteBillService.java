package billing;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import ca.ulaval.glo4002.billing.domain.bills.Bill;
import ca.ulaval.glo4002.billing.domain.bills.BillId;
import ca.ulaval.glo4002.billing.repository.HibernateBillRepository;
import ca.ulaval.glo4002.billing.services.DeleteBillService;

public class TestDeleteBillService {

	private static final int VALID_BILL_ID = 1;
	private static final int INVALID_BILL_ID = 1;

	private DeleteBillService service;
	private BillId validBillId;
	private BillId invalidBillId;

	@Mock
	private HibernateBillRepository billRepository;

	@Mock
	private Bill existing_bill;

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Before
	public void init() {
		service = new DeleteBillService(billRepository);
		validBillId = new BillId(VALID_BILL_ID);
		invalidBillId = new BillId(INVALID_BILL_ID);
		Mockito.when(existing_bill.getBillId()).thenReturn(validBillId);
		Mockito.when(billRepository.find(validBillId)).thenReturn(existing_bill);
	}

	@Test
	public void givenBillId_whenBillExists_thenDelete() {
		service.delete(validBillId);
		Mockito.verify(billRepository).delete(existing_bill);
	}
	/*
	 * Tester le cas fail
	 * 
	 * @Test public void givenBillId_whenBillDontExists_thenDeleteFails() {
	 * service.delete(invalidBillId); Mockito.verify(billRepository).delete(bill); }
	 */
}
