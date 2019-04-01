package script;

import org.testng.annotations.Test;

import framework.CommonMethods;
import framework.Configuration;
import framework.Data;
import framework.Utility;

public class MakeMytrip extends Configuration {

	@Test
	public void tc1() {

		Data.Common.getdata = Utility.readPropertyToMap(Data.config.PROPERTY_PATH);
		CommonMethods.launchApp(Data.Common.getdata.get("browser"), Data.Common.getdata.get("url"));
		CommonMethods.login(Data.Common.getdata.get("mobile"), Data.Common.getdata.get("password"));
	}

	@Test(dataProvider = "getExcelData")
	public void excelInfo(String BookingType, String From, String To, String Depature, String Return, int Adult,
			int child, int Infant) {

		System.out.println(BookingType + " " + From + " " + To + " " + Depature + " " + Return + " " + Adult + " "
				+ child + " " + Infant);
	}
}
