package billing;

import org.junit.Test;

import ca.ulaval.glo4002.billing.domain.IdBill;
import junit.framework.Assert;

public class TestIdBill {

	@Test
	public void whenNextIsCalled_thenIndexIsIncremented() {
		IdBill idBill = new IdBill();
		final int EXPECTED_INDEX = 1;
		Assert.assertEquals(idBill.next(), EXPECTED_INDEX);
	}
}
