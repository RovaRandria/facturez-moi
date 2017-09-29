package billing;

import org.junit.Test;

import ca.ulaval.glo4002.billing.dto.DueTerm;
import junit.framework.Assert;

public class TestDueTerm {

	@Test
	public void givenImmediateString_getDueTermFromStringReturnsIMMEDIATEDueTerm() {
		final String IMMEDIATE = "IMMEDIATE";
		Assert.assertEquals(DueTerm.IMMEDIATE, DueTerm.getDueTermFromString(IMMEDIATE));
	}

	@Test
	public void givenDAYS30String_getDueTermFromStringReturnsDAYS30DueTerm() {
		final String DAYS30 = "DAYS30";
		Assert.assertEquals(DueTerm.DAYS30, DueTerm.getDueTermFromString(DAYS30));
	}

	@Test
	public void givenDAYS90String_getDueTermFromStringReturnsDAYS90DueTerm() {
		final String DAYS90 = "DAYS90";
		Assert.assertEquals(DueTerm.DAYS90, DueTerm.getDueTermFromString(DAYS90));
	}

}
