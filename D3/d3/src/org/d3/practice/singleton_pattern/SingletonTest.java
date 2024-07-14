package org.d3.practice.singleton_pattern;

public class SingletonTest {

    public static void main(String[] args) {
        //Create a Runnable
        //This Runnable task retrieves the Singleton instance and prints its Hashcode
        //Printing the Hashcode as a way to verify that the same instance is used across different threads
        Runnable task = () -> {
            Singleton singletonInstance = Singleton.getInstance();  //The singleton implementation maintains a single instance across different threads
            System.out.println("Singleton instance hashcode: " + singletonInstance.hashCode());
        };

        // Create multiple threads and start them and each one of them will execute the same task
        //
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        Thread thread3 = new Thread(task);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}


