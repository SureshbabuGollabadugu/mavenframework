package framework;

import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class Configuration {
	
	@BeforeSuite
	public void beforeSuite(ITestContext ctx) {
		Data.suiteName = ctx.getCurrentXmlTest().getSuite().getName();
		Logs.initializeLog();
		UtilityMethods.killProcess();		
	}
	
	
	@AfterSuite(alwaysRun=true)
	public void afterSuite() {
		Logs.finalizeReport();
	}
	
	@BeforeTest
	public void beforeTest(ITestContext ctx) {
		Data.testName = ctx.getCurrentXmlTest().getName();
		Data.mainTest = Data.report.createTest(Data.testName);
		
	}
	
	@AfterTest
	public void afterTest() {
		
	}
	
	@Parameters("browser")
	@BeforeMethod
	public void beforeMethod(@Optional String browser, Method method) {
		Data.methodName = method.getName();
		Data.test = Data.mainTest.createNode(Data.methodName);
		
		browser = (browser==null)?"chrome":browser;
	   // CommonMethods.launchApplication(browser, url);
		
	}
	
	
}
