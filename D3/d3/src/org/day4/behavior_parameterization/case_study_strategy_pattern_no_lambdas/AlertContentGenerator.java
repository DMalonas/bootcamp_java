package org.day4.behavior_parameterization.case_study_strategy_pattern_no_lambdas;

public class AlertContentGenerator implements EmailContentGenerator {
    @Override
    public String generateSubject() {
        return "Urgent: Action Required!";
    }

    @Override
    public String generateBody() {
        return "This is the body of the alert email...";
    }
}
