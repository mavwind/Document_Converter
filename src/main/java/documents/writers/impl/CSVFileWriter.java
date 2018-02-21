package documents.writers.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CSVFileWriter extends AbstractFileWriter {

    @Override
    protected List<String> prepareRows(List<Map<String, String>> data) {
        List<String> result = new ArrayList<>();

        List<String> headers = prepareHeaderRow(data);
        String headerRow = "";
        for (String header : headers) {
            headerRow = headerRow + header + ";";
        }
        result.add(headerRow);

        for (Map<String, String> rowData : data) {
            String row = "";
            for (String header : headers) {
                row = row + rowData.get(header)+";";
            }
            result.add(row);
        }
        return result;
    }
}
