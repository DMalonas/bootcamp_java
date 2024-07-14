import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.practice.Main;
import org.practice.model.Sentence;
import org.practice.service.json.SentenceReader;
import org.practice.service.json.SentenceWriter;
import org.practice.service.matching.MatchingService;
import org.practice.service.pdf.PDFTextExtractor;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Mock
    private PDFTextExtractor extractor;
    @Mock
    private SentenceReader reader;
    @Mock
    private SentenceWriter writer;

    private MatchingService matchingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        matchingService = new MatchingService(); // Real instance

        when(reader.getSentences()).thenReturn(Arrays.asList(
                new Sentence("A lava field formed after the 2021 eruption.", null),
                new Sentence("Iceland has declared a state of emergency.", null)
        ));

        when(extractor.extractTextFromPDF("iceland_volcano.pdf")).thenReturn(Arrays.asList(
                "A lava field formed after the 2021 eruption of the Fagradalsfjall volcano.",
                "Iceland has declared a state of emergency, with police officials urging residents to evacuate."
        ));
    }

    @Test
    void testProcessTextsMatchingServiceResults() {
        Main main = new Main(extractor, reader, writer, matchingService);
        List<String> matchedSentences = main.processTexts();

        // Assert the matched sentences
        List<String> expectedMatches = Arrays.asList(
                "A lava field formed after the 2021 eruption of the Fagradalsfjall volcano.",
                "Iceland has declared a state of emergency, with police officials urging residents to evacuate."
        );
        assertEquals(expectedMatches, matchedSentences);
    }
}
