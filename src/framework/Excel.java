package framework;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

	public static Object[][] getExcelData(String path,String sheetName) {

		String array[][] = null;
		FileInputStream fis;
		try {
			fis = new FileInputStream(path);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheet(sheetName);
			int sheetIndex = wb.getSheetIndex(sheet);

			System.out.println("\t  " + "List of Row and columns:");
			System.out.println("\t  " + "****************************************");
			if (sheetIndex >= 0) {    
				int lastRowNum = sheet.getLastRowNum();
				array = new String[lastRowNum+1][];
				for (int rNum = 0; rNum <= lastRowNum; rNum++) {
					XSSFRow row = sheet.getRow(rNum);
					array[rNum] = new String[lastRowNum+6];

					if (row != null) {
						int lastCellNum=row.getLastCellNum();
						for (int cNum = 0; cNum < lastCellNum; cNum++) {
							XSSFCell ocell = row.getCell(cNum);
							String celldata = "";
							if (ocell != null) {
								CellType celltype = ocell.getCellTypeEnum();
								switch (celltype) {
								case STRING:
									celldata = ocell.getStringCellValue();
									break;
								case NUMERIC:
									celldata = String.valueOf((int) ocell.getNumericCellValue());
									break;
								default:
									celldata = String.valueOf(ocell.getNumericCellValue());
									break;
								}
								array[rNum][cNum] = celldata;
								System.out.print(" \t   :" + array[rNum][cNum]);
							}
						}
						System.out.println();
					} else {
						System.out.println("sheet contains empty Rows:");
					}
				}
				wb.close();
				fis.close();
			} else {
				System.out.println("sheet does't contain the workbook");
			}

//******************************************************************************************************************
			System.out.println();
			System.out.println("\t  " + "List of the Rows and Columns:");
			System.out.println("\t  " + "***************************************************************************");
			for (int rnum = 0; rnum < array.length; rnum++) {
				for (int cnum = 0; cnum < array[rnum].length; cnum++) {
					System.out.print(" \t   :" + array[rnum][cnum]);
				}
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return array;
	}
}