package org.example;

// This class demonstrates the use of threads in Java to perform asynchronous computations.
public class BeforeCallable {

    // Inner class Computer, which is a Thread, used to perform a computation in a separate thread.
    static class Computer extends Thread {
        private int val; // Stores the result of the computation.
        private int param; // Input parameter for the computation.

        // Constructor to initialize the Computer thread with a specific parameter.
        public Computer(int p) {
            param = p;
        }

        // The run method is executed when the thread starts.
        // It performs the actual computation.
        public void run() {
            val = param * 2 + 1; // Simple computation: double the input and add one.
        }

        // Getter method to retrieve the result of the computation.
        public int getResult() {
            return val;
        }
    }

    // Main method - entry point of the program.
    public static void main(String[] args) {
        Computer c = new Computer(100); // Create a Computer object with parameter 100.
        Thread t = new Thread(c); // Wrap the Computer in a Thread object.
        t.start(); // Start the thread, which executes the run method of Computer.

        try {
            t.join(); // Wait for the thread to finish execution.
            // After the thread completes, print the result of the computation.
            System.out.println("Computed value: " + c.getResult());
        } catch (InterruptedException ex) {
            // Exception handling in case the thread is interrupted.
            // In this simple example, the catch block is empty.
        }
    }
}
