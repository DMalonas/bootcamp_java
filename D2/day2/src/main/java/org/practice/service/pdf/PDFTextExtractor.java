package org.practice.service.pdf;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PDFTextExtractor {

    private final SentenceTokenizer tokenizer;

    public PDFTextExtractor() {
        this.tokenizer = new SentenceTokenizer();
    }

    public List<String> extractTextFromPDF(String filePath) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (is == null) {
                throw new IOException("File not found in resources: " + filePath);
            }
            try (PDDocument document = Loader.loadPDF(is.readAllBytes())) {
                PDFTextStripper stripper = new PDFTextStripper();
                String text = stripper.getText(document);
                List<String> sentences = tokenizer.tokenizeIntoSentences(text);
                return sentences;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
