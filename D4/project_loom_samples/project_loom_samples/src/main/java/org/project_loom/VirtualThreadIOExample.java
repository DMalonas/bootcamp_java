package org.project_loom;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class demonstrates handling I/O operations using virtual threads.
 * It includes reading from a file and simulating a network socket operation,
 * highlighting the non-blocking nature and efficiency of virtual threads in I/O-bound tasks.
 */
public class VirtualThreadIOExample {

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

        // Shutdown the executor
        executor.shutdown();
    }
}
