package org.day4.behavior_parameterization.case_study_strategy_pattern_no_lambdas;

public class EmailSender {
    public void sendEmail(EmailContentGenerator contentGenerator) {
        String subject = contentGenerator.generateSubject();
        String body = contentGenerator.generateBody();
        System.out.println("Sending email with Subject: \"" + subject + "\" and Body: \"" + body + "\"");
    }
}
