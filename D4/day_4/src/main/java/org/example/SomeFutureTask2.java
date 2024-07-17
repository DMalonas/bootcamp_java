package org.example;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Demonstrates the use of FutureTask for asynchronous computation in Java.
 * This class creates a FutureTask to calculate the sum of numbers from 0 to 1,000,000.
 * It uses a separate thread to execute the task and periodically checks for completion
 * without blocking the main thread.
 */
public class SomeFutureTask2 {
    public static void main(String[] args) {
        // Create a FutureTask for a long-running computation.
        // The lambda expression calculates the sum of numbers from 0 to 1,000,000.
        FutureTask<Long> task = new FutureTask<>( () -> {
            long sum = 0;
            for(int i = 0; i <= 1000000; i++) {
                sum += i; // Accumulate the sum.
            }
            return sum; // Return the computed sum.
        });

        try {
            // Create and start a new Thread to execute the FutureTask.
            var t = new Thread(task);
            t.start(); // Start the thread, which begins executing the task.

            // Loop to check if the task is complete.
            while(!task.isDone()) {
                System.out.println("not yet"); // Print "not yet" while the task is still running.
                Thread.sleep(1); // Sleep for a short duration before checking again.
            }

            // Once the task is complete, retrieve and print the result.
            // The get() method blocks if the task is not yet complete, but in this case,
            // we know it's complete because of the while loop condition.
            System.out.println("The result is: " + task.get());
        } catch (ExecutionException | InterruptedException eex) {
            // Handle possible exceptions:
            // ExecutionException: thrown if the computation threw an exception.
            // InterruptedException: thrown if the current thread was interrupted.
        }
    }
}
