package utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilityClass {
    private String path;

    public ExcelUtilityClass(String path) {
        this.path = path;
    }

    public int getRowCount(String sheetName) throws IOException {
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fi)) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                return 0; // Return 0 if sheet does not exist
            }
            return sheet.getLastRowNum();
        }
    }

    public int getCellCount(String sheetName, int rowNum) throws IOException {
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fi)) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                return 0;
            }
            XSSFRow row = sheet.getRow(rowNum);
            return (row == null) ? 0 : row.getLastCellNum();
        }
    }

    public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fi)) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                return "";
            }
            XSSFRow row = sheet.getRow(rowNum);
            if (row == null) {
                return "";
            }
            XSSFCell cell = row.getCell(colNum);
            DataFormatter formatter = new DataFormatter();
            return (cell != null) ? formatter.formatCellValue(cell) : "";
        }
    }

    public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
        File file = new File(path);
        XSSFWorkbook workbook;

        // If file does not exist, create a new workbook
        if (!file.exists()) {
            workbook = new XSSFWorkbook();
            try (FileOutputStream fo = new FileOutputStream(path)) {
                workbook.write(fo);
            }
        }

        // Open the existing file
        try (FileInputStream fi = new FileInputStream(path);
             FileOutputStream fo = new FileOutputStream(path)) {
            workbook = new XSSFWorkbook(fi);
            XSSFSheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                sheet = workbook.createSheet(sheetName);
            }

            XSSFRow row = sheet.getRow(rowNum);
            if (row == null) {
                row = sheet.createRow(rowNum);
            }

            XSSFCell cell = row.getCell(colNum);
            if (cell == null) {
                cell = row.createCell(colNum);
            }

            cell.setCellValue(data);

            workbook.write(fo);
        }
    }
}
