package org.practice.service.json;

import com.google.gson.Gson;
import org.practice.model.Sentence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SentenceReader {

    private List<Sentence> sentences;

    public SentenceReader(String filePath) {
        this.sentences = new ArrayList<>();
        this.loadSentencesFromFile(filePath);
    }


    public void loadSentencesFromFile(String filePath) {
        Gson gson = new Gson();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (is == null) {
                throw new IOException("File not found: " + filePath);
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Sentence sentence = gson.fromJson(line, Sentence.class);
                    this.sentences.add(sentence);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<Sentence> getSentences() {
        return sentences;
    }
}
