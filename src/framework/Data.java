package framework;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.w3c.dom.Document;

public class Data {

	public static class Common {

		public static WebDriver driver;
		public static String mainWindowHandle;
		public static Document objectRepository;
		public static HashMap<String,String> getdata;

	}
	public static class config{
		
		public static final String CHROME_DRIVER_PATH="Drivers\\chromedriver.exe";
		public static final String FIREFOX_DRIVER_PATH="Drivers\\geckodriver.exe";
		
		public static final String PROPERTY_PATH="config//cridentials.properties";
		public static final String REPOSITORY_PATH="Object Repository//Repository.xml";
		public static  String EXCEL_TC1="Excel\\TC1.xlsx";
		public static  String EXCEL_TC2="Excel\\TC1.xlsx";

	}
}
