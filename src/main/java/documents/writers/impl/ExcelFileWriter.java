package documents.writers.impl;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import documents.exceptions.FileWriterException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ExcelFileWriter extends AbstractFileWriter {
    @Override
    public void write(String filePath, List<Map<String, String>> data) throws FileWriterException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("data");
        List<String> headers = prepareHeaderRow(data);

        int rowNumber = 0;
        int columnNumber = 0;
        Row headerRow = sheet.createRow(rowNumber++);
        for (String header : headers) {
            Cell cell = headerRow.createCell(columnNumber++);
            cell.setCellValue(header);
        }

        for (Map<String, String> rowData : data) {
            Row row = sheet.createRow(rowNumber++);
            columnNumber = 0;
            for (String header : headers) {
                Cell cell = row.createCell(columnNumber++);
                cell.setCellValue(rowData.get(header));
            }
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            workbook.write(fileOutputStream);
            workbook.close();
        } catch (IOException e) {
            throw new FileWriterException(e.getMessage(), e);
        }
    }

    @Override
    protected List<String> prepareRows(List<Map<String, String>> data) {
        return null;
    }
}
