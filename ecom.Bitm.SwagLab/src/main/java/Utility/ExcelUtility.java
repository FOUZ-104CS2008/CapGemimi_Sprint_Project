package Utility;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

    public static String getData(String sheetName, int row, int column)
            throws Exception {

        FileInputStream fis =
                new FileInputStream(
                        "src/test/resources/Properties/TestData.xlsx");

        Workbook workbook = WorkbookFactory.create(fis);

        DataFormatter formatter = new DataFormatter();

        String data =
                formatter.formatCellValue(
                        workbook.getSheet(sheetName)
                                .getRow(row)
                                .getCell(column));

        workbook.close();

        fis.close();

        return data;
    }
}