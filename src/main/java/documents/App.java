package documents;

import documents.converters.DocumentConverter;
import documents.converters.IDocumentConverter;
import documents.exceptions.FileReaderException;
import documents.exceptions.FileWriterException;

public class App
{
    public static void main( String[] args ) {
        String inputFilePath = "";
        String outputFilePath = "";

        IDocumentConverter documentConverter = new DocumentConverter();
        try {
            documentConverter.convert(inputFilePath, outputFilePath);
        } catch (FileReaderException e) {
            System.out.println("Error! Loading the file failed!");
        } catch (FileWriterException e) {
            System.out.println("Error! Saving the file failed!");
        }
    }
}
