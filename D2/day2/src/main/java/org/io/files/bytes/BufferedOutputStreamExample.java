package org.io.files.bytes;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedOutputStreamExample {

    public static void main(String[] args) {
        String text = "Hello, BufferedOutputStream!";
        byte[] data = text.getBytes();

        try (FileOutputStream fos = new FileOutputStream("output.txt");
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {

            // Write data to the buffered output stream
            bos.write(data);

            // Always flush the stream before closing, to ensure all buffered data is written out
            bos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


