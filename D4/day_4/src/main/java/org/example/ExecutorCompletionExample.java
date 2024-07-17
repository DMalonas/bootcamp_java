package org.example;

import java.util.Random;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



/**
 * Demonstrates the use of ExecutorCompletionService for managing asynchronous tasks.
 * This class creates a fixed thread pool and submits a number of tasks to it.
 * Each task sleeps for a random duration to simulate variable task execution times.
 * ExecutorCompletionService is used to efficiently process the results of these tasks
 * as they complete. The main method calculates the sum of the results of all tasks.
 */
public class ExecutorCompletionExample {
    public static void main(String[] args) throws Exception {
        // Create an ExecutorService with a fixed thread pool of 5 threads.
        ExecutorService exSrvc = Executors.newFixedThreadPool(5);

        // ExecutorCompletionService is used to manage the completion of tasks.
        // It wraps around the ExecutorService.
        ExecutorCompletionService<Integer> cs = new ExecutorCompletionService<>(exSrvc);

        final var rnd = new Random(); // Random instance for generating sleep times.

        // Submit 10 tasks to the ExecutorCompletionService.
        for(int i = 0; i < 10; i++) {
            final var finalI = i; // Final variable for use in the lambda expression.
            cs.submit( () -> {
                try {
                    // Random sleep to simulate variable task duration.
                    Thread.sleep(rnd.nextInt(500) + 10);
                } catch(Exception ex) {
                    // Handle exceptions if any (e.g., InterruptedException).
                }
                // Print a message when a task is finishing.
                System.out.println("Task " + finalI + " finishing");
                // Return a computed value based on the loop index.
                return finalI * 100 + 1;
            });
        }

        // Calculate the sum of the results of all tasks.
        int sum = 0;
        for(int i = 0; i < 10; i++) {
            // Take() retrieves and removes the Future representing the next completed task,
            // waiting if none are yet present.
            sum += cs.take().get();
        }

        // Print the total sum of the results.
        System.out.println("Sum is: " + sum);

        // Shut down the ExecutorService.
        // No new tasks will be accepted, and it will shut down after all running tasks finish.
        exSrvc.shutdown();
    }
}
