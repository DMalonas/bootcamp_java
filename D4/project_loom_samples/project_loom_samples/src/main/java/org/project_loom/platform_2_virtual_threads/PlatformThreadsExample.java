package org.project_loom.platform_2_virtual_threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PlatformThreadsExample {

    public static void main(String[] args) {
        // Number of tasks to process
        int numberOfTasks = 1000;

        // Create a fixed thread pool executor
        ExecutorService executor = Executors.newFixedThreadPool(10);

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

    private static int processData(int taskId) {
        // Simulate some processing work
        return taskId * 2;
    }
}
