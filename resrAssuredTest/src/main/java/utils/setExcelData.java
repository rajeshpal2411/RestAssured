package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class setExcelData {
	public static void toexcelsheet(Map<String, Object[]> data)
	{
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Sample sheet");
	     Set<String> keyset = data.keySet();
	    
	        int rownum = 0;
	        for (String key : keyset) {
	            Row row = sheet.createRow(rownum++);
	          //  System.out.println("key is"+key);
	            Object[] objArr = data.get(key);
	            int cellnum = 0;
	            for (Object obj : objArr) {
	                Cell cell = row.createCell(cellnum++);
	                if (obj instanceof Date) {
	                	cell.setCellValue(key);
	                	cell = row.createCell(cellnum++);
	                    cell.setCellValue((Date) obj);
	                } else if (obj instanceof Boolean) {
	                	cell.setCellValue(key);
	                	cell = row.createCell(cellnum++);
	                    cell.setCellValue((Boolean) obj);
	                } else if (obj instanceof String) {
	                	cell.setCellValue(key);
	                	cell = row.createCell(cellnum++);
	                    cell.setCellValue((String) obj);
	                } else if (obj instanceof Double) {
	                	cell.setCellValue(key);
	                	cell = row.createCell(cellnum++);
	                    cell.setCellValue((Double) obj);
	                }
	                else if (obj instanceof ArrayList<?>) {
	        	        for (Object o : (List<?>) obj) {
	        	        	cell.setCellValue(key);
	        	        	cell = row.createCell(cellnum++);
	        	        	 cell.setCellValue(String.class.cast(o));
	        	        }
	                }
	               
	            }
	            
	            try {
	                FileOutputStream out
	                        = new FileOutputStream(new File("../resrAssuredTest/testData/testData.xlsx"));
	                workbook.write(out);
	                out.close();
	              //  System.out.println("Excel written successfully..");
	            } catch (FileNotFoundException e) {
	                e.printStackTrace();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
		
		
		
	}

}
