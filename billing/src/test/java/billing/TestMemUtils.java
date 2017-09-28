package billing;

import ca.ulaval.glo4002.billing.domain.MemUtils;
import junit.framework.Assert;
import junit.framework.TestCase;

public class TestMemUtils extends TestCase {

	public void testMemUtilsSaveAndReturn() {

		MemUtils memUtils = new MemUtils();

		byte[] test = memUtils.saveData("rekt");

		Assert.assertEquals("rekt", memUtils.returnData(test));

	}

}
