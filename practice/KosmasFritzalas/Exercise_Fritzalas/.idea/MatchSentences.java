import java.io.*;
import java.nio.file.*;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class MatchSentences {
    public static void main(String[] args) {
        try {
            // Read the JSON file
            String jsonContent = new String(Files.readAllBytes(Paths.get("input.json")));
            JSONArray jsonArray = new JSONArray(jsonContent);

            // Read the text file
            String textContent = new String(Files.readAllBytes(Paths.get("input.txt")));
            // Split sentences on '.' followed by any whitespace or end of line
            String[] sentences = textContent.split("(?<=\\.)\\s*");

            // Create a map of index to sentence
            Map<Integer, String> sentenceMap = new HashMap<>();
            for (int i = 0; i < sentences.length; i++) {
                sentenceMap.put(i, sentences[i].trim());
            }

            // Assign sentences to JSON values
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int index = jsonObject.getInt("index");
                jsonObject.put("value", sentenceMap.get(index));
            }

            // Write the final JSON to output file
            Files.write(Paths.get("output.json"), jsonArray.toString(4).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
