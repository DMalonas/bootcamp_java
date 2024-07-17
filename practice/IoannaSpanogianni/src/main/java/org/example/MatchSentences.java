package org.example;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class MatchSentences {
    public static void main(String[] args) throws IOException {
        // parsing the JSON file
        String jsonFilePath = "Sample.json";
        String jsonContent = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
        Gson gson = new Gson();
        List<JsonObject> jsonData = gson.fromJson(jsonContent, new TypeToken<List<JsonObject>>() {}.getType());

        String textFilePath = "Sample.txt";
        String textContent = new String(Files.readAllBytes(Paths.get(textFilePath)));

        // Cleaning up and splitting the text data into sentences
        String[] sentencesArray = textContent.replace('\n', ' ').split("(?<=\\.)\\s*");
        List<String> sentences = Arrays.stream(sentencesArray)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());

        // matching each JSON entry's value with the closest sentence from the text file,
        // using LevenshteinDistance method
        List<JsonObject> finalResult = new ArrayList<>();
        for (JsonObject entry : jsonData) {
            int index = entry.get("index").getAsInt();
            String value = entry.get("value").getAsString();

            String closestSentence = null;
            int minDistance = Integer.MAX_VALUE;
            for (String sentence : sentences) {
                int distance = levenshteinDistance(value, sentence);
                if (distance < minDistance) {
                    minDistance = distance;
                    closestSentence = sentence;
                }
            }

            JsonObject resultEntry = new JsonObject();
            resultEntry.addProperty("index", index);
            resultEntry.addProperty("value", value);
            resultEntry.addProperty("matchedValue", closestSentence);
            finalResult.add(resultEntry);
        }

        // Writing the final result to an output.json file
        String outputFilePath = "output.json";
        Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        try (Writer writer = new FileWriter(outputFilePath)) {
            prettyGson.toJson(finalResult, writer);
        }
    }



    // method to compute the Levenshtein distance between two strings
    public static int levenshteinDistance(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();
        int[] costs = new int[b.length() + 1];
        for (int j = 0; j < costs.length; j++) {
            costs[j] = j;
        }
        for (int i = 1; i <= a.length(); i++) {
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        return costs[b.length()];
    }
}
