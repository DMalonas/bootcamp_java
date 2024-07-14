package com.exiq.columbia.rssfunction.models;

public class DocumentDTO {
  private String documentId;
  private String documentTitle;

  private String documentState;

  public DocumentDTO() {}

  public DocumentDTO(String documentId, String documentTitle, String documentState) {
    this.documentId = documentId;
    this.documentTitle = documentTitle;
    this.documentState = documentState;
  }

  public DocumentDTO(String documentId, String documentTitle) {
    this.documentId = documentId;
    this.documentTitle = documentTitle;
  }

  public String getDocumentId() {
    return documentId;
  }

  public void setDocumentId(String documentId) {
    this.documentId = documentId;
  }

  public String getDocumentTitle() {
    return documentTitle;
  }

  public void setDocumentTitle(String documentTitle) {
    this.documentTitle = documentTitle;
  }

  public String getDocumentState() {
    return documentState;
  }

  public void setDocumentState(String documentState) {
    this.documentState = documentState;
  }

  @Override
  public String toString() {
    return "DocumentDTO{"
        + "documentId='"
        + documentId
        + '\''
        + ", documentTitle='"
        + documentTitle
        + '\''
        + ", state='"
        + documentState
        + '\''
        + '}';
  }
}
