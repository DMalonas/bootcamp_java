package org.project_loom;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * This class demonstrates the use of Executors.newVirtualThreadPerTaskExecutor() to manage and execute tasks
 * using virtual threads. It shows how to submit multiple tasks to this executor and handle their execution.
 */
public class VirtualThreadExecutorExample {

    public static void main(String[] args) {
        // Create a virtual thread per task executor
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        // Create a list of tasks
        List<Callable<String>> tasks = List.of(
                () -> {
                    Thread.sleep(1000);
                    return "Task 1 completed";
                },
                () -> {
                    Thread.sleep(2000);
                    return "Task 2 completed";
                },
                () -> {
                    Thread.sleep(3000);
                    return "Task 3 completed";
                }
        );

        try {
            // Submit tasks to the executor and get the futures
            List<Future<String>> futures = executor.invokeAll(tasks);

            // Handle the results of the tasks
            for (Future<String> future : futures) {
                try {
                    // Print the result of each task
                    System.out.println(future.get());
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Shutdown the executor
            executor.shutdown();
        }
        
        System.out.println("All tasks completed.");
    }
}
