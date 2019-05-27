package framework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

import framework.Data.ResultStatus;

public class CommonMethods {

	public static void launchApplication(String browser,String url) {
		
		switch (browser.toLowerCase()) {
		case "chrome":
			Data.driver = new ChromeDriver();
			break;
			
		case "firefox":
			Data.driver = new FirefoxDriver();
			break;
			
		case "edge":
			Data.driver = new EdgeDriver();
			break;
			
		case "ie":
			Data.driver = new InternetExplorerDriver();
			break;
			
		default:
			Logs.writeReport(ResultStatus.FAIL, "Given browser : " + browser+ " is invalid.Browser is not launched.");
			Assert.assertTrue(false, "Given browser : " + browser+ " is invalid.Browser is not launched.");
			break;
		}
		
		Data.driver.manage().window().maximize();
		Data.driver.manage().timeouts().implicitlyWait(Data.IMPLICIT_TIME_OUT, TimeUnit.SECONDS);
		
		if (EventMethods.check_element_exists(By.name("username"), 20)) {
			Logs.writeReport(ResultStatus.PASS, "Application launched successfully.", true);
			Data.mainWindowHandle = Data.driver.getWindowHandle();
		} else {
			Logs.writeReport(ResultStatus.FAIL, "Application was not launched.", true);
			Assert.assertTrue(false, "Application not launched.");
		}
		
		
	}

	
	
	}
