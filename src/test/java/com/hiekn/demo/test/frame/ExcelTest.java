package com.hiekn.demo.test.frame;

import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.hiekn.demo.test.TestBase;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;


public class ExcelTest  extends TestBase {

	private static final String EXCEL_FILE_PATH = "F:\\interface.xlsx";

	@Test
	public void test() throws Exception {
		List<Row> rowList = new ArrayList<Row>();
		XSSFWorkbook wb =  new XSSFWorkbook(new FileInputStream(EXCEL_FILE_PATH));
		Sheet sheet = null;
		for(int t=0; t<wb.getNumberOfSheets();t++){
			sheet =wb.getSheetAt(t);
			int lastRowNum = sheet.getLastRowNum();
			Row row = null;
			// 循环读取
			for (int i = 0; i <= lastRowNum ; i++) {
				row = sheet.getRow(i);
				if (row != null) {
					rowList.add(row);
					System.out.println("第"+(i+1)+"行：");
					// 获取每一单元格的值
					 for (int j = 0; j < row.getLastCellNum(); j++) {
						 String value = getCellValue(row.getCell(j));
						 System.out.print(value + " | ");
					 }
					 System.out.println();
				}
			}
		}
		wb.close();
	}
	
	public static String getCellValue(Cell cell) {
		Object result = "";
		if (cell != null) {
				switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						result = cell.getStringCellValue();
						break;
					case Cell.CELL_TYPE_NUMERIC:
		//				result = cell.getNumericCellValue();
						result = new DecimalFormat("#").format(cell.getNumericCellValue());  
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						result = cell.getBooleanCellValue();
						break;
					case Cell.CELL_TYPE_FORMULA:
						result = cell.getCellFormula();
						break;
					case Cell.CELL_TYPE_ERROR:
						result = cell.getErrorCellValue();
						break;
					case Cell.CELL_TYPE_BLANK:
						break;
					default:
						break;
				}
		}
		return result.toString();
	}
}
