package framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Utility {

	public static HashMap<String, String> readPropertyToMap(String strPropertyPath) {
		HashMap<String, String> propertyData = new HashMap<>();
		FileInputStream fis;
		try {
			fis = new FileInputStream(strPropertyPath);
			Properties p = new Properties();
			p.load(fis);
			Set<Object> allkeys = p.keySet();
			for (Object key : allkeys) {
				String proval = p.getProperty(key.toString());
				propertyData.put(key.toString(), proval);
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

		return propertyData;
	}

//**********************************************************************************************************
	/**
	 * Loading Repository file
	 * 
	 * @param repFilePath
	 * @author Abubakar
	 */
	public static void loadRepository(String repFilePath) {
		try {
			File repFile = new File(repFilePath);
			if (repFile.exists()) {
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = null;
				db = dbf.newDocumentBuilder();
				Data.Common.objectRepository = db.parse(repFile);
			} else {
				System.out.println("Repository file not found" + repFilePath);
				System.exit(0);
			}

		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}

	}

//**********************************************************************************************************
	/**
	 * Getting by
	 * 
	 * @param objectName
	 * @return
	 */
	public static By getBy_from_Repository(String objectName) {

		By by = null;
		String[] propertyCol = getObjectIdentification(objectName);

		switch (propertyCol[0].toLowerCase()) {

		case "id":
			by = By.id(propertyCol[1]);
			break;

		case "name":
			by = By.name(propertyCol[1]);
			break;

		case "tagname":
			by = By.tagName(propertyCol[1]);
			break;

		case "css":
			by = By.cssSelector(propertyCol[1]);

		case "linktext":
			by = By.linkText(propertyCol[1]);

		case "xpath":
			by = By.xpath(propertyCol[1]);
			break;

		case "partiallinktext":
			by = By.partialLinkText(propertyCol[1]);
			break;

		case "class":
			by = By.className(propertyCol[1]);
			break;
		default:
			System.out.println("The locator : " + propertyCol[0] + " is invalid for the object : " + objectName
					+ " in object repository file.");
			break;
		}

		return by;

	}

//**************************************************************************************************************************
	public static String[] getObjectIdentification(String objectName) {
		String[] propCol = new String[2];
		NodeList propVal = Data.Common.objectRepository.getElementsByTagName(objectName);

		if (propVal.getLength() == 0) {
			System.out.println(
					"ERROR : The properties are not defined for object : " + objectName + " in Object Repository file");
			System.exit(0);
		} else if (propVal.getLength() > 1) {
			System.out.println("WARNING : there are multiple objects listed in Repostiroy file with same name : "
					+ objectName + " Please keep the object name (Element Name) as unque name.");
			System.exit(0);
		} else {
			Element TOElem = (Element) propVal.item(0);

			if (TOElem.hasAttribute("locator")) {
				propCol[0] = TOElem.getAttribute("locator");
			} else {
				System.out.println("ERROR : the TO Element :" + objectName
						+ " does not have locator attribute in Object Repository File");
				System.exit(0);
			}

			if (TOElem.hasAttribute("value")) {
				propCol[1] = TOElem.getAttribute("value");
			} else {
				System.out.println("ERROR : the TO Element :" + objectName
						+ " does not have value for given locator in Object Repository File");
				System.exit(0);
			}
		}

		return propCol;
	}
}
