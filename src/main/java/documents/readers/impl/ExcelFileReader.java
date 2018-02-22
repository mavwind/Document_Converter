package documents.readers.impl;

import documents.exceptions.FileReaderException;
import documents.readers.IFileReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelFileReader implements IFileReader {

    /**
     * Method reading Excel file
     * @param filePath file path of the Excel file to be converted
     * @return converted Excel file as List of Maps
     * @throws FileReaderException if reading Excel file is failed
     */
    @Override
    public List<Map<String, String>> read(String filePath) throws FileReaderException {
        List<Map<String, String>> result = new ArrayList<>();
        try {
            FileInputStream excelFile = new FileInputStream(new File(filePath));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);

            Iterator<Row> iterator = datatypeSheet.iterator();
            List<String> headers = getHeadersFromFile(iterator.next());

            while (iterator.hasNext()) {
                Row row = iterator.next();
                Iterator<Cell> cells = row.iterator();
                Map<String, String> rowData = new HashMap<>();
                int columnNumber = 0;
                while (cells.hasNext()) {
                    rowData.put(headers.get(columnNumber), String.valueOf(cells.next()));
                    columnNumber++;
                }
                result.add(rowData);
            }

            return result;
        } catch (IOException e) {
            throw new FileReaderException(e.getMessage(), e);
        }
    }

    /**
     *
     * @param next as specific row in file
     * @return headers as List of Strings
     */
    private List<String> getHeadersFromFile(Row next) {
        List<String> headers = new ArrayList<>();
        Iterator<Cell> cells = next.iterator();
        while (cells.hasNext()) {
            headers.add(cells.next().toString());
        }
        return headers;
    }
}
