package org.d3.practice.observer_design_pattern;

public class EmailAlertObserver implements Observer {
    @Override
    public void update(String event) {
        System.out.println("Email Alert: " + event);
    }
}
