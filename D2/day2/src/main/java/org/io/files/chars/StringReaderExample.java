package org.io.files.chars;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public class StringReaderExample {

    public static void main(String[] args) {
        String multiLineString = "Hello, World!\nWelcome.\nHave a great day!";

        try (StringReader stringReader = new StringReader(multiLineString);
             BufferedReader bufferedReader = new BufferedReader(stringReader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

