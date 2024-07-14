package org.day4.java_8_in_action.streams;

import org.day4.java_8_in_action.model.Dish;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class DishProcessor {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(new Dish("meat", 200), new Dish("fish", 190),
                new Dish("vegetable", 220)); //new ArrayList<>(); // Assume 'menu' is initialized with Dish objects

        // Filter the elements using an accumulator.
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for (Dish d : menu) {
            if (d.getCalories() < 400) {
                lowCaloricDishes.add(d);
            }
        }

        // Sort the dishes with an anonymous class.
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            public int compare(Dish d1, Dish d2) {
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });

        // Process the sorted list to select the names of dishes.
        List<String> lowCaloricDishesName = new ArrayList<>();
        for (Dish d : lowCaloricDishes) {
            lowCaloricDishesName.add(d.getName());
        }

        lowCaloricDishesName =
                menu.parallelStream()
                        .filter(d -> d.getCalories() < 400)
                        .sorted(comparing(Dish::getCalories))
                        .map(Dish::getName)
                        .collect(toList());


        int calories = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);


        // Calculate the sum of calories
        int totalCalories = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();


    }
}
