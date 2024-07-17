package org.example;

import java.util.Random;

public class RandomNamePicker {
    public static void main(String[] args) {
        // Array of names
        String[] names = {"Konstantinos", "Petros", "Giannis", "Panagiotis", "Matina"};

        // Creating a Random object
        Random random = new Random();

        // Generating a random index and picking a name
        int randomIndex = random.nextInt(names.length);
        String pickedName = names[randomIndex];

        // Printing the picked name
        System.out.println("The randomly picked name is: " + pickedName);
    }
}
