# testng-emailable-report
The purpose of the project is to create a template based emailable HTML report for the TestNG framework. 

After runing the tests, an HTML report will be generated. The report is fully ready to be embedded in the body of the email.

![example report](https://raw.githubusercontent.com/wiki/Top-Q/testng-emailable-report/report.png)

## Adding the project

Make sure you have the reposoitory included in your POM file
```xml
	<repositories>
		<repository>
			<id>Top-Q_plugin_repo</id>
			<name>Top-Q Public</name>
			<url>http://maven.top-q.co.il/content/repositories/public/</url>
		</repository>
	</repositories>
```

Add the project

```XML
<dependency>
	<groupId>il.co.topq.testng</groupId>
	<artifactId>testng-emailable-report</artifactId>
	<version>1.0.02</version>
</dependency>
```

## Usage

### Adding the reporter
Add the emailable report listener to your TestNG suite. The most useful way, in most cases, is to add it using the `@Listeners` 
TestNG annotation like shown in the following example. You can also add it to your POM file or any other acceptable method.

```Java
@Listeners(EmailableReportListener.class)
public class AbstractTestCase {

}

```

The HTML report file name is `emailable-testng-report.html` and it will be created in one of the following places depending on the method you used for running the tests. 
In case you are running using Maven it will be created in the `target/surefire-reports` or `target/failsafe-report`.
If you are running your tests from your IDE, it will be created in `test-output` folder.

### Customizing

You can select which sections of the report you would like to enable. Each title represents a single section and by default all sections are displayed. In some cases, you will want maybe only to display the results summary without the list of test cases and the statuses. 
To select the sections to display, add to your suite parameter with name `emailReportEnabledSections`. The value of the parameter is integeres (zero based), that are representing the index of the sections you want to add to the report. You can add the indexes in a comma separated list and you can also use `-` to represent index ranges. For example `0-3,5`

You can add the parameter in you `TestNG.xml` file like this:

```xml
<suite name="Emailable Report Example" verbose="1">
	<parameter name="emailReportEnabledSections" value="0-3,5"></parameter>
	<test name="First Test">
	.
	.
	.
	</test>
</suite>
```


## Extending the Report

There wouldn't be much purpose in this report if it didn't allow you to easly change it in ways that are better suited for your organization. 
The report is using the [Velocity](http://velocity.apache.org/) template engine for generating the reports. If you wish to change it, just copy the template to your `src/main/resources` folder, and change it as you see fit. 
The original template file can be found in the project `src/main/resources` folder or in this direct [link](https://github.com/Top-Q/testng-emailable-report/blob/master/src/main/resources/report.vm) to the GitHub file.

The object that is injected to the report is the actual TestNG class that implemnts the `ISuite` interface. You can find the documentation of the interface in [TestNG API](https://static.javadoc.io/org.testng/testng/6.8.17/org/testng/ISuite.html). It will probably be a good idea to take a look at the template first to see how different fields can be accessed.

Since working with the `ISuite` object can be a bit cumbersome when working with templates, the emailable report exposes another, simpler object with aggregation of data that exists in the `ISuite`. You can access the object from the template with the `$summary` reference and it includes the following properties:

```
startDate
endDate
name
passedTests
skippedTests
failedTests
failedButWithinSuccessPercentageTests
```

All the values in the summary are collected from all the suites in the `ISuite` object. Remember that suite in this context means a single TestNG test as specified in the `TestNG.xml` file.

