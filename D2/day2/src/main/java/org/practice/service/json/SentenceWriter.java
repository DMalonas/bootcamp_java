package org.practice.service.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.practice.model.Sentence;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class SentenceWriter {

    private final Gson gson;

    public SentenceWriter() {
        this.gson = new GsonBuilder().serializeNulls().create();
    }

    public void writeEnhancedSentencesToFile(String outputFilePath, List<Sentence> enhancedSentences) {
        Path outputPath = Paths.get(outputFilePath);
        Path outputDir = outputPath.getParent();

        // Create the directory if it does not exist
        if (outputDir != null) {
            try {
                Files.createDirectories(outputDir);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

        try (BufferedWriter writer = Files.newBufferedWriter(outputPath, StandardCharsets.UTF_8, StandardOpenOption.CREATE)) {
            for (Sentence sentence : enhancedSentences) {
                String json = gson.toJson(sentence);
                writer.write(json);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
