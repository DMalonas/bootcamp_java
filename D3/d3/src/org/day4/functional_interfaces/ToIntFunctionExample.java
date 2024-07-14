package org.day4.functional_interfaces;

import java.util.function.ToIntFunction;

public class ToIntFunctionExample {
    public static void main(String[] args) {
        // Create a ToIntFunction that takes a String and returns its length
        ToIntFunction<String> stringLength = String::length;

        // Apply the ToIntFunction to a string
        int length = stringLength.applyAsInt("Hello, world!");

        // Print the length of the string
        System.out.println("Length of 'Hello, world!' is: " + length);
    }
}
