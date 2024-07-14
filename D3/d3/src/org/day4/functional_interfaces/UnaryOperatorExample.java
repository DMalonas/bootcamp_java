package org.day4.functional_interfaces;

import java.util.function.UnaryOperator;

public class UnaryOperatorExample {
    public static void main(String[] args) {
        // Create a UnaryOperator that doubles an Integer
        UnaryOperator<Integer> doubleNumber = n -> n * 2;

        // Apply the UnaryOperator to a number
        int result = doubleNumber.apply(5);

        // Print the result
        System.out.println("Double of 5 is: " + result);
    }
}
