package org.example;

import java.util.concurrent.CompletableFuture;

/**
 * Demonstrates the use of CompletableFuture for asynchronous computation in Java.
 * This class creates a CompletableFuture, attaches a completion handler to it,
 * and completes it asynchronously using a separate thread. The main thread
 * continues to execute and periodically checks if the CompletableFuture is done.
 */
public class ComplFut1 {
    public static void main(String[] args) throws Exception {
        // Create a CompletableFuture instance.
        final CompletableFuture<Integer> f1 = new CompletableFuture<>();

        // Attach a completion handler to the CompletableFuture.
        // This handler will be called when the future is completed.
        f1.whenComplete( (result, exception) -> {
            // Check if the completion is normal (no exception).
            if(exception == null) {
                System.out.println("The result was: " + result);
            }
            // Note: In a real-world scenario, you should also handle the exception case.
        });

        // Create a new thread to complete the CompletableFuture.
        Thread t0 = new Thread( () -> {
            try {
                // Simulate some delay in the thread.
                Thread.sleep(500);
            } catch(InterruptedException iex) {
                // Handle InterruptedException.
            }
            // Complete the CompletableFuture with a specific result.
            f1.complete(100);
        });

        // Start the thread.
        System.out.println("Starting thread");
        t0.start();

        // Main thread continues to execute while waiting for CompletableFuture to complete.
        while(!f1.isDone()) {
            System.out.println("Still not reporting that it is done..");
            try {
                // Main thread waits for a short duration before checking again.
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                // Handle InterruptedException.
            }
        }
        // Note: In a real-world scenario, you might do more meaningful work here instead of just waiting.
    }
}
