package org.day4.java_8_in_action.streams.collectors;

import org.day4.java_8_in_action.model.ComplexDish;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class PartitioningTester {

    public static void main(String[] args) {

        List<ComplexDish> menu = new ArrayList<>();

        // Add some Dish objects to the ArrayList
        menu.add(new ComplexDish("Pasta", true, 350, ComplexDish.Type.OTHER));
        menu.add(new ComplexDish("Salmon", false, 450, ComplexDish.Type.FISH));
        menu.add(new ComplexDish("Burger", false, 700, ComplexDish.Type.MEAT));
        menu.add(new ComplexDish("Salad", true, 200, ComplexDish.Type.OTHER));
        menu.add(new ComplexDish("Chicken", false, 500, ComplexDish.Type.MEAT));
        menu.add(new ComplexDish("French Fries", true, 530, ComplexDish.Type.OTHER));

        Map<Boolean, List<ComplexDish>> partitionedMenu = menu.stream()
                .collect(partitioningBy(ComplexDish::isVegetarian));


        Map<Boolean, Map<ComplexDish.Type, List<ComplexDish>>> vegetarianDishesByType = menu.stream()
                // Begin streaming the list of dishes
                .collect(
                        // First partition the stream into vegetarian and non-vegetarian dishes
                        partitioningBy(ComplexDish::isVegetarian,
                                // Then group each partition by the type of the dishes
                                Collectors.groupingBy(ComplexDish::getType)
                        )
                );


        Map<Boolean, ComplexDish> mostCaloricPartitionedByVegetarian =
                menu.stream().collect(
                        partitioningBy(ComplexDish::isVegetarian,
                                collectingAndThen(
                                        maxBy(comparingInt(ComplexDish::getCalories)),
                                        Optional::get)));
    }
}
