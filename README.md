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
	<version>1.0.00</version>
</dependency>
```

## Usage
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


## Extending the Report

There wouldn't be much purpose in this report if it didn't allow you to easly change it in ways that are better suited for your organization. 
The report is using the [Velocity](http://velocity.apache.org/) template engine for generating the reports. If you wish to change it, just copy the template to your `src/main/resources` folder, and change it as you see fit. 
The original template file can be found in the project `src/main/resources` folder or in this direct [link](https://github.com/Top-Q/testng-emailable-report/blob/master/src/main/resources/report.vm) to the GitHub file.

The object that is injected to the report is the actual TestNG class that implemnts the `ITestContext` interface. You can find the documentation of the interface in [TestNG API](http://static.javadoc.io/org.testng/testng/6.11/org/testng/ITestContext.html). It will probably be a good idea to take a look at the template first to see how different fields can be accessed.


