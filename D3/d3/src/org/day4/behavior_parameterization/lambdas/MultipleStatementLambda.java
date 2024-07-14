package org.day4.behavior_parameterization.lambdas;

import java.util.function.BiFunction;

public class MultipleStatementLambda {

    public static void main(String[] args) {
        // Lambda expression with multiple statements
        BiFunction<Integer, Integer, Integer> multiplyAndPrint = (a, b) -> {
            int result = a * b;
            System.out.println("Multiplying " + a + " and " + b + " gives " + result);
            return result; // Explicit return statement is needed
        };

        // Invoke the lambda expression
        int product = multiplyAndPrint.apply(5, 10); // This will also print the message
        System.out.println("Product is: " + product);
    }
}
