package billing;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.ulaval.glo4002.billing.domain.bills.BillId;
import ca.ulaval.glo4002.billing.services.BillIdGenerator;

public class TestBillIdGenerator {

	@Test
	public void givenBillIdGenerator_whenCreatingNewId_thenNewIdIsIncrementedByOne() {
		BillIdGenerator billIdGenerator = new BillIdGenerator();
		BillId firstId = billIdGenerator.getId();
		BillId secondId = billIdGenerator.getId();
		assertEquals(firstId.getId() + 1, secondId.getId());
	}

}
