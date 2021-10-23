package services.puckemonGenerator;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.ResourceLoader;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Every Puckemon has set never changing values that the dynamic values of a puckemon (Ex speed and moveSet) are based on.
 * These are stored in an Excel document 'MonRegister.xlsx' for easy manipulation. This class function is to read that sheet.
 * @author Lukas Jigberg
 */
class ExcelReader {

    /**
     * Reads the excel document and returns a list of data of specific Puckemon depending on the ID.
     */
    private List<Object> readPuckemonRegister(int id){
        List<Object> excelData = new ArrayList<>();
        try
        {
            ClassLoader classLoader = getClass().getClassLoader();
            FileInputStream file = new FileInputStream(classLoader.getResource("MonRegister.xlsx").getFile());

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFRow row = sheet.getRow(id);

            try{
                excelData.add(row.getCell(1).getStringCellValue());
                excelData.add(row.getCell(2).getStringCellValue());
                excelData.add((int) row.getCell(3).getNumericCellValue());
                excelData.add((int) row.getCell(4).getNumericCellValue());
                excelData.add((int) row.getCell(5).getNumericCellValue());
                excelData.add((int) row.getCell(6).getNumericCellValue());
                excelData.add((int) row.getCell(7).getNumericCellValue());
                excelData.add((int) row.getCell(8).getNumericCellValue());
                excelData.add(row.getCell(10).getStringCellValue());
            }
            catch (Exception e){
                System.out.println("Puckemon ID "+id+" does not exist");
            }


            file.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return excelData;
    }

    /**
     *
     * @param id The id of the puckemon the data it wants to collect.
     * @return All the stats of the Puckemon.
     */
    public List<Object> getExcelData(int id){
        return readPuckemonRegister(id);
    }


}
