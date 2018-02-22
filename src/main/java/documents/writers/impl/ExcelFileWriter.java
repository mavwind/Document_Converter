package documents.writers.impl;

import documents.exceptions.FileWriterException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ExcelFileWriter extends AbstractFileWriter {
    /**
     * Method writing Excel file. It is using org.apache.poi dependency
     * @param filePath file path where converted Excel file should be saved
     * @param data previously processed data( as List of Maps) from external file
     * @throws FileWriterException if creating or saving Excel file is failed
     */
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
