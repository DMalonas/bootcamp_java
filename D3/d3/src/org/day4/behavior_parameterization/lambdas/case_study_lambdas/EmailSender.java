package org.day4.behavior_parameterization.lambdas.case_study_lambdas;

// EmailSender.java
// This class is responsible for sending emails.
public class EmailSender {
    // Method to send an email, which takes EmailContent object
    public void sendEmail(EmailContent content) {
        // Getting subject and body using the supplied lambda expressions
        String subject = content.getSubject();
        String body = content.getBody();
        // Simulating email sending
        System.out.println("Sending email with Subject: \"" + subject + "\" and Body: \"" + body + "\"");
    }
}
