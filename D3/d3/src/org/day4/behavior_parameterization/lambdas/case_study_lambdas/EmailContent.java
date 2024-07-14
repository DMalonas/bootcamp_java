package org.day4.behavior_parameterization.lambdas.case_study_lambdas;

import java.util.function.Supplier;

// EmailContent.java
// This class represents the content of an email, with separate suppliers for the subject and body.
public class EmailContent {
    // Suppliers for subject and body - they supply values when requested
    private final Supplier<String> subjectSupplier;
    private final Supplier<String> bodySupplier;

    // Constructor taking two lambda expressions or Suppliers for subject and body
    public EmailContent(Supplier<String> subjectSupplier, Supplier<String> bodySupplier) {
        this.subjectSupplier = subjectSupplier;
        this.bodySupplier = bodySupplier;
    }

    // Method to get the subject from the supplier
    public String getSubject() {
        return subjectSupplier.get();
    }

    // Method to get the body from the supplier
    public String getBody() {
        return bodySupplier.get();
    }
}

