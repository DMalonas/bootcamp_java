package org.example;

import java.util.concurrent.*;

public class AfterCallable {

    // Define a static inner class named Computer that implements Callable with a generic return type of Integer
    static class Computer implements Callable<Integer> {
        private int param; // Declare a private int variable named param

        // Constructor that accepts an integer parameter
        public Computer(int p) {
            param = p; // Assign the passed integer parameter to the instance variable param
        }

        // The call method that must be implemented for Callable, which returns an Integer
        public Integer call() throws Exception {
            // The computation logic, which is the parameter times two plus one, is returned here
            return param * 2 + 1;
        }
    }

    // The main method, which is the entry point of the program
    public static void main(String[] args) {
        // Create an ExecutorService object to manage threads, using a single thread executor for simplicity
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // Submit the Computer task to the executor, which expects a Callable object and returns a Future representing the pending result
        Future<Integer> future = executor.submit(new Computer(100));

        try {
            // Call get on the future to retrieve the result of the computation. This call blocks until the computation is complete.
            Integer result = future.get();
            // Print the result of the computation to the console
            System.out.println("Computed value: " + result);
        } catch (InterruptedException | ExecutionException e) {
            // Catch potential exceptions that could be thrown during the execution of the future.get() method
            e.printStackTrace(); // Print the stack trace of the exception to the console
        }

        // Shut down the executor service, which is a good practice to release system resources and allow the application to exit cleanly
        executor.shutdown();
    }
}

