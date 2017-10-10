package billing;

import ca.ulaval.glo4002.billing.domain.IdBill;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestIdBill {

  IdBill TEST;
  long DIFFERENCE;

  @Before
  public void init() {
    TEST = new IdBill();
    DIFFERENCE = TEST.current();
    DIFFERENCE -= TEST.next();
  }

  @Test
  public void givenAnyUse_whenCallingObject_thenIncrement() {
    assertEquals(TEST.current() - DIFFERENCE, TEST.next());
  }

  @Test
  public void givenAnyUse_whenUpdating_thenIncrement() {
    long init = TEST.current();
    TEST.update();
    assertEquals(init - DIFFERENCE, TEST.current());
  }
}