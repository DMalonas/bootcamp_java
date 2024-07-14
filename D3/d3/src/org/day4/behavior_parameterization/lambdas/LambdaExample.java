package org.day4.behavior_parameterization.lambdas;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LambdaExample {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("Apple", "Orange", "Banana");

        // Using a lambda expression to implement the Comparator interface
        Collections.sort(words, (s1, s2) -> s1.compareTo(s2));

        System.out.println(words);
    }
}
