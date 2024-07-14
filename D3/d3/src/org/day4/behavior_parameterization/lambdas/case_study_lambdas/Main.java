package org.day4.behavior_parameterization.lambdas.case_study_lambdas;

// Main.java
// Main class to run the email sending example
public class Main {
    public static void main(String[] args) {
        // Creating an instance of EmailSender
        EmailSender emailSender = new EmailSender();

        // Creating and sending a promotional email
        // Lambda expressions define how to generate the subject and body
        EmailContent promoContent = new EmailContent(
                () -> "Don't Miss Our Promotional Sale!",  // Supplier for subject
                () -> "This is the body of the promotional email..." // Supplier for body
        );
        emailSender.sendEmail(promoContent);

        // Creating and sending an informational email
        EmailContent infoContent = new EmailContent(
                () -> "Stay Informed: Weekly Update", // Supplier for subject
                () -> "This is the body of the informational email..." // Supplier for body
        );
        emailSender.sendEmail(infoContent);

        // Creating and sending an alert email
        EmailContent alertContent = new EmailContent(
                () -> "Urgent: Action Required!", // Supplier for subject
                () -> "This is the body of the alert email..." // Supplier for body
        );
        emailSender.sendEmail(alertContent);
    }
}
