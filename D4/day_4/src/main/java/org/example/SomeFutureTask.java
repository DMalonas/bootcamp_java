package org.example;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class SomeFutureTask {
    public static void main(String[] args) {
        // Create a FutureTask to perform a long-running computation asynchronously.
        // The lambda expression defines the task, which calculates the sum of numbers from 0 to 9999.
        FutureTask<Long> task = new FutureTask<>( () -> {
            long sum = 0;
            for(int i = 0; i < 10000; i++) {
                sum += i; // Accumulate the sum.
            }
            return sum; // Return the computed sum.
        });

        try {
            // Create a new Thread to execute the FutureTask.
            var t = new Thread(task);
            t.start(); // Start the thread, which begins executing the task.

            // Wait for the task to complete and retrieve the result.
            // The get() method blocks until the task is complete.
            boolean done = task.isDone();
//            if (done) {
//
//            } else {
//
//            }
            System.out.println("The result is: " + task.get());
        } catch (ExecutionException | InterruptedException eex) {
            // Handle possible exceptions:
            // ExecutionException: thrown if the computation threw an exception.
            // InterruptedException: thrown if the current thread was interrupted while waiting.
        }
    }
}
