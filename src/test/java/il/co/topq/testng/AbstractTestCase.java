package il.co.topq.testng;

import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;

@Ignore
@Listeners(EmailableReportListener.class)
public class AbstractTestCase {

}
