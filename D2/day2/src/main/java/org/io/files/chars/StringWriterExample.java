package org.io.files.chars;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;

public class StringWriterExample {
    public static void main(String[] args) {
        StringWriter stringWriter = new StringWriter();
        try (BufferedWriter bufferedWriter = new BufferedWriter(stringWriter)) {
            bufferedWriter.write("Hello, World!");
            bufferedWriter.newLine();  // writes a newline
            bufferedWriter.write("Welcome.");
            bufferedWriter.newLine();  // writes a newline
            bufferedWriter.write("Have a great day!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = stringWriter.toString();
        System.out.println(result);
    }
}

