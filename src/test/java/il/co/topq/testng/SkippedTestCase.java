package il.co.topq.testng;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SkippedTestCase extends AbstractTestCase {

	@BeforeMethod
	public void failedSetup() throws Exception {
		throw new Exception("Failing the setup phase");
	}

	@Test
	public void skippedTest() {

	}

}
