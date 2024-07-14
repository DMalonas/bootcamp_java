package org.day4.behavior_parameterization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorAnonymousClassExample {
    public static void main(String[] args) {
        // Creating a list of strings
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Orange");
        fruits.add("Banana");

        // Using an anonymous class to implement the Comparator interface
        Comparator<String> lengthComparator = new Comparator<String>() {
            @Override
            public int compare(String fruit1, String fruit2) {
                return Integer.compare(fruit1.length(), fruit2.length());
            }
        };

        // Sorting the list using the lengthComparator
        Collections.sort(fruits, lengthComparator);

        // Printing the sorted list
        for (String fruit : fruits) {
            System.out.println(fruit);
        }
    }
}
