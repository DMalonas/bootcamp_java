package org.day4.functional_interfaces;

import java.util.function.IntFunction;

public class IntFunctionExample {
    public static void main(String[] args) {
        // Create an IntFunction that converts an int to a String
        IntFunction<String> intToString = num -> "Number: " + num;

        // Apply the IntFunction to an int value
        String result = intToString.apply(123);

        // Print the result
        System.out.println(result); // Output will be "Number: 123"
    }
}
