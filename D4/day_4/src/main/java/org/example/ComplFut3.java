package org.example;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Demonstrates the use of CompletableFuture for handling asynchronous computations
 * and exception handling in Java. This class creates two CompletableFutures and an
 * ExecutorService with a fixed thread pool. It shows how the first CompletableFuture
 * is completed normally by multiple threads, with only the first completion taking effect,
 * and how the second CompletableFuture is completed exceptionally.
 */
public class ComplFut3 {
    public static void main(String[] args) throws Exception {
        // Create an ExecutorService with a fixed thread pool of 2 threads.
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Create two CompletableFuture instances.
        CompletableFuture<Integer> f1 = new CompletableFuture<>();
        CompletableFuture<Integer> f2 = new CompletableFuture<>();

        // Submit tasks to the executor to complete the first CompletableFuture (f1).
        // Only the first call to complete() will take effect; subsequent calls are ignored.
        executor.execute( () -> { f1.complete(100); }); // Completes f1 with value 100.
        executor.execute( () -> {
            try { Thread.sleep(100); } catch (InterruptedException ex) {}
            f1.complete(101); // This completion attempt is ignored.
        });
        executor.execute( () -> {
            try { Thread.sleep(200); } catch (InterruptedException ex) {}
            f1.complete(102); // This completion attempt is also ignored.
        });

        // Set up a completion handler for the second CompletableFuture (f2).
        // This handler will be invoked if f2 is completed exceptionally.
        f2.whenComplete( (res, exc) -> {
            if(exc != null) {
                System.out.println("f2 completed exceptionally: " + exc);
            }
        });

        // Submit a task to complete f2 exceptionally.
        executor.execute( () -> f2.completeExceptionally(new IllegalArgumentException()) );

        // Wait for the first CompletableFuture (f1) to be completed.
        while(!f1.isDone()) {
            System.out.println("Still not reporting that it is done..");
            try { Thread.sleep(300); } catch (InterruptedException ex) {}
        }

        // Print the result of the first CompletableFuture (f1).
        System.out.println("Now it is done: " + f1.get());

        // Shut down the ExecutorService.
        executor.shutdown();
    }
}
