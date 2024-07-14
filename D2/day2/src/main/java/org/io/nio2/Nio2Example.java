package org.io.nio2;

import java.nio.file.*;
import java.io.IOException;
import java.nio.file.attribute.BasicFileAttributes;

public class Nio2Example {

    public static void main(String[] args) {
        // Define the paths
        Path sourcePath = Paths.get("sourceFile.txt");
        Path targetPath = Paths.get("targetFile.txt");

        // Copy a file
        try {
            Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied successfully.");
        } catch (IOException e) {
            System.err.println("I/O Exception: " + e.getMessage());
        }

        // Delete a file
        try {
            Files.deleteIfExists(targetPath);
            System.out.println("File deleted successfully.");
        } catch (IOException e) {
            System.err.println("I/O Exception: " + e.getMessage());
        }

        // Read file attributes
        try {
            BasicFileAttributes attrs = Files.readAttributes(sourcePath, BasicFileAttributes.class);
            System.out.println("Creation time: " + attrs.creationTime());
            System.out.println("Last access time: " + attrs.lastAccessTime());
            System.out.println("Last modified time: " + attrs.lastModifiedTime());
            System.out.println("File size: " + attrs.size() + " bytes");
        } catch (IOException e) {
            System.err.println("I/O Exception: " + e.getMessage());
        }

        // Create a directory
        Path dirPath = Paths.get("newDir");
        try {
            Files.createDirectory(dirPath);
            System.out.println("Directory created successfully.");
        } catch (IOException e) {
            System.err.println("I/O Exception: " + e.getMessage());
        }

        // Directory listing
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath)) {
            for (Path file : stream) {
                System.out.println(file.getFileName());
            }
        } catch (IOException | DirectoryIteratorException e) {
            System.err.println("I/O Exception: " + e.getMessage());
        }
    }
}


