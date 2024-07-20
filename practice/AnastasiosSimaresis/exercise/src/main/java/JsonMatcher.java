import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class JsonMatcher {
    public static void main(String[] args) throws IOException {
        // Specify the full path to the sample.json and sample.txt files
        String jsonFilePath = "sample.json";
        String textFilePath = "sample.txt";

        // parse JSON array from file and read text file lines into a list
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode jsonArray = (ArrayNode) mapper.readTree(new File(jsonFilePath));
        List<String> textLines = Files.readAllLines(Paths.get(textFilePath));


        for (int i = 0; i < jsonArray.size(); i++) {
            ObjectNode jsonObject = (ObjectNode) jsonArray.get(i);
            String value = jsonObject.get("value").asText().trim();

            // Find the best match in the text lines
            String bestMatch = null;
            int maxCommonWords = 0;
            for (String line : textLines) {
                line = line.trim();
                int commonWords = countCommonWords(value, line);
                if (commonWords > maxCommonWords) {
                    maxCommonWords = commonWords;
                    bestMatch = line;
                }
            }

            if (bestMatch != null) {
                jsonObject.put("matchedValue", bestMatch);
            } else {
                jsonObject.put("matchedValue", "No match found");
            }
        }

        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("output.json"), jsonArray);
    }

    // Helper function to count common words between two strings
    private static int countCommonWords(String s1, String s2) {
        String[] words1 = s1.split("\\W+");
        String[] words2 = s2.split("\\W+");
        int commonCount = 0;

        for (String word1 : words1) {
            for (String word2 : words2) {
                if (word1.equalsIgnoreCase(word2)) {
                    commonCount++;
                }
            }
        }

        return commonCount;
    }
}
