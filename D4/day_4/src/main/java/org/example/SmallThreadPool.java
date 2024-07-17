package org.example;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A simple implementation of a thread pool with two threads.
 * This class manages a small pool of threads and a queue of tasks.
 * Tasks can be added to the queue and are executed by the threads in the pool.
 */
public class SmallThreadPool {
    private Thread t0, t1; // Two threads for the thread pool.
    final AtomicBoolean stopFlag = new AtomicBoolean(false); // Flag to signal the threads to stop.
    // Queue to hold the tasks. It has a capacity of 3.
    public final LinkedBlockingDeque<Runnable> taskQ = new LinkedBlockingDeque<Runnable>(3);

    // Constructor to create and start the threads.
    public SmallThreadPool() {
        t0 = makeThread("one");
        t1 = makeThread("two");
        t0.start(); // Start the first thread.
        t1.start(); // Start the second thread.
    }

    // Helper method to create a thread.
    // Each thread polls tasks from the task queue and executes them.
    Thread makeThread(String name) {
        return new Thread( () -> {
            while(!stopFlag.get()) { // Continue until stopFlag is true.
                try {
                    System.out.println("Poll " + name);
                    // Poll a task from the queue, waiting up to 100 milliseconds.
                    Runnable task = taskQ.pollLast(100, TimeUnit.MILLISECONDS);
                    System.out.println("Running in thread: " + name);
                    if (task != null) {
                        task.run(); // Execute the task.
                    }
                } catch(InterruptedException ex) {
                    // Handle InterruptedException.
                }
            }
            System.out.println("Thread " + name + " is closing");
        });
    }

    // Method to stop the thread pool.
    public void stop() throws InterruptedException {
        stopFlag.set(true); // Set the stop flag to true.
        t0.join(); // Wait for the first thread to terminate.
        t1.join(); // Wait for the second thread to terminate.
    }

    // Main method to test the thread pool.
    public static void main(String[] args) throws Exception {
        SmallThreadPool stp = new SmallThreadPool();
        // Add 10 no-op tasks to the task queue.
        for(int i = 0; i < 10; i++) {
            stp.taskQ.put( () -> { /* no op */ } );
        }
        stp.stop(); // Stop the thread pool.
    }
}
