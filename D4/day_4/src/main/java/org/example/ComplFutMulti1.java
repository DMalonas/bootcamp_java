package org.example;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Demonstrates the use of CompletableFuture for combining results of multiple
 * asynchronous computations. This class creates two CompletableFutures that
 * complete asynchronously after different delays. It then combines these futures
 * and processes their combined result.
 */
public class ComplFutMulti1 {
    // Helper method to simulate a delay (sleep).
    public static void sleeper(int n) {
        try {
            Thread.sleep(n);
        } catch (InterruptedException ex) {
            // Handle InterruptedException.
        }
    }

    public static void main(String[] args) throws Exception {
        // Create an ExecutorService with a fixed thread pool of 5 threads.
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Create the first CompletableFuture that completes after a 200ms delay.
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
            sleeper(200);
            System.out.println("F1 COMPLETE");
            return 100; // Return a result.
        }, executor);

        // Create the second CompletableFuture that completes after a 100ms delay.
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
            sleeper(100);
            System.out.println("F2 COMPLETE");
            return 200; // Return a result.
        }, executor);

        // Combine f1 and f2. The combination does not block and waits for both to complete.
        // The result of the combination (sum of f1 and f2 results) is then processed.
        CompletableFuture<Void> f12 = f1.thenCombine(f2, (x, y) -> x + y)
                .thenAccept(x -> { // This is the thenAccept on the combined future.
                    System.out.println("Final result is: " + x);
                });

        // Shut down the ExecutorService.
        executor.shutdown();
    }
}
