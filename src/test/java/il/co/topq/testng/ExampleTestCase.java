package il.co.topq.testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ExampleTestCase extends AbstractTestCase {

	@Test
	public void testSuccess() {

	}

	@Test
	public void testFailure() {
		Assert.assertNotNull(null, "Purposed assertion failure");
	}

	@Test
	public void testError() throws Exception {
		throw new Exception("Puprposly throwing exception");
	}

	@Test
	public void oneSecondTest() throws InterruptedException {
		Thread.sleep(1000);
	}
	
	@Test
	public void longFailureSentenceMessageTest() {
		Assert.assertNotNull(null, "Long long long long long long long long Long long long long long long long long Long long long long long long long long Long long long long long long long long Long long long long long long long long Long long long long long long long long");
	}
	
	@Test
	public void longFailureWordMessageTest() {
		Assert.assertNotNull(null, "ExceptionForSomehingThatWeLikeInJavaLikeAbstractFactoryBuilderConcreteImplInterfaceListenerSingeltonPatternObjectImplSomethingAndAnotherThingAndSomeMore");
	}

}
