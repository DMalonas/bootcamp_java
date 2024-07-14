package org.d3.practice.singleton_pattern;

public class Singleton {

    // The volatile keyword ensures that multiple threads handle the uniqueInstance variable correctly
    private static volatile Singleton uniqueInstance;

    // Private constructor to avoid client applications to use constructor
    private Singleton() {}

    // Static method to get the singleton instance, double lock approach
    public static Singleton getInstance() {
        if (uniqueInstance == null) { // First check (no locking)
            /**
             * Lock and Second Check: If the instance is null, it's necessary to synchronize.
             * After acquiring the lock, a second check is performed to ensure that no other thread created
             * the instance in the period between the first check and acquiring the lock.
             * This second check is crucial because another thread might have acquired the lock first and created the instance.
             */
            synchronized (Singleton.class) {
                if (uniqueInstance == null) { // Second check (with locking)
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }

    // Example method to be called on the singleton instance
    public void displayMessage() {
        System.out.println("Singleton instance: " + this);
    }
}
