package org.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The CondLocks class implements Runnable and demonstrates the use of 
 * ReentrantLocks and Conditions to control the execution order of threads.
 * Each thread increments a shared counter, but only after it reaches a 
 * specified start point (startAfter). The use of Conditions allows threads 
 * to efficiently wait for the counter to reach the start point before 
 * proceeding with incrementing.
 */
public class CondLocks implements Runnable {
    public static int cnt = 0; // Shared variable among threads
    private ReentrantLock lck; // Lock for synchronization
    private Condition cond;    // Condition for controlling thread execution order
    private int startAfter;    // The count value after which the thread starts processing

    public CondLocks(ReentrantLock lck, Condition c, int st) {
        this.lck = lck;
        this.cond = c;
        this.startAfter = st;
    }

    public void run() {
        for (int i = 0; i < 1000; i++) {
            lck.lock(); // Acquiring the lock
            try {
                // Wait until the shared counter reaches the startAfter value
                while (cnt < startAfter)
                    cond.await();

                // Increment the shared counter
                cnt += 1;

                // Notify all waiting threads
                cond.signalAll();
            } catch (InterruptedException iex) {
                // InterruptedException handling, if required
            } finally {
                lck.unlock(); // Releasing the lock
            }
        }
    }
}




class Main2 {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        // Create multiple threads with different startAfter values
        Thread t1 = new Thread(new CondLocks(lock, condition, 0)); // starts immediately
        Thread t2 = new Thread(new CondLocks(lock, condition, 500)); // starts after cnt reaches 500
        Thread t3 = new Thread(new CondLocks(lock, condition, 1000)); // starts after cnt reaches 1000

        // Start the threads
        t1.start();
        t2.start();
        t3.start();

        // Optionally, wait for all threads to finish
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the final value of the shared counter
        System.out.println("Final count: " + CondLocks.cnt);
    }
}
