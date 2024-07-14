package org.io.files;

import java.io.File;
import java.io.IOException;

public class FileCreateExample {

    public static void main(String[] args) {
        File myFile = new File("example.txt");

        try {
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
