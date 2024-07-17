package org.example;

public class Threads2d {
    public static void main(String[] args) {
        // Creating and starting a new non-daemon thread
        Thread t0 = new Thread(() -> {
            // Non-daemon thread
            try {
                // The thread sleeps for 5 seconds
                Thread.sleep(5000);
            } catch (InterruptedException iex) {
                // InterruptedException is caught but no action is taken
            }
            // After waking up, it prints this message
            System.out.println("Exiting thread after 5 seconds");
        });
        t0.start(); // Start the thread

        // Main thread prints this message and then terminates
        System.out.println("Main terminates here");
    }
}
