package documents.writers.impl;

import documents.exceptions.FileWriterException;
import documents.writers.IFileWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class XMLFileWriter implements IFileWriter {

    private static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    private static final String ROOT_OPENING_TAG = "<root>";
    private static final String ROOT_CLOSING_TAG = "</root>";
    private static final String ELEMENT_OPENING_TAG = "<element>";
    private static final String ELEMENT_CLOSING_TAG = "</element>";
    private static final String TAG_LEFT_BRACKET = "<";
    private static final String TAG_RIGHT_BRACKET = ">";
    private static final String CLOSING_TAG_LEFT_BRACKET = "</";
    private static final String NEW_LINE = "\n";
    private static final String TABULATOR = "\t";
    private static final String DOUBLE_TABULATOR = "\t\t";

    @Override
    public void write(String filePath, List<Map<String, String>> data) throws FileWriterException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            String content = prepareContent(data);
            bufferedWriter.write(content);

            bufferedWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            throw new FileWriterException(e.getMessage(), e);
        }
    }

    private String prepareContent(List<Map<String, String>> data) {

        List<String> headers = new ArrayList<>();
        headers.addAll(data.get(0).keySet());

        StringBuffer content = new StringBuffer();
        content.append(XML_HEADER).append(NEW_LINE).append(ROOT_OPENING_TAG).append(NEW_LINE);
        for (Map<String, String> rowData : data) {

            content.append(TABULATOR).append(ELEMENT_OPENING_TAG).append(NEW_LINE);
            for (String header : headers) {
                content.append(DOUBLE_TABULATOR).append(TAG_LEFT_BRACKET).append(header).append(TAG_RIGHT_BRACKET)
                        .append(rowData.get(header))
                        .append(CLOSING_TAG_LEFT_BRACKET).append(header).append(TAG_RIGHT_BRACKET).append(NEW_LINE);
            }
            content.append(TABULATOR).append(ELEMENT_CLOSING_TAG).append(NEW_LINE);

        }
        content.append(ROOT_CLOSING_TAG);
        return content.toString();
    }
}
