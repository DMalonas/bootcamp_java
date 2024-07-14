package org.io.files.bytes;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class DataInputStreamExample {
    public static void main(String[] args) {
        String filename = "datafile.dat";
        // Reading data from the file using DataInputStream
        try (DataInputStream dis = new DataInputStream(new FileInputStream(filename))) {
            int readAge = dis.readInt();
            float readSalary = dis.readFloat();
            String readName = dis.readUTF();
            System.out.println("Age: " + readAge);
            System.out.println("Salary: " + readSalary);
            System.out.println("Name: " + readName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


