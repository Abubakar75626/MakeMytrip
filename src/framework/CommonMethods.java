package framework;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CommonMethods {

	public static void launchApp(String browser,String url) {
		
		switch (browser.toLowerCase()) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver",Data.config.CHROME_DRIVER_PATH);
			Data.Common.driver=new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver",Data.config.FIREFOX_DRIVER_PATH);
			Data.Common.driver=new FirefoxDriver();
		default:
			break;
			
		}
		Data.Common.driver.get(url);
		Data.Common.mainWindowHandle=Data.Common.driver.getWindowHandle();
		WebElement element=Events.waitForElementToDisplay(Utility.getBy_from_Repository("loginButton"),"click on login or create ",30);
		if(element!=null) {
			element.click();
			System.out.println("Application lunched sucessfull");
		}else {
			System.out.println("Application not lunched");
		}
		
	}
	
	public static void login(String UserName,String Password) {
		Events.enterValue(Utility.getBy_from_Repository("email"),UserName, "Enter UserName");
		Events.enterValue(Utility.getBy_from_Repository("password"),Password, "Enter Password");
		WebElement element=Events.waitForElementToDisplay(Utility.getBy_from_Repository("login"), "click on login button",30);
		JavascriptExecutor jse=(JavascriptExecutor) Data.Common.driver;
		jse.executeScript("arguments[0].click();", element);
	}
}
