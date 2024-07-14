package org.practice;

import org.practice.model.Sentence;
import org.practice.service.json.SentenceReader;
import org.practice.service.json.SentenceWriter;
import org.practice.service.matching.MatchingService;
import org.practice.service.pdf.PDFTextExtractor;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private final PDFTextExtractor extractor;
    private final SentenceReader reader;
    private final SentenceWriter writer;
    private final MatchingService matchingService;

    public Main(PDFTextExtractor extractor, SentenceReader reader, SentenceWriter writer, MatchingService matchingService) {
        this.extractor = extractor;
        this.reader = reader;
        this.writer = writer;
        this.matchingService = matchingService;
    }

    public List<String> processTexts() {
        // Extract sentences from a PDF file
        String pdfFilePath = "iceland_volcano.pdf"; // Replace with your actual PDF file path
        List<String> pdfSentences = extractor.extractTextFromPDF(pdfFilePath);

        // Get sentences from the SentenceReader
        List<Sentence> sentences = reader.getSentences();
        List<Sentence> enhancedSentences = new ArrayList<>();

        // Check for matches and count them
        int matchCount = 0;
        List<String> matchedSentences = new ArrayList<>();
        if (pdfSentences != null && !pdfSentences.isEmpty()) {
            for (Sentence sentence : sentences) {
                String sentenceValueToMatch = sentence.getValue();
                String matchedSentenceValue = matchingService.matchSentence(sentenceValueToMatch, pdfSentences);
                if (matchedSentenceValue != null) {
                    matchedSentences.add(matchedSentenceValue);
                    System.out.println("Matched Sentence: " + matchedSentenceValue);
                    sentence.setOriginalText(matchedSentenceValue);
                } else {
                    System.out.println("No match found.");
                }
            }

            // Write the enhanced sentences to file
            writer.writeEnhancedSentencesToFile("OUTPUT/iceland_volcano_output.txt", enhancedSentences);
            System.out.println("Total number of matches found: " + matchCount);
        } else {
            System.out.println("No sentences extracted from PDF.");
        }
        return matchedSentences; // Return the list of matched sentences
    }

    public static void main(String[] args) {
        PDFTextExtractor extractor = new PDFTextExtractor();
        SentenceReader reader = new SentenceReader("iceland_volcano.txt");
        SentenceWriter writer = new SentenceWriter();
        MatchingService matchingService = new MatchingService();

        Main main = new Main(extractor, reader, writer, matchingService);
        main.processTexts();
    }
}
