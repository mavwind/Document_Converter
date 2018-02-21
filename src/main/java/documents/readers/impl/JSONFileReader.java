package documents.readers.impl;

import org.json.JSONArray;
import org.json.JSONObject;
import documents.exceptions.FileReaderException;
import documents.readers.IFileReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONFileReader implements IFileReader {

    private static final String FILE_DEFAULT_ENCODING = "UTF-8";

    @Override
    public List<Map<String, String>> read(String filePath) throws FileReaderException {
        List<Map<String, String>> result = new ArrayList<>();
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(filePath));
            String fileContents = new String(encoded, FILE_DEFAULT_ENCODING);

            JSONArray jsonFromFile = new JSONArray(fileContents);

            for (int i = 0; i < jsonFromFile.length(); i++) {
                Object jsonObjectRaw = jsonFromFile.get(i);
                JSONObject jsonObject = new JSONObject(jsonObjectRaw.toString());
                Map<String, String> record = new HashMap<>();
                for (String key : jsonObject.keySet()) {
                    record.put(key, jsonObject.get(key).toString());
                }
                result.add(record);
            }

        } catch (IOException e) {
            throw new FileReaderException(e.getMessage(), e);
        }
        return result;
    }

}
