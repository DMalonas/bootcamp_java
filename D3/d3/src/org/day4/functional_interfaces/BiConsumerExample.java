package org.day4.functional_interfaces;

import java.util.function.BiConsumer;

public class BiConsumerExample {
    public static void main(String[] args) {
        // Create a BiConsumer that takes two Strings and prints their concatenation
        BiConsumer<String, String> printConcatenation = (str1, str2) -> {
            String concatenated = str1 + str2;
            System.out.println(concatenated);
        };

        // Use the BiConsumer with two strings
        printConcatenation.accept("Hello, ", "world!");

        // Another example: A BiConsumer that adds two numbers and prints the result
        BiConsumer<Integer, Integer> printSum = (num1, num2) -> {
            int sum = num1 + num2;
            System.out.println("Sum: " + sum);
        };

        // Use the BiConsumer to add two numbers
        printSum.accept(10, 20);
    }
}
