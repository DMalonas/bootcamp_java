package org.example;

import java.util.concurrent.ConcurrentHashMap;

/**
 * The MapSet1 class demonstrates the use of ConcurrentHashMap for managing
 * concurrent operations in a multi-threaded context. It features producers
 * that populate the map and set concurrently and a consumer that performs
 * aggregation and search operations on these structures.
 *
 * Key Points:
 * - ConcurrentHashMap is used to ensure thread safety during concurrent modifications.
 * - Producer threads populate the map and set with key-value pairs.
 * - The consumer thread performs a reduce operation on the map and searches a key in the set.
 *
 * Expected Behavior:
 * - The map's values are summed up, and the result is printed.
 * - The search operation in the set looks for a specific key and prints the result.
 */
public class MapSet1 {
    public static void main(String[] args) throws InterruptedException {
        // Initializing concurrent collections
        var map = new ConcurrentHashMap<String, Integer>();

        // Creating and starting producer threads
        for (int i = 0; i < 10; i++) {
            final int vf = i; // Variable for use in lambda
            Thread t = new Thread(() -> {
                for (int k = 0; k < 1000; k++) {
                    var x = vf * 1000 + k;
                    // Populating map and set
                    map.put("Map" + x, x);
                }
            });
            t.start();
        }

        // Short pause to let some data accumulate
        Thread.sleep(10);

        // Creating and starting a consumer thread
        Thread c = new Thread(() -> {
            // Aggregating values from the map
            long res = map.reduceValues(2, (x, y) -> x + y);
            System.out.println("Map res: " + res);


        });
        c.start();
        c.join();
    }
}
