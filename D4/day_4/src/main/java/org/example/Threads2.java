package org.example;

public class Threads2 {
    public static void main(String[] args) {
        // Creating a thread with a lambda expression as its runnable task
        Thread t0 = new Thread(() -> {
            long cnt = 0; 
            boolean receivedInt = false;

            // Loop until a certain count is reached
            while (cnt < 10000000) {
                cnt = cnt + 1;

                // Check if the current thread has been interrupted
                if (Thread.currentThread().isInterrupted() && !receivedInt) {
                    System.out.println("Received interrupted signal at count: " + cnt);
                    // Mark that the interruption has been received
                    receivedInt = true;
                }
            }

            // Print the final count and the interrupted status
            System.out.println("Count: " + cnt + " Interrupted: " +
                               Thread.currentThread().isInterrupted());
        });

        // Start the thread
        t0.start();

        try {
            // Main thread sleeps for a short time
            Thread.sleep(5);
            // Interrupt the thread t0
            t0.interrupt();
            // Wait for t0 to finish
            t0.join();
        } catch (InterruptedException iex) {
            // Handle InterruptedException here if necessary
        }
    }
}
