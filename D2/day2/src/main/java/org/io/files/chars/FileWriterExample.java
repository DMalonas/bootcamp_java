package org.io.files.chars;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterExample {
    public static void main(String[] args) {
        // Define the path to the file where data will be written
        String filePath = "output.txt";

        // Try-with-resources to ensure FileWriter is closed after use
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write some text to the file
            writer.write("Hello, this is a test.");
            writer.write("\nThis is a new line in the file.");

            System.out.println("Writing to file completed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
