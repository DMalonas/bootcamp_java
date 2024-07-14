package org.day4.functional_interfaces;

import java.util.function.IntPredicate;

public class IntPredicateExample {
    public static void main(String[] args) {
        // Create an IntPredicate to check if an integer is even
        IntPredicate isEven = num -> num % 2 == 0;

        // Test the IntPredicate with a couple of numbers
        boolean result1 = isEven.test(4); // true, since 4 is even
        boolean result2 = isEven.test(5); // false, since 5 is odd

        // Print the results
        System.out.println("Is 4 even? " + result1);
        System.out.println("Is 5 even? " + result2);
    }
}
