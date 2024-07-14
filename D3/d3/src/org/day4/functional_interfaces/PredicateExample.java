package org.day4.functional_interfaces;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateExample {
    public static void main(String[] args) {
        // Creating a list of integers
        List<Integer> numbers = Arrays.asList(-10, -5, 0, 5, 10);

        // Creating a Predicate to check if a number is positive
        Predicate<Integer> isPositive = number -> number > 0;

        // Filtering the list using the Predicate
        List<Integer> positiveNumbers = numbers.stream()
            .filter(isPositive)
            .collect(Collectors.toList());

        // Printing the positive numbers
        System.out.println("Positive numbers: " + positiveNumbers);
    }
}
