package org.example;

/**
 * Implements a Runnable that calculates the sum of numbers up to a given count.
 * This class maintains a local state for the count value.
 */
class HardSumTask implements Runnable {
    private long count;

    /**
     * Constructor to initialize the count.
     * @param count The maximum number to sum up to.
     */
    public HardSumTask(int count) {
        this.count = count;
    }

    /**
     * The run method contains the task to be executed in the thread.
     * It simulates a busy state and then calculates the sum of numbers.
     */
    public void run() {
        try {
            // Simulate a busy state
            System.out.println("busy");
            Thread.sleep(1000);
        } catch(InterruptedException iex) {
            // Handling InterruptedException is important but not implemented here
        }
        // Calculate and print the sum of numbers from 1 to count
        System.out.println("HardSumTask: " + count*(count+1)/2);
    }
}

public class Threads0 {
    public static void main(String[] args) {
        // Creating a thread with the HardSumTask
        Thread t0 = new Thread(new HardSumTask(1000000));

        // Creating a thread using a lambda expression
        Thread t1 = new Thread(() -> {
            long sum = 0;
            for(int i = 1; i <= 1000000; i++) {
                sum += i;
            }
            System.out.println("Lambda: " + sum);
        });

        // Start both threads
        t0.start();
        t1.start();

        // Join both threads to ensure they complete before exiting the main thread
        try {
            t0.join();
            t1.join();
        } catch(InterruptedException iex) {
            // Handle InterruptedException here if necessary
        }
    }
}
