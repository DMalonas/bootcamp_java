package org.example;

public class SynchronizedExample {
    // Shared resource among threads
    private static int counter = 0;

    // Lock object for synchronization
    private static final Object lock = new Object();

    // Thread class that increments the counter
    static class CounterThread extends Thread {
        public void run() {
            // Loop to increment the counter
            for (int i = 0; i < 1000; i++) {
                // Synchronized block to ensure only one thread can access this block at a time
                synchronized (lock) {
                    // Incrementing the shared counter
                    counter++;
                }
                // The lock is released when the block ends, allowing other threads to enter the block
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Creating two threads
        Thread t1 = new CounterThread();
        Thread t2 = new CounterThread();

        // Starting the threads
        t1.start();
        t2.start();

        // Waiting for both threads to finish execution
        t1.join();
        t2.join();

        // Printing the final value of the counter
        // Due to synchronization, this should always be 2000 (1000 increments by each thread)
        System.out.println("Final counter value: " + counter);
    }
}

