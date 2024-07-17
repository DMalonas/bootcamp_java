package org.example;

/**
 * A thread class that counts continuously until instructed to stop.
 */
class CounterTask extends Thread {

    /**
     * GPT
     * The volatile keyword in Java is used to indicate that
     * a variable's value will be modified by different threads.
     * Declaring a variable as volatile ensures that its value
     * is always read from and written to the main memory, not just the thread's
     * local cache. This guarantees visibility of changes to the variable across different threads.
     */
    // The volatile keyword ensures that changes to this variable
    // are immediately visible to other threads.
    volatile private boolean shouldStop = false;

    private long counter = 0;

    /**
     * The run method contains the core logic that is executed in the thread.
     * It increments the counter in a loop until told to stop.
     */
    public void run() {
        // Continuously increment counter until shouldStop becomes true
        while(!shouldStop) {
            counter += 1;
        }
        // Print the final count value when the loop ends
        System.out.println("Counted up to " + counter);
    }

    /**
     * Signals the thread to stop counting.
     */
    public void pleaseStop() {
        shouldStop = true;
    }
}

public class Threads1 {
    public static void main(String[] args) {
        // Create and start the counter thread
        CounterTask t0 = new CounterTask();
        t0.start();

        try {
            // Main thread pauses for a short duration
            Thread.sleep(500);

            // Instructs the counter thread to stop
            t0.pleaseStop();

            // Waits for the counter thread to finish
            t0.join();
        } catch(InterruptedException iex) {
            // InterruptedException handling, if required, would go here
        }
    }
}
