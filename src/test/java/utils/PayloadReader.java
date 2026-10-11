package utils;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

public class PayloadReader {

    private PayloadReader() {
    }

    public static String getPayload(String path) throws IOException {
        String content = Files.readString(Path.of(path));
        String lower = path.toLowerCase(Locale.ROOT);

        if (lower.endsWith(".json")) {
            // Parsing it first makes the test fail early if the JSON is invalid
            return new JSONObject(content).toString();
        } else if (lower.endsWith(".xml") || lower.endsWith(".xsd")) {
            return content;
        } else {
            throw new IllegalArgumentException(
                    "Unsupported file type: " + path + " (use .json, .xml or .xsd)");
        }
    }

}
