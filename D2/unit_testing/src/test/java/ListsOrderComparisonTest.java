import org.example.DocumentDTO;
import org.example.DocumentDtoHelperService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DocumentDtoHelperServiceTest {

  DocumentDtoHelperService documentDtoHelperService;

  @BeforeEach
  public void init() {
    this.documentDtoHelperService = new DocumentDtoHelperService();
  }

  @Test
  void testSameOrderAndState() {
    DocumentDTO doc1 = new DocumentDTO("1", "Title1", "PUBLISHED");
    DocumentDTO doc2 = new DocumentDTO("2", "Title2", "PUBLISHED");
    DocumentDTO doc3 = new DocumentDTO("3", "Title3", "READY");

    List<DocumentDTO> episodesOrderList = Arrays.asList(doc1, doc2, doc3);
    List<DocumentDTO> documentDTOsByCustomerId = Arrays.asList(doc1, doc2, doc3);

    boolean result =
        documentDtoHelperService.isSortedSameWay(episodesOrderList, documentDTOsByCustomerId);
    assertTrue(result, "Lists should be sorted the same way with matching states");
  }

  @Test
  void testDifferentOrder() {
    DocumentDTO doc1 = new DocumentDTO("1", "Title1", "PUBLISHED");
    DocumentDTO doc2 = new DocumentDTO("2", "Title2", "PUBLISHED");
    DocumentDTO doc3 = new DocumentDTO("3", "Title3", "READY");

    List<DocumentDTO> episodesOrderList = Arrays.asList(doc1, doc3, doc2);
    List<DocumentDTO> documentDTOsByCustomerId = Arrays.asList(doc1, doc2, doc3);

    boolean result =
        documentDtoHelperService.isSortedSameWay(episodesOrderList, documentDTOsByCustomerId);
    assertFalse(result, "Lists should not be sorted the same way");
  }

  @Test
  void testDifferentState() {
    DocumentDTO doc1 = new DocumentDTO("1", "Title1", "PUBLISHED");
    DocumentDTO doc2 = new DocumentDTO("2", "Title2", "PUBLISHED");
    DocumentDTO doc3 = new DocumentDTO("3", "Title3", "READY");
    DocumentDTO doc4 = new DocumentDTO("3", "Title3", "PUBLISHED"); // Different state

    List<DocumentDTO> episodesOrderList = Arrays.asList(doc1, doc2, doc3);
    List<DocumentDTO> documentDTOsByCustomerId = Arrays.asList(doc1, doc2, doc4);

    boolean result =
        documentDtoHelperService.isSortedSameWay(episodesOrderList, documentDTOsByCustomerId);
    assertFalse(result, "Lists should not match due to different states");
  }

  @Test
  void testDifferentSize() {
    DocumentDTO doc1 = new DocumentDTO("1", "Title1", "PUBLISHED");
    DocumentDTO doc2 = new DocumentDTO("2", "Title2", "PUBLISHED");

    List<DocumentDTO> episodesOrderList = Arrays.asList(doc1, doc2);
    List<DocumentDTO> documentDTOsByCustomerId = List.of(doc1);

    boolean result =
        documentDtoHelperService.isSortedSameWay(episodesOrderList, documentDTOsByCustomerId);
    assertFalse(result, "Lists should not match due to different sizes");
  }
}
