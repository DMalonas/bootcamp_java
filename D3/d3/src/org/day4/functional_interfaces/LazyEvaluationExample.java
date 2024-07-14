package org.day4.functional_interfaces;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class LazyEvaluationExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        Stream<String> nameStream = names.stream()
            .filter(name -> {
                System.out.println("Filtering: " + name);
                return name.startsWith("A");
            })
            .map(name -> {
                System.out.println("Mapping: " + name);
                return name.toUpperCase();
            });

        System.out.println("Stream created, no operations have been performed yet.");

        // The terminal operation triggers the actual processing
        String firstMatch = nameStream.findFirst().orElse(null);
        System.out.println("First match: " + firstMatch);
    }
}
