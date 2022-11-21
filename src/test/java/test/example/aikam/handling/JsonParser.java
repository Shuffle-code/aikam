package test.example.aikam.handling;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonParser {
    private JSONObject getJsonObjectFromFile(String fileName) throws IOException, JSONException {
        String jsonString = new String(Files.readAllBytes(Paths.get(fileName)));
        return new JSONObject(jsonString);
    }
}
