package documents.writers;

import documents.exceptions.FileWriterException;

import java.util.List;
import java.util.Map;

public interface IFileWriter {
    void write(String filePath, List<Map<String, String>> data) throws FileWriterException;
}
