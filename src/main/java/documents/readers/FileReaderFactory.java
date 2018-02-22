package documents.readers;

import documents.commons.SupportedFileExtensions;
import documents.readers.impl.*;

/**
 * Application of design pattern: Factory
 * Class is responsible for returning proper file reader according to file extension
 * Possibility of implementing further file readers
 */
public class FileReaderFactory {

    public IFileReader produce(String filePath) {
        IFileReader result = null;
        if (filePath.endsWith(SupportedFileExtensions.CSV)) {
            result = new CSVFileReader();
        }
        if (filePath.endsWith(SupportedFileExtensions.JSON)) {
            result = new JSONFileReader();
        }
        if (filePath.endsWith(SupportedFileExtensions.XML)) {
            result= new XMLFileReader();
        }
        if (filePath.endsWith(SupportedFileExtensions.EXCEL)) {
            result = new ExcelFileReader();
        }
        return result;
    }
}
