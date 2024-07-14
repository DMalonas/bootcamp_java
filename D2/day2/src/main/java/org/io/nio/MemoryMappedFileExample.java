package org.io.nio;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.file.*;

public class MemoryMappedFileExample {
    public static void main(String[] args) {
        Path path = Paths.get("example.bin");
        // Ensure the file has size before mapping
        try (RandomAccessFile file = new RandomAccessFile(path.toFile(), "rw")) {
            file.setLength(1024); // Set file size to 1024 bytes
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        // Use the file channel to map the file into memory
        try (FileChannel fileChannel = (FileChannel.open(path, StandardOpenOption.READ, StandardOpenOption.WRITE))) {
            MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileChannel.size());

            // Reading from memory-mapped file
            byte byteData = buffer.get(0);
            System.out.println("Read byte: " + byteData);

            // Writing to memory-mapped file
            buffer.put(0, (byte) 123);
            System.out.println("Written 123 at position 0");

            // Force changes to be written to the file
            buffer.force();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


