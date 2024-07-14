package org.io.files.randomaccess;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RAFReadExample {

    public static void main(String[] args) {
        try {
            RandomAccessFile raf = new RandomAccessFile("exampleRAF.txt", "r");
            String line;
            while ((line = raf.readLine()) != null) {
                System.out.println(line);
            }
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

