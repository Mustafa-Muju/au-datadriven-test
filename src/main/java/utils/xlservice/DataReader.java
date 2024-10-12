package main.java.utils.xlservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataReader {
	
	private static Map<String, String> dataMap = new HashMap<String, String>();
	
	public static void XlsReader(String Excelpath, String sheetname, int rowNum) throws IOException {

		dataMap.clear();
		try {
			FileInputStream fs = new FileInputStream(new File(Excelpath));
			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet(sheetname);
			String title = "";
			String value = "";

			for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
				title = sheet.getRow(0).getCell(j).getStringCellValue();
				value = sheet.getRow(rowNum).getCell(j).getStringCellValue();
				dataMap.put(title, value);
			}
			workbook.close();
			fs.close();
		} catch (Exception e) {
			System.out.println("Error during reading input file");
			throw new Error(e.getStackTrace().toString());
		}
	}

	public static Map<String, String> hashmap() {
		return dataMap;
	}
	
	public static void main(String[] args) throws IOException {
		XlsReader(System.getProperty("user.dir") + "\\src\\main\\resources\\xlSheets\\"+"BAT"+".xlsx", "BAT", 1);
		System.out.println(dataMap.get("Scenario"));
	}

}
