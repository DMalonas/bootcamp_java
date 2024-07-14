package org.day4.java_8_in_action.streams.collectors.primes.custom_collector;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * The PrimeNumbersCollectorRunner class serves as an entry point to demonstrate the usage of
 * the PrimeNumbersCollector. It generates a stream of integers up to a specified limit and then
 * collects them using the PrimeNumbersCollector, separating the prime numbers from the non-primes.
 * The results are then printed to the console.
 */
public class PrimeNumbersCollectorRunner {

    public static void main(String[] args) {
        // Creating a stream of numbers to test for primality
        int numbersUpTo = 100;
        Map<Boolean, List<Integer>> primesAndNonPrimes =
                IntStream.rangeClosed(2, numbersUpTo)
                .boxed()
                .collect(new PrimeNumbersCollector());

        // Output the results
        System.out.println("Primes: " + primesAndNonPrimes.get(true));
        System.out.println("Non-primes: " + primesAndNonPrimes.get(false));
    }
}