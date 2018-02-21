package documents.writers;

import documents.commons.SupportedFileExtensions;
import documents.writers.impl.*;

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
