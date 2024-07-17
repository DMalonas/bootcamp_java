package org.example;

public class Threads6 {
    public static void main(String[] args) {
        // Creating a new thread
        Thread t0 = new Thread(() -> {
            // This thread will throw an IllegalArgumentException
            throw new IllegalArgumentException("An argument was wrong");
        });

        // Setting the name of the thread
        t0.setName("ExceptionalThread");

        // Setting an UncaughtExceptionHandler for the thread
        t0.setUncaughtExceptionHandler(
                new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread t, Throwable ex) {
                // This block is executed when an uncaught exception occurs in the thread
                System.out.println("Thread " + t.getName());
                System.out.println(" terminated with " + ex);
            }
        });

        t0.setUncaughtExceptionHandler((t, ex) -> {
            //This block is executed when
            System.out.println("Thread " + t.getName());
            System.out.println(" terminated with " + ex);

        });

        // Starting the thread
        t0.start();


        // Setting an UncaughtExceptionHandler for the thread using a lambda
//        t0.setUncaughtExceptionHandler((t, ex) -> {
//            // This block is executed when an uncaught exception occurs in the thread
//            System.out.println("Thread " + t.getName() + " terminated with " + ex);
//        });

    }
}
