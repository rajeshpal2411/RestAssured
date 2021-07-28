package utils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;

public class GetExcel {

    public static String getData(int page, int row, int col) throws FileNotFoundException {
        String excelPath = "../resrAssuredTest/testData/testData.xlsx"; // path where the xlsx file is saved
        FileInputStream excelFile = new FileInputStream(excelPath);
        HSSFWorkbook workBook = null; // initializing it null so the exception could be caught.
        try {
            workBook = new HSSFWorkbook(excelFile);
           // HSSFSheet sheet = workBook.getSheetAt(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert workBook != null; // asserting that workbook is not empty
        Sheet sheet = workBook.getSheetAt(page); 
        // to get on the sheet which contains data
       // System.out.println("workbook sheet at 0"+workBook.getSheetAt(0).getRow(0).getCell(0));
        Cell cell = sheet.getRow(row).getCell(col); // to get on the cell from which we have to extract data

        // to typecast the cell data to string using DataFormatter
        DataFormatter formatter = new DataFormatter();
        //System.out.println("cell value is===="+formatter.formatCellValue(cell));
        return formatter.formatCellValue(cell);
      
        
    }
}