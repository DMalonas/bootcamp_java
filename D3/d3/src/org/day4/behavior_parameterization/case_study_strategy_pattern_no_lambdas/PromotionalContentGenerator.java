package org.day4.behavior_parameterization.case_study_strategy_pattern_no_lambdas;

public class PromotionalContentGenerator implements EmailContentGenerator {
    @Override
    public String generateSubject() {
        return "Don't Miss Our Promotional Sale!";
    }

    @Override
    public String generateBody() {
        return "This is the body of the promotional email...";
    }
}
