# testng-emailable-report
The purpose of the project is to create a template based emailable HTML report for the TestNG framework. 

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
In case you are running using Maven it will be created in the `target/surefire-reports` or 'target/failsafe-report'.
If you are running your tests from your IDE, it will be created in 'test-output' folder.



