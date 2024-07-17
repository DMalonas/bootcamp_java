package org.project_loom;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * This class demonstrates how to handle exceptions and errors in virtual threads.
 * It shows how exceptions are propagated and handled when using virtual threads with executors.
 */
public class VirtualThreadErrorHandlingExample {

    public static void main(String[] args) {
        // Create a virtual thread per task executor
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        // Task that throws an exception
        Runnable errorTask = () -> {
            try {
                System.out.println("Starting errorTask in virtual thread...");
                throw new RuntimeException("An error occurred in errorTask!");
            } catch (RuntimeException e) {
                // Handle exception within the task
                System.err.println("Caught exception in errorTask: " + e.getMessage());
            }
        };

        // Submit the task to the executor
        executor.submit(errorTask);

        // Shutdown the executor gracefully
        executor.shutdown();
        try {
            // Wait for all tasks to finish
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
        
        System.out.println("All tasks completed.");
    }
}
