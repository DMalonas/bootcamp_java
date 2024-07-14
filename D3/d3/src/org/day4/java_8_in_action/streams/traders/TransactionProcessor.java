package org.day4.java_8_in_action.streams.traders;


import org.day4.java_8_in_action.model.Dish;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

public class TransactionProcessor {



    public static void main(String[] args) {
        List<Transaction> transactions = Arrays.asList(
                new Transaction(new Trader("Brian", "Cambridge"), 2011, 300),
                new Transaction(new Trader("Raoul", "Cambridge"), 2011, 400),
                new Transaction(new Trader("Raoul", "Cambridge"), 2012, 1000),
                new Transaction(new Trader("Mario", "Milan"), 2012, 710),
                new Transaction(new Trader("Mario", "Milan"), 2012, 700),
                new Transaction(new Trader("Alan", "Cambridge"), 2012, 950)
        );

        List<Transaction> tr2011 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());

        System.out.println(tr2011);


        // Listing 5.1: Find all transactions in 2011 and sort by value (small to high)
        tr2011 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(Collectors.toList());

        System.out.println("Transactions in 2011, sorted by value:");
        tr2011.forEach(System.out::println);

        // Listing 5.2: What are all the unique cities where the traders work?
        List<String> cities = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());

        System.out.println("Unique cities where the traders work:");
        cities.forEach(System.out::println);


        Set<String> citiesDistinct =
                transactions.stream()
                        .map(transaction -> transaction.getTrader().getCity())
                        .collect(toSet());


        // Listing 5.3: Find all traders from Cambridge and sort them by name
        List<Trader> tradersFromCambridge = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> "Cambridge".equals(trader.getCity()))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(Collectors.toList());

        System.out.println("Traders from Cambridge, sorted by name:");
        tradersFromCambridge.forEach(System.out::println);

        // Listing 5.4: Return a string of all traders’ names sorted alphabetically
        String traderStr = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2 + " ");

        System.out.println("All traders' names sorted alphabetically:");
        System.out.println(traderStr.trim()); // trim to remove the last space

        //Not efficient because all the strings are continuously concatenated
        traderStr =
                transactions.stream()
                        .map(transaction -> transaction.getTrader().getName())
                        .distinct()
                        .sorted()
                        .collect(joining());








        // Listing 5.5: Are any traders based in Milan?
        boolean milanBased = transactions.stream()
                .anyMatch(transaction -> "Milan".equals(transaction.getTrader().getCity()));
        System.out.println("Are any traders based in Milan? " + milanBased);

        // Listing 5.6: Print all transactions’ values from the traders living in Cambridge
        System.out.println("Transactions’ values from traders living in Cambridge:");
        transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        // Listing 5.7: What’s the highest value of all the transactions?
        Optional<Integer> highestValue = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        highestValue.ifPresent(value -> System.out.println("Highest value of all transactions: " + value));

        // Listing 5.8: Find the transaction with the smallest value
        Optional<Transaction> smallestTransaction = transactions.stream()
                .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
        smallestTransaction.ifPresent(transaction -> System.out.println("Transaction with the smallest value: " + transaction));


        smallestTransaction =
                transactions.stream()
                        .min(comparing(Transaction::getValue));


        List<Dish> menu = Arrays.asList(new Dish("meat", 200), new Dish("fish", 190),
                new Dish("vegetable", 220)); //new ArrayList<>(); // Assume 'menu' is initialized with Dish objects

        int calories = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);


        System.out.println("Total calories: " + calories);






        OptionalInt maxCalories = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();

        int i = maxCalories.orElse(1);


        Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);


        //Streams from function
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);


        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }
}
