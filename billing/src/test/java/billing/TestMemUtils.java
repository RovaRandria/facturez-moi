package billing;

import org.junit.Test;

import junit.framework.Assert;
import ca.ulaval.glo4002.billing.memory.MemUtils;

public class TestMemUtils {

	@Test
	public void whenSavingData_thenReturnDataReturnsTheRightData() {
		MemUtils memUtils = new MemUtils();

		final String RANDOM_DATA = "random data";

		byte[] test = memUtils.saveData(RANDOM_DATA);

		Assert.assertEquals(RANDOM_DATA, memUtils.returnData(test));
	}

}
