package org.d3.practice.observer_design_pattern;

public class SMSAlertObserver implements Observer {
    @Override
    public void update(String event) {
        System.out.println("SMS Alert: " + event);
    }
}
