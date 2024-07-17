package org.example;

public class Threads2d2 {
    public static void main(String[] args) {
        // Creating a new thread
        Thread t0 = new Thread(() -> {
            // Daemon thread
            try {
                // The thread attempts to sleep for 5 seconds
                Thread.sleep(5000);
            } catch (InterruptedException iex) {
                // InterruptedException is caught but no action is taken
            }
            // This line may or may not execute depending on when the JVM exits
            System.out.println("Exiting thread after 5 seconds");
        });

        // Setting the thread as a daemon thread
        t0.setDaemon(true);

        // Setting the name of the thread
        t0.setName("the daemon thread");

        // Starting the thread
        t0.start();

        // Main thread prints this message and then terminates
        System.out.println("Main terminates here");
    }
}
