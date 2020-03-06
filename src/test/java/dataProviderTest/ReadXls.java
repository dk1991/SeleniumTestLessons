package dataProviderTest;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadXls {

    public Object[][] readExcel() throws IOException {
        FileInputStream fls = new FileInputStream("src/test/resources/credentials.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fls);
        XSSFSheet sheet = workbook.getSheet("credentials");
        int totalRows = sheet.getLastRowNum() /* -1 */;
        int totalColumns = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] obj = new Object[totalRows][totalColumns];
        for (int i = 0; i < totalRows; i++) {
            obj[i][0] = sheet.getRow(i + 1).getCell(0).toString();
            obj[i][1] = sheet.getRow(i + 1).getCell(1).toString();
        }

        return obj;
    }

    @DataProvider(name = "LoginExcelDataProvider2")
    public Object[][] excelData() throws IOException {
        return new ReadXls().readExcel();
    }
}
