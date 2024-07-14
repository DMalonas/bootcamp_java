package org.d3.practice.observer_design_pattern;

public class LoggerObserver implements Observer {
    @Override
    public void update(String event) {
        System.out.println("Logger received: " + event);
    }
}

