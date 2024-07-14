package org.d3.practice.observer_design_pattern;

import java.util.ArrayList;
import java.util.List;

public class EventSource {
    private final List<Observer> observers = new ArrayList<>();

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String event) {
        for (Observer observer : observers) {
            observer.update(event);
        }
    }

    // Method to trigger events (can be expanded based on specific scenarios)
    public void triggerEvent(String event) {
        System.out.println("Event triggered: " + event);
        notifyObservers(event);
    }
}
