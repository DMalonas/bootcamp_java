package org.day4.functional_interfaces;

@FunctionalInterface
public interface EnhancedFunctionalInterface {
    // Abstract method - the essential part of a functional interface
    void performAction();

    // Default method - provides a default implementation
    default void defaultMethod() {
        System.out.println("Default implementation");
    }

    // Static method - utility method related to the functional interface
    static void staticMethod() {
        System.out.println("Static utility method");
    }
}
