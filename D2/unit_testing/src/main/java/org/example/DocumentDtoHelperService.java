package com.exiq.columbia.rssfunction.services;

import com.exiq.columbia.rssfunction.models.Document;
import com.exiq.columbia.rssfunction.models.DocumentDTO;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DocumentDtoHelperService {

  public List<DocumentDTO> getDocumentDTOsByCustomerId(
      String customerId, List<Document> publishedDocuments, List<Document> readyDocuments) {
    List<Document> filteredDocuments = new ArrayList<>();

    // Set state to PUBLISHED for publishedDocuments
    filteredDocuments.addAll(
        publishedDocuments.stream()
            .filter(doc -> doc.getCustomerId().equals(customerId))
            .peek(doc -> doc.setState("PUBLISHED"))
            .collect(Collectors.toList()));

    // Set state to READY for readyDocuments
    filteredDocuments.addAll(
        readyDocuments.stream()
            .filter(doc -> doc.getCustomerId().equals(customerId))
            .peek(doc -> doc.setState("READY"))
            .collect(Collectors.toList()));

    // Sort documents by Created date in ascending order
    filteredDocuments.sort(Comparator.comparing(Document::getCreated));

    // Map to DocumentDTO
    return filteredDocuments.stream()
        .map(doc -> new DocumentDTO(doc.getDocumentId(), doc.getTitle(), doc.getState()))
        .collect(Collectors.toList());
  }

  public boolean isSortedSameWay(
      List<DocumentDTO> episodesOrderList, List<DocumentDTO> documentDTOsByCustomerId) {
    if (episodesOrderList.size() != documentDTOsByCustomerId.size()) {
      return false; // Lists are of different sizes, so they can't be sorted the same way.
    }

    for (int i = 0; i < episodesOrderList.size(); i++) {
      DocumentDTO doc1 = episodesOrderList.get(i);
      DocumentDTO doc2 = documentDTOsByCustomerId.get(i);

      if (!doc1.getDocumentId().equals(doc2.getDocumentId())) {
        return false; // Found a mismatch in documentId at the same position.
      }

      if (!doc1.getDocumentState().equals(doc2.getDocumentState())) {
        return false; // Found a mismatch in documentState for the same documentId.
      }
    }

    return true; // All documentIds and documentStates match in the same order.
  }
}
