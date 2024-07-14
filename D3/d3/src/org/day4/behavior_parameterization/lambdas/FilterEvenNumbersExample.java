package org.day4.behavior_parameterization.lambdas;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterEvenNumbersExample {

    public static void main(String[] args) {
        // Initialize a list of integers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Define a Predicate lambda expression to check if a number is even
        Predicate<Integer> isEvenPredicate = n -> n % 2 == 0;

        // Filter the list of numbers using the stream API and the predicate
        List<Integer> evenNumbers = numbers.stream()  // Convert the list to a stream
                                           .filter(isEvenPredicate)  // Apply the predicate to filter the stream
                                           .collect(Collectors.toList());  // Collect the results back into a list

        // Print out the even numbers
        System.out.println("Even Numbers: " + evenNumbers);
    }
}
