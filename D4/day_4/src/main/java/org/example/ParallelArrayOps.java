package org.example;

import java.util.Arrays;

/**
 * The ParallelArrayOps class demonstrates various parallel operations on arrays
 * using Java's Arrays class. It includes examples of parallel sorting, setting
 * array elements in parallel, and performing a cumulative operation in parallel.
 *
 * Key Points:
 * - Demonstrates parallelSort, parallelSetAll, and parallelPrefix methods.
 * - These methods leverage parallel processing to improve performance on large arrays.
 */
public class ParallelArrayOps {
    public static String arrToString(int[] arr) {
        // Converts an array to a string representation
        StringBuilder sb = new StringBuilder();
        Arrays.stream(arr).forEach((x) -> sb.append(x).append(" "));
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] arr = {10, 12, 11, 13, 19, 18, 17, 16, 15, 14};

        // Parallel sorting of the array
        Arrays.parallelSort(arr);
        System.out.println(arrToString(arr));

        // Setting each element of the array in parallel
        Arrays.parallelSetAll(arr, (idx) -> idx + 1);
        System.out.println(arrToString(arr));

        // Cumulative operation in parallel
        Arrays.parallelPrefix(arr, (x, y) -> x + y);
        System.out.println(arrToString(arr));
    }
}
