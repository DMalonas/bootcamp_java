package org.io.files.randomaccess;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RAFWriteExample {

    public static void main(String[] args) {
        try {
            RandomAccessFile raf = new RandomAccessFile("exampleRAF.txt", "rw");
            raf.writeBytes("Hello, Random Access File!");
            System.out.println("Data written to the file.");
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

