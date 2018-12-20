package il.co.topq.testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SuccessPercentageTestCase extends AbstractTestCase {

	int count = 0;

	@Test(invocationCount = 5, successPercentage = 50)
	public void successPercentageTest() {
		if (++count > 3) {
			Assert.fail("fail with count > 3");
		}
	}
}
