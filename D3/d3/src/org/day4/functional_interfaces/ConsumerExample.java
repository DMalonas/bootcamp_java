package org.day4.functional_interfaces;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerExample {
    public static void main(String[] args) {
        // Creating a list of Strings
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        // Creating a Consumer to print a String
        Consumer<String> printConsumer = System.out::println; //method reference

        // Using the Consumer in a forEach loop to print each name in the list
        names.forEach(printConsumer);
    }
}
