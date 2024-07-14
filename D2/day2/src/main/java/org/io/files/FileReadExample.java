package org.io.files;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileReadExample {

    public static void main(String[] args) {
        File myFile = new File("example.txt");

        try {
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println("Read from file: " + data);
            }
            myReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}


