package documents.writers.impl;

import documents.exceptions.FileWriterException;
import documents.writers.IFileWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractFileWriter implements IFileWriter {

    /**
     * Method writing CSV file.
     * @param filePath file path where converted CSV file should be saved
     * @param data previously processed data( as List of Maps) from external file
     * @throws FileWriterException if creating or saving CSV file is failed
     */
    @Override
    public void write(String filePath, List<Map<String, String>> data) throws FileWriterException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            List<String> rows = prepareRows(data);

            for (String row : rows) {
                bufferedWriter.write(row+"\n");
            }

            bufferedWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            throw new FileWriterException(e.getMessage(), e);
        }
    }

    protected abstract List<String> prepareRows(List<Map<String,String>> data);

    /**
     * Method preparing row of headers
     * @param data previously processed data( as List of Maps) from external file
     * @return headers as List of Strings
     */
    protected List<String> prepareHeaderRow(List<Map<String, String>> data) {
        List<String> headers = new ArrayList<>();
        for (String key : data.get(0).keySet()) {
            headers.add(key);
        }
        return headers;
    }




}
