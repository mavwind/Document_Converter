package documents.readers;

import documents.commons.SupportedFileExtensions;
import documents.readers.impl.*;

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
