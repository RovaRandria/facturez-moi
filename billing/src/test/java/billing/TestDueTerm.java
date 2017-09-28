package billing;

import ca.ulaval.glo4002.billing.dto.DueTerm;
import junit.framework.Assert;
import junit.framework.TestCase;

public class TestDueTerm extends TestCase {

	public void testDueTerm_whenGivenString_ReturnRightDueTerm() {

		Assert.assertEquals(DueTerm.IMMEDIATE, DueTerm.getDueTermFromString("IMMEDIATE"));
		Assert.assertEquals(DueTerm.DAYS30, DueTerm.getDueTermFromString("DAYS30"));
		Assert.assertEquals(DueTerm.DAYS90, DueTerm.getDueTermFromString("DAYS90"));

	}

}
