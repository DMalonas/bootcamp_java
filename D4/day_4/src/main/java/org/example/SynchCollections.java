package org.example;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * The SynchCollections class demonstrates the use of synchronized collections
 * in Java. It showcases how to make a List thread-safe using Collections.synchronizedList
 * and highlights the importance of external synchronization during iteration.
 *
 * Key Points:
 * - Collections.synchronizedList is used to wrap a LinkedList in a synchronized list.
 * - Multiple threads perform concurrent additions to the list.
 * - External synchronization is needed when iterating over the synchronized collection
 *   to avoid concurrent modification exceptions and ensure thread safety.
 */
public class SynchCollections {
    public static void main(String[] args) {
        // Wrapping a LinkedList in a synchronized list
        List<Integer> lst = Collections.synchronizedList(new LinkedList<>());

        // Creating and starting multiple threads for list modification
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(() -> {
                for (int q = 1; q <= 10; q++) {
                    lst.add(q);
                }
            });
            t.start();
        }

        // Synchronization is required for safe iteration over the list
        int transientSum = 0;
        synchronized (lst) {
            for (Integer i : lst) {
                transientSum += i;
            }
        }

        // Printing the sum of list elements
        System.out.println(transientSum);
    }
}
