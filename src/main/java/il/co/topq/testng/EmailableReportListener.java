package il.co.topq.testng;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

import org.apache.commons.io.FileUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.RuntimeSingleton;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class EmailableReportListener implements ISuiteListener {

	private static final String TEMPLATE_NAME = "report.vm";
	private static final String REPORT_NAME = "emailable-testng-report.html";

	@Override
	public void onFinish(ISuite suite) {
		try {
			RuntimeServices rs = RuntimeSingleton.getRuntimeServices();
			StringReader sr = new StringReader(ResourceUtils.resourceToString(TEMPLATE_NAME));
			SimpleNode sn = rs.parse(sr, TEMPLATE_NAME);
			Template t = new Template();
			t.setRuntimeServices(rs);
			t.setData(sn);
			t.initDocument();

			VelocityContext vc = new VelocityContext();
			vc.put("suite", suite);
			vc.put("summary", SuiteSummary.build(suite));

			StringWriter sw = new StringWriter();
			t.merge(vc, sw);
			File file = new File(new File(suite.getOutputDirectory()).getParent(), REPORT_NAME);
			FileUtils.write(file, sw.toString(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		suite.getResults().values().stream().forEach(s-> s.getTestContext().getPassedTests());
	}
	

	//************* Unused **************

	@Override
	public void onStart(ISuite suite) {
	}
	//***********************************

}
