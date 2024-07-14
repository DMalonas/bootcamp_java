package org.io.files.randomaccess;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RAFCreateExample {

    public static void main(String[] args) {
        try {
            RandomAccessFile raf = new RandomAccessFile("exampleRAF.txt", "rw");
            System.out.println("File created or opened for read/write.");
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


