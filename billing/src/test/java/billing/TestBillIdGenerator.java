package billing;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.ulaval.glo4002.billing.domain.bills.BillId;
import ca.ulaval.glo4002.billing.repository.BillIdGenerator;

public class TestBillIdGenerator {

	@Test
	public void givenBillIdGenerator_whenCreatingNewId_thenNewIdIsDifferent() {
		BillIdGenerator billIdGenerator = BillIdGenerator.getInstance();
		BillId firstId = billIdGenerator.getId();
		BillId secondId = billIdGenerator.getId();
		assertNotEquals(firstId.toString(), secondId.toString());
	}

}
