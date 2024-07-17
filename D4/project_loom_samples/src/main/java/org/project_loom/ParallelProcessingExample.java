package org.project_loom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * This class demonstrates parallel processing using virtual threads.
 * It creates a large number of data processing tasks and executes them concurrently
 * using virtual threads, highlighting scalability and efficiency gains.
 */
public class ParallelProcessingExample {

    public static void main(String[] args) {
        // Number of tasks to process
        int numberOfTasks = 1000;

        // Create a virtual thread per task executor
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        // List to hold the tasks
        List<Callable<Integer>> tasks = new ArrayList<>();

        // Create and add tasks to the list
        for (int i = 0; i < numberOfTasks; i++) {
            final int taskId = i;
            tasks.add(() -> {
                // Simulate data processing task
                int result = processData(taskId);
                System.out.println("Task " + taskId + " processed with result: " + result);
                return result;
            });
        }

        try {
            // Submit all tasks to the executor and get the futures
            List<Future<Integer>> futures = executor.invokeAll(tasks);

            // Handle the results of the tasks
            for (Future<Integer> future : futures) {
                try {
                    // Get and print the result of each task
                    System.out.println("Result: " + future.get());
                } catch (Exception e) {
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

    /**
     * Simulate a data processing task.
     *
     * @param taskId the ID of the task
     * @return the result of processing
     */
    private static int processData(int taskId) {
        // Simulate some processing work
        return taskId * 2;
    }
}
