package documents.writers;

import documents.commons.SupportedFileExtensions;
import documents.writers.impl.*;

/**
 * Application of design pattern: Factory
 * Class is responsible for returning proper file writer according to file extension
 * Possibility of implementing further file readers
 */
public class FileWriterFactory {

    public IFileWriter produce(String filePath) {
        IFileWriter result = null;
        if (filePath.endsWith(SupportedFileExtensions.CSV)) {
            result = new CSVFileWriter();
        }
        if (filePath.endsWith(SupportedFileExtensions.JSON)) {
            result = new JSONFileWriter();
        }
        if (filePath.endsWith(SupportedFileExtensions.XML)) {
            result = new XMLFileWriter();
        }
        if (filePath.endsWith(SupportedFileExtensions.EXCEL)) {
            result = new ExcelFileWriter();
        }
        return result;
    }
}
