package org.project_loom;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * This class demonstrates how to properly interrupt and cancel virtual threads.
 * It includes handling thread interruption and ensuring resources are cleaned up correctly.
 */
public class VirtualThreadInterruptionExample {

    public static void main(String[] args) {
        // Create a virtual thread per task executor
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        // Task that handles interruption
        Runnable interruptibleTask = () -> {
            try {
                System.out.println("Task started. Sleeping for 10 seconds...");
                // Simulate long-running task
                Thread.sleep(10000);
                System.out.println("Task completed.");
            } catch (InterruptedException e) {
                // Handle thread interruption
                System.err.println("Task was interrupted!");
                // Perform necessary cleanup
                cleanupResources();
            }
        };

        // Submit the task to the executor
        var future = executor.submit(interruptibleTask);

        // Cancel the task after 2 seconds
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Attempt to cancel the task
        future.cancel(true);

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

    /**
     * Perform necessary cleanup operations.
     */
    private static void cleanupResources() {
        System.out.println("Cleaning up resources...");
        // Add cleanup code here (e.g., closing files, releasing locks)
    }
}
