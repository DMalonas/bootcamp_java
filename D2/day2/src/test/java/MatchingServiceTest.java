
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.practice.service.matching.MatchingService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class MatchingServiceTest {

    private MatchingService matchingService;

    @BeforeEach
    void setUp() {
        matchingService = new MatchingService();
    }

    @Test
    void testMatchingSentence() {
        String sentence = "A lava field formed after the 2021 eruption.";
        List<String> pdfSentences = Arrays.asList(
                "A lava field formed after the 2021 eruption of the Fagradalsfjall volcano.",
                "Another sentence not related."
        );
        String expected = "A lava field formed after the 2021 eruption of the Fagradalsfjall volcano.";
        String actual = matchingService.matchSentence(sentence, pdfSentences);
        assertEquals(expected, actual);
    }

    @Test
    void testNoMatchFound() {
        String sentence = "Random sentence with no match.";
        List<String> pdfSentences = Arrays.asList(
                "A lava field formed after the 2021 eruption of the Fagradalsfjall volcano.",
                "Another sentence not related."
        );
        assertNull(matchingService.matchSentence(sentence, pdfSentences));
    }

    @Test
    void testCaseInsensitivityAndSpaceNormalization() {
        String sentence = " a LAVA  FIELD formed  after  the 2021 eruption ";
        List<String> pdfSentences = Arrays.asList(
                "A lava field formed after the 2021 eruption of the Fagradalsfjall volcano.",
                "Another sentence not related."
        );
        String expected = "A lava field formed after the 2021 eruption of the Fagradalsfjall volcano.";
        String actual = matchingService.matchSentence(sentence, pdfSentences);
        assertEquals(expected, actual);
    }

    @Test
    void testSpecialCharactersHandling() {
        String sentence = "Iceland's southwest coast";
        List<String> pdfSentences = Arrays.asList(
                "The site is located close to the town Grindavik on Iceland's southwest coast.",
                "Another sentence not related."
        );
        String expected = "The site is located close to the town Grindavik on Iceland's southwest coast.";
        assertEquals(expected, matchingService.matchSentence(sentence, pdfSentences));
    }
}
