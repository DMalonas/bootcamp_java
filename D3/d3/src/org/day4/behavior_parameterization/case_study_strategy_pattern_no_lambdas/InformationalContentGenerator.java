package org.day4.behavior_parameterization.case_study_strategy_pattern_no_lambdas;

public class InformationalContentGenerator implements EmailContentGenerator {
    @Override
    public String generateSubject() {
        return "Stay Informed: Weekly Update";
    }

    @Override
    public String generateBody() {
        return "This is the body of the informational email...";
    }
}
