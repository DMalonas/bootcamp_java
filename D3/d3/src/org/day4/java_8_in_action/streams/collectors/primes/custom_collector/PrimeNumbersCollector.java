package org.day4.java_8_in_action.streams.collectors.primes.custom_collector;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * The PrimeNumbersCollector class implements the Collector interface.
 * It's used to create a custom collector that partitions a stream of integers
 * into a Map of two lists, one for prime numbers and the other for non-prime numbers.
 * It overrides the required methods of the Collector interface to perform the stream reduction.
 */
public class PrimeNumbersCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

    @Override
    // supplier() is called to provide a new result container, which in this case is a Map<Boolean, List<Integer>>
    // where the key 'true' is associated with a list to store prime numbers,
    // and the key 'false' is associated with a list to store non-prime numbers.
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> {
            Map<Boolean, List<Integer>> map = new HashMap<>();
            map.put(true, new ArrayList<>());
            map.put(false, new ArrayList<>());
            return map;
        };
    }

    @Override
    // BiConsumer is a functional interface that takes two arguments and performs an operation on them without returning any result.
    // In this context, the BiConsumer takes two arguments: a Map<Boolean, List<Integer>> and an Integer.
    // The Map<Boolean, List<Integer>> (acc) represents a map where the keys are Boolean values,
    // and each key maps to a list of integers. The Boolean key typically represents a condition or a classification criterion.
    // In this case, the Boolean key is used to differentiate between prime numbers (true) and non-prime numbers (false).
    // The Integer (candidate) is the number that is being evaluated to determine whether it is prime or not.
    //
    // The lambda expression implemented by this BiConsumer performs the following operation:
    // 1. It evaluates the 'candidate' integer to determine if it is a prime number using the 'isPrime' method.
    // 2. Based on the result of 'isPrime', it fetches the corresponding list (either the list of prime numbers or
    //    the list of non-prime numbers) from the map 'acc'.
    // 3. It then adds the 'candidate' integer to the appropriate list in the map.
    //
    // This BiConsumer is typically used in the context of a stream operation, where it serves as an accumulator in a grouping or partitioning operation.
    // It effectively partitions the stream of integers into two groups (prime and non-prime) and collects them in the respective lists within the map.
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {
            acc.get(isPrime(candidate)).add(candidate);
        };
    }


    @Override
    // combiner() is a function implemented as a BinaryOperator to merge two result containers. A BinaryOperator is a functional
    // interface in Java that takes two operands of the same type, performing an operation on them and returning a result of the same type.
    // This method is used during the reduction process, particularly when the stream is processed in parallel, where partial results from
    // different threads need to be combined into a single result container. The combiner ensures that the results are properly merged,
    // maintaining the integrity of the partitioning into prime and non-prime lists.
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2) -> {
            map1.get(true).addAll(map2.get(true));
            map1.get(false).addAll(map2.get(false));
            return map1;
        };
    }


    @Override
    // finisher() is a function applied at the end of the accumulation process, transforming the intermediate
    // result container to the final result type. It's akin to the map operation, but on the container level, not the element level.
    // The identity function is a special case where the accumulated container type is the same as the result type,
    // and thus no transformation is needed. The identity function, as provided by Function.identity(), simply returns its input argument.
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }


    @Override
    // characteristics() returns an immutable set of Characteristics indicating the collector's behavior.
    // IDENTITY_FINISH signifies that the finisher function is the identity function and can be omitted.
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.IDENTITY_FINISH);
    }


    // This method is necessary for the accumulator to check if the numbers are prime.
    // The implementation for this method is not shown in the image provided.
    // Replace this with your actual prime-checking logic.
    private boolean isPrime(int candidate) {
        // Assume 'candidate' is an integer greater than 1
        for (int i = 2; i * i <= candidate; i++) {
            if (candidate % i == 0) {
                return false; // Not a prime number
            }
        }
        return true; // Is a prime number
    }
}