package org.day4.java_8_in_action.streams;

import org.day4.java_8_in_action.model.ComplexDish;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class DishProcessor2 {







        public static void main(String[] args) {

            List<String> title = Arrays.asList("Java8", "In", "Action");
            Stream<String> s = title.stream();
            s.forEach(System.out::println); // First terminal operation
//            s.forEach(System.out::println); // This will throw an IllegalStateException
            title.stream().forEach(System.out::println);
            title.stream().forEach(System.out::println);

            // Initialize the ArrayList of Dish objects
            List<ComplexDish> menu = new ArrayList<>();

            // Add some Dish objects to the ArrayList
            menu.add(new ComplexDish("Pasta", true, 350, ComplexDish.Type.OTHER));
            menu.add(new ComplexDish("Salmon", false, 450, ComplexDish.Type.FISH));
            menu.add(new ComplexDish("Burger", false, 700, ComplexDish.Type.MEAT));
            menu.add(new ComplexDish("Salad", true, 200, ComplexDish.Type.OTHER));
            menu.add(new ComplexDish("Chicken", false, 500, ComplexDish.Type.MEAT));
            menu.add(new ComplexDish("French Fries", true, 530, ComplexDish.Type.OTHER));

            // The 'menu' ArrayList is now populated with ComplexDish objects
            // You can use this ArrayList as needed in your code

            // External iteration using an iterator
            List<String> names = new ArrayList<>();
            Iterator<ComplexDish> iterator = menu.iterator();
            while (iterator.hasNext()) {
                ComplexDish d = iterator.next();
                names.add(d.getName()); // Add the name of each dish to the 'names' list
            }

            // Internal iteration using streams
            List<String> namesWithStreams = menu.stream() // Convert the 'menu' list to a stream
                    .map(ComplexDish::getName) // Map each Dish object to its name
                    .collect(toList()); // Collect the results into a list


            // Use a stream to process the ComplexDishes
             names = menu.stream() // Get a stream from the list of dishes
                    .filter(d -> d.getCalories() > 300) // Intermediate operation to filter ComplexDishes over 300 calories
                    .map(ComplexDish::getName) // Intermediate operation to get the name of each dish
                    .limit(3) // Intermediate operation to limit the results to the first 3 dishes
                    .collect(toList()); // Terminal operation to convert the stream into a list



            // Select only the first three high-caloric ComplexDishes and store the results in another list
            List<String> threeHighCaloricDishNames = menu.stream() // Get a stream from the menu list
                    .filter(d -> d.getCalories() > 300) // Create a pipeline of operations: first filter high-calorie ComplexDishes
                    .map(ComplexDish::getName) // Get the names of the dishes
                    .limit(3) // Select only the first three
                    .collect(toList()); // Store the results in another list

            // Print out the names of the three high-caloric dishes
            System.out.println(threeHighCaloricDishNames);




            // Use a stream to process and print the dishes
            names = menu.stream()
                    // Filter dishes with more than 300 calories
                    .filter(d -> {
                        System.out.println("filtering: " + d.getName()); // Printing the dish as it's being filtered
                        return d.getCalories() > 300;
                    })
                    // Map each Dish to its name
                    .map(d -> {
                        System.out.println("mapping: " + d.getName()); // Printing the dish as you extract its name
                        return d.getName();
                    })
                    // Limit to the first three dishes
                    .limit(3)
                    // Collect the results into a list
                    .collect(Collectors.toList());

            // Print the final list of names
            System.out.println(names);


            /*************** TERMINAL OPERATIONS **************/

            menu.stream().forEach(System.out::println);


            long count = menu.stream()
                    .filter(d -> d.getCalories() > 300)
                    .distinct()
                    .limit(3)
                    .count();


            List<ComplexDish> vegetarianDishes = new ArrayList<>();
            for(ComplexDish d: menu){
                if(d.isVegetarian()){
                    vegetarianDishes.add(d);
                }
            }

            vegetarianDishes =
                    menu.stream()
                            .filter(ComplexDish::isVegetarian)
                            .collect(toList());







            List<ComplexDish> dishes = menu.stream()
                    .filter(d -> d.getCalories() > 300)
                    .limit(3)
                    .collect(toList());



            dishes = menu.stream()
                    .filter(d -> d.getCalories() > 300)
                    .skip(2)
                    .collect(toList());


            dishes =
                    menu.stream()
                            .filter(d -> d.getType() == ComplexDish.Type.MEAT)
                            .limit(2)
                            .collect(toList());


            List<String> dishNames = menu.stream()
                    .map(ComplexDish::getName)
                    .collect(toList());





            List<Integer> dishNameLengths = menu.stream()
                    .map(ComplexDish::getName)
                    .map(String::length)
                    .collect(toList());



            /** FLATMAP **/

            List<String> words = Arrays.asList("Java8", "Lambdas", "In", "Action");
            List<Integer> wordLengths = words.stream()
                    .map(String::length)
                    .collect(toList());


            List<String> uniqueCharacters = words.stream()
                    .map(w -> w.split(""))
                    .flatMap(Arrays::stream)
                    .distinct()
                    .collect(Collectors.toList());

            System.out.println(uniqueCharacters);


            List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
            numbers.stream()
                    .filter(i -> i % 2 == 0)
                    .distinct()
                    .forEach(System.out::println);

            numbers = Arrays.asList(1, 2, 3, 4, 5);
            List<Integer> squares =
                    numbers.stream()
                            .map(n -> n * n)
                            .collect(toList());


            /**
             * You could use two maps to iterate on the two lists and generate the pairs.
             * But this would return a Stream<Stream<Integer[]>>. What you need to do is
             * flatten the generated streams to result in a Stream<Integer[]>. This is what flatMap is for:
             */
            List<Integer> numbers1 = Arrays.asList(1, 2, 3);
            List<Integer> numbers2 = Arrays.asList(3, 4);
            List<Integer> finalNumbers1 = numbers2;
            List<int[]> pairs =
                    numbers1.stream()
                            .flatMap(i -> finalNumbers1.stream()
                                    .map(j -> new int[]{i, j})
                            )
                            .collect(toList());


            /**
             * 3. How would you extend the previous example to return only
             * pairs whose sum is divisible by 3? For example, (2, 4) and (3, 3) are valid.
             *
             * Answer:
             *
             * You saw earlier that filter can be used with a predicate to filter
             * elements from a stream. Because after the flatMap operation you have a stream of int[] that
             * represent a pair, you just need a predicate to check to see if the sum is divisible by 3:
             */
            numbers1 = Arrays.asList(1, 2, 3);
            numbers2 = Arrays.asList(3, 4);
            List<Integer> finalNumbers = numbers2;
            pairs =
                numbers1.stream()
                        .flatMap(i ->
                                finalNumbers.stream()
                                        .filter(j -> (i + j) % 3 == 0)
                                        .map(j -> new int[]{i, j})
                        )
                        .collect(toList());


            //5.3.1. Checking to see if a predicate matches at least one element
            if(menu.stream().anyMatch(ComplexDish::isVegetarian)){
                System.out.println("The menu is (somewhat) vegetarian friendly!!");
            }

            //5.3.2. Checking to see if a predicate matches all elements
            boolean isHealthy = menu.stream()
                    .allMatch(d -> d.getCalories() < 1000);


            //5.3.2. Checking to see if a predicate matches all elements
            isHealthy = menu.stream()
                    .noneMatch(d -> d.getCalories() >= 1000);


            Optional<ComplexDish> dish =
                    menu.stream()
                            .filter(ComplexDish::isVegetarian)
                            .findAny();


            Optional<ComplexDish> vegetarianDish = menu.stream()
                    .filter(ComplexDish::isVegetarian)
                    .findAny();

            vegetarianDish.ifPresent(d -> System.out.println(d.getName()));




            List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
            Optional<Integer> firstSquareDivisibleByThree =
                    someNumbers.stream()
                            .map(x -> x * x)
                            .filter(x -> x % 3 == 0)
                            .findFirst(); // 9


            /**Reducing **/

            int sum = 0;
            for (int x : numbers) {
                sum += x;
            }


            sum = numbers.stream().reduce(0, (a, b) -> a + b);

            int product = numbers.stream().reduce(1, (a, b) -> a * b);


            sum = numbers.stream().reduce(0, Integer::sum);

            Optional<Integer> sumWithReduce = numbers.stream().reduce((a, b) -> (a + b));



            Optional<Integer> max = numbers.stream().reduce(Integer::max);
            Optional<Integer> min = numbers.stream().reduce(Integer::min);




            count = menu.stream()
                    .map(d -> 1)
                    .reduce(0, (a, b) -> a + b);

            count = menu.stream().count();



            sum = numbers.parallelStream().reduce(0, Integer::sum);

        }
}
