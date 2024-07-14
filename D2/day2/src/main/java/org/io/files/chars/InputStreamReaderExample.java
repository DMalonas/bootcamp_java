package org.io.files.chars;

import java.io.FileInputStream;
import java.io.InputStreamReader;

public class InputStreamReaderExample {
    public static void main(String[] args) {
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream("example.txt"), "UTF-8")) {
            int data;
            while ((data = reader.read()) != -1) {
                char character = (char) data;
                System.out.print(character);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


