package org.io.files.bytes;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataOutputStreamExample {
    public static void main(String[] args) {
        String filename = "datafile.dat";

        // Writing data to the file using DataOutputStream
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename))) {
            int age = 25;
            float salary = 5000.5f;
            String name = "Alice";
            dos.writeInt(age);
            dos.writeFloat(salary);
            dos.writeUTF(name);
            System.out.println("Data written to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


