package org.io.files.chars;

import java.io.FileReader;
import java.io.IOException;

public class FileReaderExample {
    public static void main(String[] args) {
        // Define the path to the file to be read
        String filePath = "input.txt";

        // Try-with-resources to ensure FileReader is closed after use
        try (FileReader reader = new FileReader(filePath)) {
            int character;

            // Read the characters one by one until the end of the file
            while ((character = reader.read()) != -1) {
                // Print each character to the console
                System.out.print((char) character);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

