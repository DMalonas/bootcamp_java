package org.project_loom;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * This class demonstrates handling I/O operations using virtual threads.
 * It includes reading from a file and simulating a network socket operation,
 * highlighting the non-blocking nature and efficiency of virtual threads in I/O-bound tasks.
 */
public class VirtualThreadIOExample2 {

    public static void main(String[] args) {
        // Create a virtual thread per task executor
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        // Task for reading from a file
        Runnable fileReadTask = () -> {
            Path path = Paths.get("example.txt");
            try {
                String content = Files.readString(path);
                System.out.println("File content: " + content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        // Task for simulating a network socket operation
        Runnable networkTask = () -> {
            try {
                // Simulate network delay
                Thread.sleep(2000);
                System.out.println("Network operation completed.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        // Submit tasks to the executor
        executor.submit(fileReadTask);
        executor.submit(networkTask);

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

