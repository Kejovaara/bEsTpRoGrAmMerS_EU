package model;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class ExcelReader {


    ArrayList excelData = new ArrayList();
    private ArrayList readPuckemonRegister(int id){
        try
        {
            FileInputStream file = new FileInputStream(new File("src/main/java/model/MonRegister.xlsx"));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFRow row = sheet.getRow(id);
            excelData.add(row.getCell(1).getStringCellValue());
            excelData.add(row.getCell(2).getStringCellValue());
            excelData.add(row.getCell(3).getStringCellValue());
            excelData.add((int) row.getCell(4).getNumericCellValue());
            excelData.add((int) row.getCell(5).getNumericCellValue());
            excelData.add((int) row.getCell(6).getNumericCellValue());
            excelData.add((int) row.getCell(7).getNumericCellValue());
            excelData.add((int) row.getCell(8).getNumericCellValue());
            excelData.add((int) row.getCell(9).getNumericCellValue());
            excelData.add(row.getCell(11).getStringCellValue());

            file.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return excelData;
    }

    public ArrayList getExcelData(int id){
        return readPuckemonRegister(id);
    }


}
