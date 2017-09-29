package billing;

import junit.framework.Assert;
import junit.framework.TestCase;
import memory.MemUtils;

public class TestMemUtils extends TestCase {

	public void testMemUtilsSaveAndReturn() {

		MemUtils memUtils = new MemUtils();

		byte[] test = memUtils.saveData("rekt");

		Assert.assertEquals("rekt", memUtils.returnData(test));

	}

}
