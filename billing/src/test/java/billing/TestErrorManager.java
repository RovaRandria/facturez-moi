package billing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.errorManager.ErrorBilling;
import ca.ulaval.glo4002.errorManager.ErrorStack;

public class TestErrorManager {

	ErrorStack ERRORLIST;
	ErrorBilling ANYERROR;
	String test = "test";

	@Before
	public void init() {
		ERRORLIST = new ErrorStack();
		ANYERROR = new ErrorBilling(test, test, test);
	}

	@Test
	public void givenAnyError_whenStackWereEmpty_thenRetunNotEmpty() {
		ERRORLIST.addError(ANYERROR);
		assertEquals(false, ERRORLIST.empty());
	}

	@Test
	public void givenNothing_whenStackWereEmpty_thenRetunEmpty() {
		assertEquals(true, ERRORLIST.empty());
	}

	@Test
	public void givenAnyError_whenStackWereEmpty_thenErrorFound() {
		ERRORLIST.addError(ANYERROR);
		assertEquals(true, ERRORLIST.containsError(ANYERROR));
	}

	@Test
	public void givenAnyError_whenStackWereEmpty_thenErrorNotSameObjectFound() {
		ERRORLIST.addError(ANYERROR);
		assertEquals(true, ERRORLIST.containsError(new ErrorBilling(test, test, test)));
	}

}