package il.co.topq.testng;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.RuntimeSingleton;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class EmailableReportListener implements ITestListener {

	private static final String TEMPLATE_NAME = "report.vm";
	private static final String REPORT_NAME = "emailable-testng-report.html";

	@Override
	public void onFinish(ITestContext testngContext) {
		try {
			RuntimeServices rs = RuntimeSingleton.getRuntimeServices();
			StringReader sr = new StringReader(resourceToString(TEMPLATE_NAME));
			SimpleNode sn = rs.parse(sr, TEMPLATE_NAME);
			Template t = new Template();
			t.setRuntimeServices(rs);
			t.setData(sn);
			t.initDocument();

			VelocityContext vc = new VelocityContext();
			vc.put("context", testngContext);

			StringWriter sw = new StringWriter();
			t.merge(vc, sw);
			File file = new File(new File(testngContext.getOutputDirectory()).getParent(), REPORT_NAME);
			FileUtils.write(file, sw.toString(), "utf-16");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String resourceToString(final String resourceName) throws IOException {
		try (Scanner s = new Scanner(getClass().getClassLoader().getResourceAsStream(resourceName))) {
			s.useDelimiter("\\A");
			return s.hasNext() ? s.next() : "";
		}
	}

	//************* Unused **************
	
	@Override
	public void onTestStart(ITestResult result) {
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	}

	@Override
	public void onTestFailure(ITestResult result) {
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
	}
	
	//***********************************

}
