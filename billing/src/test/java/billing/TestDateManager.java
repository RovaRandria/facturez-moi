package billing;

import ca.ulaval.glo4002.billing.domain.DateManager;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestDateManager {

  @Test
  public void givenStringDate_whenConvertingAndReverting_thenRemainTheSame() {
    String string = TestV.MODEL_DATE;

    DateManager.stringToDate(string);
    assertEquals(string, DateManager.dateToString(DateManager.stringToDate(string)));
  }
}
