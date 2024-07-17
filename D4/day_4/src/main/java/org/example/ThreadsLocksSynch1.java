package org.example;

import java.util.ArrayList;

/**
 * The Synch class contains a synchronized method 'inc' to safely increment a shared static variable.
 * The use of the synchronized keyword ensures that access to this method is mutually exclusive,
 * thus preventing race conditions in a multi-threaded environment.
 */
class Synch {
    public static int cnt = 0;

    synchronized void inc() {
        for (int i = 0; i < 1000; i++) {
            cnt += 1;
        }
    }
}

/**
 * The ThreadsLocksSynch1 class demonstrates the use of synchronized methods for thread safety.
 * It creates 1000 threads, each of which calls the 'inc' method of a single Synch instance.
 * The synchronization in 'inc' ensures that the increments to 'cnt' are thread-safe.
 * 
 * Expected Outcome:
 * The final output should consistently be "Shared var is: 1000000", indicating that the 
 * synchronized method successfully prevented race conditions.
 */
public class ThreadsLocksSynch1 {
    public static void main(String[] args) {
        ArrayList<Thread> c = new ArrayList<Thread>();
        Synch s = new Synch();

        for (int i = 0; i < 1000; i++) {
            c.add(new Thread(() -> { s.inc(); }));
        }

        for (int i = 0; i < 1000; i++) {
            c.get(i).start();
        }

        try {
            for (int i = 0; i < 1000; i++) {
                c.get(i).join();
            }
        } catch (InterruptedException ex) {
            // InterruptedException handling
        }

        System.out.println("Shared var is: " + Synch.cnt);
    }
}
