package framework;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Events {

	public static void enterValue(By by,String strVal,String stepName) {
		
		try{
			WebElement element=Data.Common.driver.findElement(by);
			element.clear();
			element.sendKeys(strVal);
		}catch(NoSuchElementException nse) {
			System.out.println(stepName+"No such element Given locator"+by.toString());
		}
		
	}
	
	public static void clickElement(By by,String stepName) {
		try {
			WebElement element=Data.Common.driver.findElement(by);
			element.click();
		}catch(NoSuchElementException e) {
			System.out.println(stepName+"Unable to click element"+by.toString());
		}
		
	}
	
	public static void clickElement_JSE(By by,String stepName) {
		try {
			WebElement element=Data.Common.driver.findElement(by);
			JavascriptExecutor jse=(JavascriptExecutor) Data.Common.driver;
			jse.executeScript("arguments[0].click();", element);
		}catch(NoSuchElementException e) {
			System.out.println(stepName+"Unable to click element"+by.toString());
		}
		
	}
	public static WebElement waitForElementToDisplay(By by, String stepName, int maxTime) {
		WebElement element = null;
		try {
			WebDriverWait wait = new WebDriverWait(Data.Common.driver, maxTime);
			wait.pollingEvery(Duration.ofMillis(300));
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));

		} catch (Exception e) {
			System.out.println("Element with locator : " + by.toString());
					
		}

		return element;
	}
}
