package org.io.files.chars;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedWriterExample {
    public static void main(String[] args) {
        // Specify the path to the file
        String filePath = "path/to/your/output.txt";
        // Data to write to the file
        String[] data = {
                "Hello, world!",
                "Welcome to BufferedWriter example.",
                "Have a nice day!"
        };
        try (
                // Create a FileWriter to write to the file
                FileWriter fileWriter = new FileWriter(filePath);
                // Wrap the FileWriter with BufferedWriter
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)
        ) {
            // Write the data to the file
            for (String line : data) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();  // Write a newline character for each line
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


