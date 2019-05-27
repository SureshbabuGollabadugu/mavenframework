package framework;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class EventMethods {
	
	
	public static WebElement waitForElement(By locator,int timeOut) {
		WebElement element;
		try {
			WebDriverWait wait = new WebDriverWait(Data.driver, timeOut);
			 element= 	wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			wait.pollingEvery(Duration.ofMillis(200));	
		} catch(Exception e) {
			element = null;
			System.out.println("element could not be located with locator: '"+locator.toString()+"' even after waiting for :"+timeOut+ " seconds.");
		}
		
		return element;
		
	}
	
	public static WebElement waitforElement_to_enable(By locator,int timeOut) {
		WebElement element;
		try {
			WebDriverWait wait = new WebDriverWait(Data.driver, timeOut);
			 element= 	wait.until(ExpectedConditions.elementToBeClickable(locator));
			wait.pollingEvery(Duration.ofMillis(200));	
		} catch(Exception e) {
			element = null;
			System.out.println("element could not be located with locator: '"+locator.toString()+"' even after waiting for :"+timeOut+ " seconds.");
		}
		
		return element;
		
	}
	
	public static boolean waitforElement_to_enable(WebElement element,int timeOut) {
		boolean isElementEnabled;
		try {
			WebDriverWait wait = new WebDriverWait(Data.driver, timeOut);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			wait.pollingEvery(Duration.ofMillis(200));
			isElementEnabled = true;
		} catch(Exception e) {
			isElementEnabled = false;
			System.out.println("element "+element.toString()+"' is not enabled even after waiting for :"+timeOut+ " seconds.");
		}
		
		return isElementEnabled;
		
	}
	
	public static boolean waitforElement_to_visible(WebElement element,int timeOut) {
		boolean isElementEnabled;
		try {
			WebDriverWait wait = new WebDriverWait(Data.driver, timeOut);
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.pollingEvery(Duration.ofMillis(200));
			isElementEnabled = true;
		} catch(Exception e) {
			isElementEnabled = false;
			System.out.println("element "+element.toString()+"' is not enabled even after waiting for :"+timeOut+ " seconds.");
		}
		
		return isElementEnabled;
		
	}
	
	

	public static WebElement waitforElement_to_visible(By by,int timeOut) {
		WebElement element;
		try {
			WebDriverWait wait = new WebDriverWait(Data.driver, timeOut);
			element =	wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			wait.pollingEvery(Duration.ofMillis(200));
		
		} catch(Exception e) {
			element = null;
			System.out.println("element "+element.toString()+"' is not enabled even after waiting for :"+timeOut+ " seconds.");
		}
		
		return element;
		
	}
	
	
	public static boolean check_element_exists(By by,int timeOut) {
		boolean isElementExists;
		if (waitForElement(by, timeOut) != null) {
			isElementExists = true;
		} else {
			isElementExists = false;
		}
		
		return isElementExists;
	}
	
	
	public static void enterValue(By by,String input) {
		WebElement element = waitForElement(by, 20);
		if (element == null) {
			Assert.assertFalse(true, "No element is found with locator:" + by.toString());
		} else {
			if (waitforElement_to_visible(element, 20)) {
				if (waitforElement_to_enable(element, 10)) {
					element.clear();
					element.sendKeys(input);
				} else {
					Assert.assertTrue(false,"Element : " + by.toString()+ " is not enaled even after waiting for 20 secconds.");
				}
			} else {
				Assert.assertTrue(false,"Element : " + by.toString()+ " is not visible even after waiting for 20 secconds.");
			}
		}
	}
	
	public static void appendValue_in_textField(By by,String input) {
		WebElement element = waitForElement(by, 20);
		if (element == null) {
			Assert.assertFalse(true, "No element is found with locator:" + by.toString());
		} else {
			if (waitforElement_to_visible(element, 20)) {
				if (waitforElement_to_enable(element, 10)) {
					element.sendKeys(input);
				} else {
					Assert.assertTrue(false,"Element : " + by.toString()+ " is not enaled even after waiting for 20 secconds.");
				}
			} else {
				Assert.assertTrue(false,"Element : " + by.toString()+ " is not visible even after waiting for 20 secconds.");
			}
		}
	}
	
	
	public static void clickButton(By by,String input) {
		WebElement element = waitForElement(by, 20);
		if (element == null) {
			Assert.assertFalse(true, "No element is found with locator:" + by.toString());
		} else {
			if (waitforElement_to_visible(element, 20)) {
				if (waitforElement_to_enable(element, 10)) {
					element.click();
				} else {
					Assert.assertTrue(false,"Element : " + by.toString()+ " is not enaled even after waiting for 20 secconds.");
				}
			} else {
				Assert.assertTrue(false,"Element : " + by.toString()+ " is not visible even after waiting for 20 secconds.");
			}
		}
	}
	
	public static void clickLink(By by,String input) {
		WebElement element = waitForElement(by, 20);
		if (element == null) {
			Assert.assertFalse(true, "No element is found with locator:" + by.toString());
		} else {
			if (waitforElement_to_visible(element, 20)) {				
					element.click();				
			} else {
				Assert.assertTrue(false,"Element : " + by.toString()+ " is not visible even after waiting for 20 secconds.");
			}
		}
	}
	
	
	public static boolean switchToFrame(String frameTitle) {
		boolean isFrameFound = false;
		try {
			Data.driver.switchTo().frame(frameTitle);
			isFrameFound = true;
		} catch (NoSuchFrameException nsf) {
			
			List<WebElement> allFrames = Data.driver.findElements(By.tagName("iframe"));
			
			for (WebElement frameElem : allFrames) {
				String frameName = frameElem.getAttribute("name");
				if (frameName != null) {
					if (frameName.equalsIgnoreCase(frameTitle)) {
						isFrameFound = true;
						Data.driver.switchTo().frame(frameElem);
						break;
					}
				} else {
					frameName = frameElem.getAttribute("id");
					if (frameName != null) {
						if (frameName.equalsIgnoreCase(frameTitle)) {
							isFrameFound = true;
							Data.driver.switchTo().frame(frameElem);
							break;
						}
					}
				}
					
			}
		}
		
		return isFrameFound;
	}


}
