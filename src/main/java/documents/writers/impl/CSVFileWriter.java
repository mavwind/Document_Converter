package documents.writers.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CSVFileWriter extends AbstractFileWriter {

    /**
     * Method creating CSV format List of Strings
     * @param data previously processed data( as List of Maps) from external file
     * @return List of Strings as CVS format rows using semicolon as separator
     */
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
