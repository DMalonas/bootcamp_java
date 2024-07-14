package org.io.files.chars;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderExample {
    public static void main(String[] args) {
        // Specify the path to the file
        String filePath = "path/to/your/file.txt";
        try (
                // Create a FileReader to read the file
                FileReader fileReader = new FileReader(filePath);

                // Wrap the FileReader with BufferedReader
                BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {
            // Read the file line-by-line
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


