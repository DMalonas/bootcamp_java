package org.project_loom.platform_2_virtual_threads.context_switching;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualThreadApp {

    public static void main(String[] args) throws Exception {
        // Define tasks
        List<String> filesToRead = List.of("file1.txt", "file2.txt", "file3.txt");
        List<String> urlsToFetch = List.of("https://example.com", "https://example.org", "https://example.net");

        // Number of iterations
        int iterations = 100;
        long totalTime = 0;

        for (int i = 0; i < iterations; i++) {
            long startTime = System.currentTimeMillis();

            // Create a virtual thread per task executor
            ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

            // Submit file reading tasks
            for (String filePath : filesToRead) {
                executor.submit(() -> {
                    try {
                        readFile(filePath);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

            // Submit URL fetching tasks
            for (String url : urlsToFetch) {
                executor.submit(() -> {
                    try {
                        fetchUrl(url);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

            // Shutdown the executor and await termination
            executor.shutdown();
            executor.awaitTermination(1, java.util.concurrent.TimeUnit.HOURS);

            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            totalTime += executionTime;

            System.out.println("Iteration " + (i + 1) + " execution time: " + executionTime + "ms");
        }

        System.out.println("Average execution time: " + (totalTime / iterations) + "ms");
    }

    private static void readFile(String filePath) throws Exception {
        Path path = Paths.get(filePath);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Read from " + filePath + ": " + line);
            }
        }
    }

    private static void fetchUrl(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Fetched from " + urlString + ": " + line);
            }
        }
    }
}
