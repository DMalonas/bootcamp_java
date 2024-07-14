package org.day4.java_8_in_action.behavior_parameterization;

import org.day4.java_8_in_action.behavior_parameterization.predicates.ApplePredicate;
import org.day4.java_8_in_action.behavior_parameterization.predicates.AppleRedAndHeavyPredicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class AppleParameterization {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        List<Apple> inventory = Arrays.asList(
                new Apple("red", 0.5),
                new Apple("blue", 0.4),
                new Apple("green", 0.3),
                new Apple("red", 0.7),
                new Apple("red", 0.2));
        List<Apple> greenApples = filterApplesByColor(inventory, "green");
        List<Apple> redApples = filterApplesByColor(inventory, "red");
         greenApples = filterApples(inventory, "green", 0, true);
        List<Apple> heavyApples = filterApples(inventory, "", 150, false);
        List<Apple> redAndHeavyApples =
                filterApples(inventory, new AppleRedAndHeavyPredicate());


        /**
         * With anonymous class
         */
        redApples = filterApples(inventory, new ApplePredicate() {
            public boolean test(Apple apple) {
                return "red".equals(apple.getColor());
            }
        });



        // using a lambda expression
        List<Apple> result =
                filterApples(inventory, (Apple apple) -> "red".equals(apple.getColor()));



        //using abstract types
        redApples = filter(inventory, (Apple apple) -> "red".equals(apple.getColor()));

        List<Integer> numbers = new ArrayList<>();
        numbers.add(2); numbers.add(3); numbers.add(4);
        List<Integer> evenNumbers = filter(numbers, (Integer i) -> i % 2 == 0);



       greenApples =
                filter(inventory, (Apple a) -> "green".equals(a.getColor()));

    }




    public static List<Apple> filterApplesByColor(List<Apple> inventory,
                                                  String color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory){
            if ( apple.getColor().equals(color) ) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApplesByWeight(List<Apple> inventory,
                                                   int weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory){
            if ( apple.getWeight() > weight ){
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApples(List<Apple> inventory, String color, int weight, boolean flag) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ((flag && apple.getColor().equals(color)) || (!flag && apple.getWeight() > weight)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }


    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T e : list) {
            if (p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }
}
