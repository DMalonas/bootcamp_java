package org.day4.behavior_parameterization.case_study_strategy_pattern_no_lambdas;

public class Main {
    public static void main(String[] args) {
        EmailSender emailSender = new EmailSender();

        // Send a promotional email
        PromotionalContentGenerator promoContent = new PromotionalContentGenerator();
        emailSender.sendEmail(promoContent);

        // Send an informational email
        InformationalContentGenerator infoContent = new InformationalContentGenerator();
        emailSender.sendEmail(infoContent);

        // Send an alert email
        AlertContentGenerator alertContent = new AlertContentGenerator();
        emailSender.sendEmail(alertContent);
    }
}
