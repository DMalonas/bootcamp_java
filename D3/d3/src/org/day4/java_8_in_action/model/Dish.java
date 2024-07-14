package org.day4.java_8_in_action.model;

// Assuming a Dish class exists with the following structure
public class Dish {
    private String name;
    private int calories;

    public Dish(String name, int calories) {
        this.name = name;
        this.calories = calories;
    }

    // Constructor, getters and setters are not shown for brevity
    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", calories=" + calories +
                '}';
    }
}
