package billing;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.ulaval.glo4002.billing.domain.DateManager;

public class TestDateManager {

	@Test
	public void givenStringDate_whenConvertingAndReverting_thenRemainTheSame() {
		String string = TestV.MODEL_DATE;

		DateManager.stringToDate(string);
		assertEquals(string, DateManager.dateToString(DateManager.stringToDate(string)));
	}
}
