package org.d3.practice.observer_design_pattern;

public class ObserverPatternDemo {
    public static void main(String[] args) {
        // Creating the event source
        EventSource eventSource = new EventSource();

        // Creating observers
        LoggerObserver loggerObserver = new LoggerObserver();
        EmailAlertObserver emailObserver = new EmailAlertObserver();
        SMSAlertObserver smsObserver = new SMSAlertObserver();

        // Attaching observers
        eventSource.attach(loggerObserver);
        eventSource.attach(emailObserver);
        eventSource.attach(smsObserver);

        // Triggering an event
        eventSource.triggerEvent("User logged in");

        // Detaching an observer and triggering another event
        eventSource.detach(emailObserver);
        eventSource.triggerEvent("User logged out");
    }
}
