package org.io.files.chars;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class OutputStreamWriterExample {
    public static void main(String[] args) {
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("output.txt"), "UTF-8")) {
            String content = "Hello, OutputStreamWriter!";
            writer.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


