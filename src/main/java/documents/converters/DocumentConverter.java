package documents.converters;

import documents.exceptions.FileReaderException;
import documents.exceptions.FileWriterException;
import documents.readers.FileReaderFactory;
import documents.readers.IFileReader;
import documents.writers.FileWriterFactory;
import documents.writers.IFileWriter;

import java.util.List;
import java.util.Map;

public class DocumentConverter implements IDocumentConverter {

    @Override
    public void convert(String inputFilePath, String outputFilePath) throws FileReaderException, FileWriterException {
        //reading the file
        FileReaderFactory fileReaderFactory = new FileReaderFactory();
        IFileReader reader = fileReaderFactory.produce(inputFilePath);
        List<Map<String, String>> data = reader.read(inputFilePath);

        //writing the file
        FileWriterFactory fileWriterFactory = new FileWriterFactory();
        IFileWriter fileWriter = fileWriterFactory.produce(outputFilePath);
        fileWriter.write(outputFilePath, data);
    }
}
