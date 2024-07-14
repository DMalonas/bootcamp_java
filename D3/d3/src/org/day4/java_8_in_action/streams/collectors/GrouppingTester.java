package org.day4.java_8_in_action.streams.collectors;


import org.day4.java_8_in_action.model.CaloricLevel;
import org.day4.java_8_in_action.model.ComplexDish;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class GrouppingTester {

    public static void main(String[] args) {
        List<ComplexDish> menu = new ArrayList<>();

        // Add some Dish objects to the ArrayList
        menu.add(new ComplexDish("Pasta", true, 350, ComplexDish.Type.OTHER));
        menu.add(new ComplexDish("Salmon", false, 450, ComplexDish.Type.FISH));
        menu.add(new ComplexDish("Burger", false, 700, ComplexDish.Type.MEAT));
        menu.add(new ComplexDish("Salad", true, 200, ComplexDish.Type.OTHER));
        menu.add(new ComplexDish("Chicken", false, 500, ComplexDish.Type.MEAT));
        menu.add(new ComplexDish("French Fries", true, 530, ComplexDish.Type.OTHER));

        //6.3. Grouping
        Map<ComplexDish.Type, List<ComplexDish>> dishesByType =
                menu.stream().collect(groupingBy(ComplexDish::getType));


        Map<CaloricLevel, List<ComplexDish>> dishesByCaloricLevel = menu.stream().collect(
                groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return
                            CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                } ));


        // Perform multilevel grouping of dishes by type and caloric level.
        Map<ComplexDish.Type, Map<ComplexDish.CaloricLevel, List<ComplexDish>>> dishesByTypeCaloricLevel = menu.stream()
                .collect(
                        Collectors.groupingBy(ComplexDish::getType,                  // First-level classification function by Type
                                Collectors.groupingBy(dish -> {                   // Second-level classification function by CaloricLevel
                                    // Determine the caloric level of the dish based on the number of calories.
                                    if (dish.getCalories() <= 400) return ComplexDish.CaloricLevel.DIET;
                                    else if (dish.getCalories() <= 700) return ComplexDish.CaloricLevel.NORMAL;
                                    else return ComplexDish.CaloricLevel.FAT;
                                })
                        )
                );

        Map<ComplexDish.Type, Long> typesCount = menu.stream().collect(
                groupingBy(ComplexDish::getType, counting()));


        Map<ComplexDish.Type, Optional<ComplexDish>> mostCaloricByType =
                menu.stream()
                        .collect(groupingBy(ComplexDish::getType,
                                maxBy(comparingInt(ComplexDish::getCalories))));


        // Create a map of Dish.Type to Dish that contains the highest-calorie Dish of each type.
        Map<ComplexDish.Type, ComplexDish> mostCaloricByType2 = menu.stream()
                .collect(
                        Collectors.groupingBy(ComplexDish::getType,  // Classification function: groups dishes by their type.
                                Collectors.collectingAndThen(
                                        Collectors.maxBy(Comparator.comparingInt(ComplexDish::getCalories)), // Transformation function: finds max calorie dish.
                                        Optional::get  // Wrapped collector: extracts the value from the Optional.
                                )
                        )
                );

        mostCaloricByType2.forEach((type, dish) -> System.out.println(type + ": " + dish.getCalories()));


        // Now print the result
        for (Map.Entry<ComplexDish.Type, ComplexDish> entry : mostCaloricByType2.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getCalories());
        }


        Map<ComplexDish.Type, Integer> totalCaloriesByType =
                menu.stream().collect(groupingBy(ComplexDish::getType,
                        summingInt(ComplexDish::getCalories)));














        //Iterative
        // Map to hold the most caloric Dish by Type
        Map<ComplexDish.Type, ComplexDish>  mostCaloricByTypeImperative = new HashMap<>();

        // Iterate over the list of dishes
        for (ComplexDish dish : menu) {
            ComplexDish.Type type = dish.getType();
            Optional<ComplexDish> mostCaloricDishImp = mostCaloricByType.get(type);

            // If we have no entry for this type yet, or if the current dish has more calories,
            // we put it in the map as the most caloric Dish of this type
            if (mostCaloricDishImp == null || dish.getCalories() > mostCaloricDishImp.get().getCalories()) {
                mostCaloricByType.put(type, Optional.of(dish));
            }
        }

        // Print the most caloric Dish for each type
        for (Map.Entry<ComplexDish.Type, Optional<ComplexDish>> entry : mostCaloricByType.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().get().getCalories());
        }



        //declarative
        Map<ComplexDish.Type, ComplexDish> mostCaloricByTypeDeclarative = menu.stream()
                // Begin streaming the list of dishes
                .collect(
                        // Group dishes by their type. This results in a Map<ComplexDish.Type, List<ComplexDish>>
                        Collectors.groupingBy(ComplexDish::getType,
                                // After grouping, for each type, apply the downstream collector
                                Collectors.collectingAndThen(
                                        // Find the max calorie dish using a comparator that compares dishes based on calories
                                        Collectors.maxBy(Comparator.comparingInt(ComplexDish::getCalories)),
                                        // After finding the max, get it from the Optional wrapper. This assumes there is always a dish present.
                                        Optional::get)));


        totalCaloriesByType =
                menu.stream().collect(groupingBy(ComplexDish::getType,
                        summingInt(ComplexDish::getCalories)));



        Map<ComplexDish.Type, Set<CaloricLevel>>  caloricLevelsByType =
                menu.stream().collect(
                        groupingBy(ComplexDish::getType, mapping(
                                dish -> { if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                                else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                else return CaloricLevel.FAT; },
                                toSet() )));


        // Print out the map
        caloricLevelsByType.forEach((type, caloricLevels) -> {
            System.out.println(type + ": " + caloricLevels);
        });

        // Print out the map iteratively
        for (Map.Entry<ComplexDish.Type, Set<CaloricLevel>> entry : caloricLevelsByType.entrySet()) {
            ComplexDish.Type type = entry.getKey();
            Set<CaloricLevel> caloricLevels = entry.getValue();
            System.out.println(type + ": " + caloricLevels);
        }

    }
}
