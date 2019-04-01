package framework;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import framework.Data;
public class Configuration {

	@BeforeSuite
	public void beforeSuite() {
		
		Utility.loadRepository(Data.config.REPOSITORY_PATH);
	}
	
	@DataProvider
	public  Object[][] getExcelData() {
		
		Object[][] data=Excel.getExcelData(Data.config.EXCEL_TC1,"Sheet1");	
	   
		for(int i=1;i<data.length;i++) {
		
			for(int j=0;j<data.length;j++) {
	
				System.out.print(data[i][j]+"\t ");
			}
			
		}
		
		return data;
	}
}
