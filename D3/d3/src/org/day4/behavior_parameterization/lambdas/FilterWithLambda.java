package org.day4.behavior_parameterization.lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FilterWithLambda {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Filter numbers greater than 5
        List<Integer> filtered = filter(numbers, number -> number > 5);
        
        // Print the filtered list
        System.out.println("Numbers greater than 5: " + filtered);
    }

    // Method to filter list based on a condition defined by Predicate
    public static <T> List<T> filter(List<T> list, Predicate<T> condition) {
        List<T> result = new ArrayList<>();
        for (T element : list) {
            if (condition.test(element)) {
                result.add(element);
            }
        }
        return result;
    }
}
