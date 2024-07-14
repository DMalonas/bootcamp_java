package org.practice.service.matching;

import org.practice.service.json.SentenceReader;
import org.practice.service.pdf.PDFTextExtractor;

import java.util.List;

public class MatchingService {



    public MatchingService() {
    }

    public String matchSentence(String sentence, List<String> pdfSentences) {
        for (String pdfSentence : pdfSentences) {
            String modifiedPdfSentence = pdfSentence.replace("\r\n", " ").toLowerCase();
            String sentenceToLowerCase = sentence.toLowerCase();
            // Normalize spaces: Replace multiple spaces with a single space
            String normalizedModifiedPdfSentence = modifiedPdfSentence.replaceAll("\\s+", " ");
            String normalizedSentenceToLowerCase = sentenceToLowerCase.replaceAll("\\s+", " ");
            // Check if the PDF sentence contains the input sentence
            if (modifiedPdfSentence.contains(sentenceToLowerCase)) {
                return pdfSentence; // Return the matching sentence
            }

            // Check if the PDF sentence is exactly the same as the input sentence
//            if (modifiedPdfSentence.equals(sentenceToLowerCase)) {
//                return pdfSentence;
//            }

            // Check for a match after normalizing spaces and removing non-alphanumeric characters
            String normalizedPdfSentence = normalizedModifiedPdfSentence.replaceAll("[^a-zA-Z0-9\\s]", "").trim();
            String normalizedSentence = normalizedSentenceToLowerCase.replaceAll("[^a-zA-Z0-9\\s]", "").trim();


            if (normalizedPdfSentence
                    .contains(normalizedSentence)) {
                return pdfSentence;
            }        }
        return null; // or return ""; if you prefer to return an empty string when no match is found
    }

}
