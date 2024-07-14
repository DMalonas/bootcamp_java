package org.day4.java_8_in_action.streams;

import java.util.function.IntSupplier;
import java.util.stream.IntStream;

public class Runner {
    public static void main(String[] args) {
        // Create an IntSupplier instance for generating Fibonacci numbers.
        IntSupplier fib = new IntSupplier() {
            // The last two Fibonacci numbers are stored in 'previous' and 'current'.
            private int previous = 0;
            private int current = 1;

            // The getAsInt method defines how to supply the next Fibonacci number.
            @Override
            public int getAsInt() {
                // Store the current 'previous' value to return it later.
                int oldPrevious = this.previous;
                // Calculate the next Fibonacci number.
                int nextValue = this.previous + this.current;
                // Update 'previous' and 'current' to their new values.
                this.previous = this.current;
                this.current = nextValue;
                // Return the 'oldPrevious' which is the current number in the sequence.
                return oldPrevious;
            }
        };

        // Generate a stream of Fibonacci numbers using the 'fib' supplier.
        // Limit the sequence to the first 10 numbers.
        // Print each number in the sequence to the console.
        IntStream.generate(fib)
                 .limit(10)
                 .forEach(System.out::println);
    }
}
