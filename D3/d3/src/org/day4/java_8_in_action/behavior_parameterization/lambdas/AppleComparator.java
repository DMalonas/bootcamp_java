package org.day4.java_8_in_action.behavior_parameterization.lambdas;

import org.day4.java_8_in_action.behavior_parameterization.Apple;

import java.util.Comparator;

public class AppleComparator {

    public static final Comparator<Apple> byWeight = new Comparator<Apple>() {
        @Override
        public int compare(Apple a1, Apple a2) {
            return Double.compare(a1.getWeight(), a2.getWeight());
        }
    };

    public static final Comparator<Apple> byWeightButWithLambda =
            (Apple a1, Apple a2) -> Double.compare(a1.getWeight(), a2.getWeight());

    // Rest of the class code goes here...
}
