package org.example;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The Atomic class uses an AtomicInteger for thread-safe increment operations.
 * It provides an 'inc' method to increment the AtomicInteger instance 'cnt' 1000 times.
 * The AtomicInteger ensures that each increment operation is atomic and thread-safe.
 * It also provides a 'getVal' method to retrieve the current value of 'cnt'.
 */
class Atomic {
    AtomicInteger cnt = new AtomicInteger(0);

    public void inc() {
        for (int i = 0; i < 1000; i++) {
            // Two ways to increment the atomic integer
            // cnt.incrementAndGet(); // or
            cnt.updateAndGet((x) -> x + 1);
        }
    }

    public int getVal() { return cnt.get(); }
}

/**
 * ThreadsLocksAtomic0 demonstrates the use of the Atomic class in a multi-threaded context.
 * It creates 1000 threads, each calling the 'inc' method on a single instance of Atomic.
 * This ensures that concurrent increments to the AtomicInteger are thread-safe.
 * 
 * Expected Outcome:
 * The final output should consistently be "Shared var is: 1000000",
 * demonstrating the effectiveness of AtomicInteger in ensuring atomicity of operations.
 */
public class ThreadsLocksAtomic0 {
    public static void main(String[] args) {
        ArrayList<Thread> c = new ArrayList<Thread>();
        Atomic s = new Atomic();

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

        System.out.println("Shared var is: " + s.getVal());
    }
}
