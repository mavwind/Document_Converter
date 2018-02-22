package documents.readers.impl;

import documents.exceptions.FileReaderException;
import documents.readers.IFileReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVFileReader implements IFileReader {

    private static final String CSV_FILE_SEPARATOR = ";";

    /**
     * Method reads CSV file and takes semicolon as separator of words
     * @param filePath file path of the CSV file to be converted
     * @return converted CSV file as List of Maps
     * @throws FileReaderException if reading CSV file is failed
     */
    @Override
    public List<Map<String, String>> read(String filePath) throws FileReaderException {
        List<Map<String, String>> result = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            String[] headers = new String[10];
            int lineCounter = 1;
            while ((line = bufferedReader.readLine()) != null && !line.isEmpty()) {
                if (lineCounter == 1) {
                    headers = line.split(CSV_FILE_SEPARATOR);
                    lineCounter++;
                } else {
                    String[] lineValues = line.split(CSV_FILE_SEPARATOR);
                    Map<String, String> row = new HashMap<>();
                    for (int i = 0; i < headers.length; i++) {
                        row.put(headers[i], lineValues[i]);
                    }
                    result.add(row);
                }
            }
        } catch (java.io.IOException e) {
            throw new FileReaderException(e.getMessage(), e.getCause());
        }
        return result;
    }
}
