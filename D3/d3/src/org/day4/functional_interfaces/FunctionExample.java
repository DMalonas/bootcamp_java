package org.day4.functional_interfaces;

import java.util.function.Function;

public class FunctionExample {
    public static void main(String[] args) {
        // Creating a function to convert a String to its length
        Function<String, Integer> stringLengthFunction = String::length;

        // Example usage of the function
        String exampleString = "Hello, World!";
        int length = stringLengthFunction.apply(exampleString);

        // Printing the result
        System.out.println("The length of '" + exampleString + "' is: " + length);
    }
}
