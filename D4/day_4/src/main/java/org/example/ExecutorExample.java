package org.example;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorExample {
    public static void main(String[] args) throws Exception {
        // Create an ExecutorService with a fixed thread pool of 2 threads.
        ExecutorService thrPool = Executors.newFixedThreadPool(2);

        // LinkedList to store Future objects representing the results of submitted tasks.
        LinkedList<Future<Integer>> res = new LinkedList<>();

        // Submit 10 tasks to the thread pool.
        for(int i = 0; i < 10; i++) {
            final var finalI = i; // Final variable for use in the lambda expression.
            // Submit a Callable task that returns an Integer.
            var f = thrPool.submit( () -> {
                // Print the name of the thread executing this task.
                System.out.println("Running in thread: " + Thread.currentThread().getName());
                // Return a computed value based on the loop index.
                return finalI * 100 + 1;
            });
            // Add the Future object returned by submit() to the list.
            res.add(f);
        }

        // Calculate the sum of the results of all tasks.
        int sum = 0;
        for(Future<Integer> fint : res) {
            // Wait for the task to complete and retrieve its result.
            sum += fint.get();
        }

        // Print the total sum of the results.
        System.out.println("Sum is " + sum);

        // Shut down the ExecutorService.
        // No new tasks will be accepted, and it will shut down after all running tasks finish.
        thrPool.shutdown();
    }
}
