package org.day4.functional_interfaces;

import java.util.function.BiFunction;

public class BiFunctionExample {
    public static void main(String[] args) {
        // Create a BiFunction that takes two Integers and returns their sum as an Integer
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;

        // Use the BiFunction to add two numbers
        int result = add.apply(10, 20);

        // Print the result
        System.out.println("The sum is: " + result);

        // Another example: Concatenating two strings
        BiFunction<String, String, String> concatenate = (str1, str2) -> str1 + str2;
        String combinedString = concatenate.apply("Hello, ", "World!");

        // Print the concatenated string
        System.out.println(combinedString);
    }
}
