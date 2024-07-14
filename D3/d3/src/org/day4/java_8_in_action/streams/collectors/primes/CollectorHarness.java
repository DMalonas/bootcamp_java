package org.day4.java_8_in_action.streams.collectors.primes;

import org.day4.java_8_in_action.streams.collectors.primes.custom_collector.PrimeNumbersCollector;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static org.day4.java_8_in_action.streams.collectors.primes.PartitioningIsPrimeTester.partitionPrimes;

public class CollectorHarness {

    public static void main(String[] args) {
        // Initialize with the maximum value as we want to find the minimum (fastest) execution time
        long fastest = Long.MAX_VALUE;

        // Run the test 10 times
        for (int i = 0; i < 10; i++) {
            // Record the start time in nanoseconds
            long start = System.nanoTime();

            // The method partitionPrimes is supposed to partition the first million natural numbers into primes and non-primes
            // but its implementation is not provided in the image. You need to implement this method.
            partitionPrimes(1_000_000);

            // Calculate the duration of the execution in nanoseconds and convert to milliseconds
            long duration = (System.nanoTime() - start) / 1_000_000;

            // Check if this execution has been the fastest one so far
            if (duration < fastest) fastest = duration;
        }

        // Print out the fastest execution time in milliseconds
        System.out.println("Fastest execution done in " + fastest + " msecs");
        // Run the test 10 times
        for (int i = 0; i < 10; i++) {
            // Record the start time in nanoseconds
            long start = System.nanoTime();

            // The method partitionPrimes is supposed to partition the first million natural numbers into primes and non-primes
            // but its implementation is not provided in the image. You need to implement this method.
            Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector = partitionPrimesWithCustomCollector(1_000_000);
            // Print the partitioned prime numbers
//            System.out.println("Prime numbers: " + partitionPrimesWithCustomCollector.get(true));

            // Print the partitioned non-prime numbers
//            System.out.println("Non-prime numbers: " + partitionPrimesWithCustomCollector.get(false));
            // Calculate the duration of the execution in nanoseconds and convert to milliseconds
            long duration = (System.nanoTime() - start) / 1_000_000;

            // Check if this execution has been the fastest one so far
            if (duration < fastest) fastest = duration;
        }

        // Print out the fastest execution time in milliseconds
        System.out.println("Fastest execution done in " + fastest + " msecs");
    }

    public static Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(new PrimeNumbersCollector());
    }
}
