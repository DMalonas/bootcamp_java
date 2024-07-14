package org.io.nio;

import java.io.*;
import java.nio.channels.*;
import java.nio.file.*;

public class FileLockingExample {
    public static void main(String[] args) {
        Path path = Paths.get("example.txt");
        try (RandomAccessFile raf = new RandomAccessFile(path.toFile(), "rw");
             FileChannel fileChannel = raf.getChannel();
             FileLock lock = fileChannel.tryLock()) {
            if (lock != null) {
                System.out.println("File is locked for exclusive access");
                // Perform operations on the file
                // Simulate some work with a thread sleep
                Thread.sleep(2000);
                System.out.println("Release the lock");
            }
        } catch (OverlappingFileLockException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}


