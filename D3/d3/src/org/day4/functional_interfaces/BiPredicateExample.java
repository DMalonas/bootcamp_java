package org.day4.functional_interfaces;

import java.util.function.BiPredicate;

public class BiPredicateExample {
    public static void main(String[] args) {
        // Create a BiPredicate to check if the sum of two integers is positive
        BiPredicate<Integer, Integer> isSumPositive = (num1, num2) -> (num1 + num2) > 0;

        // Test the BiPredicate with two numbers
        boolean result1 = isSumPositive.test(10, -5);
        boolean result2 = isSumPositive.test(-7, -8);

        // Print the results
        System.out.println("Is sum of 10 and -5 positive? " + result1);  // true
        System.out.println("Is sum of -7 and -8 positive? " + result2); // false
    }
}
