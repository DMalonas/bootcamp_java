package org.io.files.bytes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamExample {
    public static void main(String[] args) {
        File file = new File("output.txt");
        String data = "This is some sample data to write to the file.";

        try (FileOutputStream fos = new FileOutputStream(file)) {
            // Convert string to bytes and write to the file
            fos.write(data.getBytes());
            System.out.println("Data written to the file successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

