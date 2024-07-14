package org.day4.behavior_parameterization.lambdas;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AnonymousClassExample {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("Apple", "Orange", "Banana");

        // Using an anonymous class to implement the Comparator interface
        Collections.sort(words, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });

        System.out.println(words);
    }
}
