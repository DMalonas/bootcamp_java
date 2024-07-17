package org.project_loom;

/**
 * This class demonstrates the creation and execution of a basic virtual thread in Java.
 * The example shows how to use Thread.startVirtualThread() to create a lightweight virtual thread,
 * execute a simple task, and ensure the main thread waits for the virtual thread to complete.
 * This highlights the simplicity and efficiency of using virtual threads compared to traditional threads.
 */
public class VirtualThreadExample {

    public static void main(String[] args) {
        // Creating and starting a virtual thread
        // Thread.startVirtualThread() creates and starts a new virtual thread
        // The parameter is a Runnable, which defines the task to be executed by the virtual thread
        Thread virtualThread = Thread.startVirtualThread(() -> {
            System.out.println("Hello from virtual thread!");   // This is the task that the virtual thread will execute
        });

        // Ensuring the main thread waits for the virtual thread to finish
        // join() method allows the main thread to wait until the virtual thread completes its execution
        try {
            virtualThread.join();
        } catch (InterruptedException e) {
            // Handling the InterruptedException if the virtual thread is interrupted while waiting
            e.printStackTrace();
        }

        // This message will be printed after the virtual thread has finished executing
        System.out.println("Main thread finished.");
    }
}
