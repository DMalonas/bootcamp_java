package org.example;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Demonstrates various ways of composing and chaining CompletableFuture instances
 * in Java. This class showcases how to transform and consume results of asynchronous
 * computations, as well as how to handle different stages of task execution using
 * CompletableFutures and an ExecutorService.
 */
public class ComplFut4 {
    public static void main(String[] args) throws Exception {
        // Create an ExecutorService with a fixed thread pool of 5 threads.
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Create a CompletableFuture and chain it with transformations and a final consumption.
        CompletableFuture<Integer> f1 = new CompletableFuture<>();
        f1.thenApply(x -> "The value is " + x) // Transform Integer to String.
          .thenAccept(System.out::println); // Consume the result (print it).

        // Create another CompletableFuture, start it asynchronously, and chain multiple stages.
        CompletableFuture<String> f2 = new CompletableFuture<>();
        f2.supplyAsync(() -> "First part", executor) // Start asynchronously using the executor.
          .thenCompose(x -> CompletableFuture.supplyAsync(() -> x + " and then second")) // Compose with another stage.
          .thenApply(x -> x + " part") // Apply a transformation.
          .thenAccept(System.out::println); // Consume the result (print it).

        // Create a CompletableFuture, start it asynchronously, and attach a Runnable as the final stage.
        CompletableFuture<String> f3 = new CompletableFuture<>();
        f3.supplyAsync(() -> {
            System.out.println("Thread of some result " + Thread.currentThread().getName());
            return "Some result";
        }, executor)
          .thenRun(() -> {
            try {
                Thread.sleep(300); // Simulate some processing delay.
                System.out.println("This is the runnable from thread " + Thread.currentThread().getName());
            } catch (InterruptedException iex) {
                // Handle InterruptedException.
            }
        });

        // Complete the first CompletableFuture (f1) asynchronously.
        executor.execute(() -> f1.complete(100));

        // Shut down the ExecutorService.
        executor.shutdown();
    }
}
