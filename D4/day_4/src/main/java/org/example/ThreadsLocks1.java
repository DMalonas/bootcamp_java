package org.example;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The Corruptor class implements Runnable and is designed to increment a shared static counter.
 * It uses a ReentrantLock to synchronize access to the counter to ensure thread safety.
 * Each instance of Corruptor takes a ReentrantLock object, ensuring that all instances
 * use the same lock for synchronization, thus avoiding race conditions.
 */
class Corruptor implements Runnable {
    public static int cnt = 0; // Shared variable among threads
    private ReentrantLock lck; // Lock for synchronization

    public Corruptor(ReentrantLock lck) {
        this.lck = lck; // Initializing the lock
    }

    public void run() {
        for (int i = 0; i < 1000; i++) {
            lck.lock(); // Acquiring the lock before accessing the shared variable
            try {
                cnt += 1; // Critical section: modifying the shared variable
            } finally {
                lck.unlock(); // Ensuring the lock is always released
            }
        }
    }
}

/**
 * The ThreadsLocks1 class demonstrates the use of ReentrantLock to synchronize access
 * to a shared static variable in a multi-threaded environment. It creates 1000 threads
 * where each thread runs an instance of Corruptor. All Corruptor instances share the same
 * ReentrantLock, ensuring that increments to the shared counter are thread-safe.
 * The expected outcome is a consistent final value of the counter after all threads have completed.
 */
public class ThreadsLocks1 {
    public static void main(String[] args) {
        ReentrantLock tlock = new ReentrantLock(); // Creating a lock
        ArrayList<Thread> c = new ArrayList<>(); // List to hold threads

        // Creating 1000 threads, each running a Corruptor instance with the same lock
        for (int i = 0; i < 1000; i++) {
            c.add(new Thread(new Corruptor(tlock)));
        }

        // Starting each thread
        for (int i = 0; i < 1000; i++) {
            c.get(i).start();
        }

        // Waiting for all threads to complete
        try {
            for (int i = 0; i < 1000; i++) {
                c.get(i).join();
            }
        } catch (InterruptedException ex) {
            // Handling InterruptedException
        }

        // Printing the final value of the shared variable
        System.out.println("Shared var is: " + Corruptor.cnt);
        // Expected output: "Shared var is: 1000000"
    }
}
