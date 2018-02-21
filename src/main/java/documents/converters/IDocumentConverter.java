package documents.converters;

import documents.exceptions.FileReaderException;
import documents.exceptions.FileWriterException;

public interface IDocumentConverter {
    void convert(String inputFilePath, String outputFilePath) throws FileReaderException, FileWriterException;
}
