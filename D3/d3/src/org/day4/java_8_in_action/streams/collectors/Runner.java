package org.day4.java_8_in_action.streams.collectors;

import org.day4.java_8_in_action.model.Dish;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Runner {



    // Assuming there's a Transaction class with a getCurrency method
    static class Transaction {
        private Currency currency;
        // other fields and methods
        
        public Transaction(Currency currency) {
            this.currency = currency;
        }

        public Currency getCurrency() {
            return currency;
        }

        // other methods
    }

    public static void main(String[] args) {
        // Example list of transactions to be grouped
        List<Transaction> transactions = List.of(
            new Transaction(Currency.getInstance("USD")),
            new Transaction(Currency.getInstance("EUR"))
        );

        // Create the map where the grouped transaction will be accumulated.
        Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();

        // Iterate the list of transactions.
        for (Transaction transaction : transactions) {
            // Extract the transaction's currency.
            Currency currency = transaction.getCurrency();

            // Get the list of transactions for this currency.
            List<Transaction> transactionsForCurrency = transactionsByCurrencies.get(currency);

            // If there's no entry in the grouping Map for this currency, create it.
            if (transactionsForCurrency == null) {
                transactionsForCurrency = new ArrayList<>();
                transactionsByCurrencies.put(currency, transactionsForCurrency);
            }

            // Add the currently traversed Transaction to the List of Transactions with the same currency.
            transactionsForCurrency.add(transaction);
        }
        
        // Print the grouped transactions
        transactionsByCurrencies.forEach((currency, transactionList) -> {
            System.out.println(currency.getCurrencyCode() + ": " + transactionList.size());
        });


        transactionsByCurrencies =
                transactions.stream().collect(groupingBy(Transaction::getCurrency));

        transactions =
                transactions.stream().collect(Collectors.toList());


        List<Dish> menu = Arrays.asList(new Dish("meat", 200), new Dish("fish", 190),
                new Dish("vegetable", 220)); //new ArrayList<>(); // Assume 'menu' is initialized with Dish objects


        long howManyDishes = menu.stream().collect(Collectors.counting());

        howManyDishes = menu.stream().count();


        Comparator<Dish> dishCaloriesComparator =
                Comparator.comparingInt(Dish::getCalories);

        Optional<Dish> mostCalorieDish =
                menu.stream()
                        .collect(maxBy(dishCaloriesComparator));



        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));

        double avgCalories =
                menu.stream().collect(averagingInt(Dish::getCalories));


        IntSummaryStatistics menuStatistics =
                menu.stream().collect(summarizingInt(Dish::getCalories));


        String shortMenu = menu.stream().map(Dish::getName).collect(joining());

        shortMenu = menu.stream().map(Dish::getName).collect(joining(", "));


        totalCalories = menu.stream().collect(reducing(
                0, Dish::getCalories, (i, j) -> i + j));


        mostCalorieDish =
                menu.stream().collect(reducing(
                        (d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));


        totalCalories = menu.stream()
                .collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));

        totalCalories =
                menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();

        totalCalories = menu.stream().mapToInt(Dish::getCalories).sum();

        shortMenu = menu.stream().map(Dish::getName).collect(joining());



    }
}
