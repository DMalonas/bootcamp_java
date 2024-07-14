package org.io.files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriteExample {

    public static void main(String[] args) {
        File myFile = new File("example.txt");

        try {
            FileWriter myWriter = new FileWriter(myFile);
            myWriter.write("Hello, World!");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}


