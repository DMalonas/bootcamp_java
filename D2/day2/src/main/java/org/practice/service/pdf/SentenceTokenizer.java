package org.practice.service.pdf;

import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;
import java.util.ArrayList;
import java.util.List;

public class SentenceTokenizer {

    public List<String> tokenizeIntoSentences(String text) {
        Document doc = new Document(text);
        List<String> sentences = new ArrayList<>();
        for (Sentence sent : doc.sentences()) {
            sentences.add(sent.text());
        }
        return sentences;
    }
}
