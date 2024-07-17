package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Demonstrates the use of Java's ProcessBuilder to execute an external command
 * and process its output. This class specifically runs the 'ls -al' command
 * (which lists files in the current directory in long format), reads its output,
 * and calculates the total number of lines and characters in the output.
 */
public class Processes1 {
    public static void main(String[] args) throws Exception {
        // Start a process using ProcessBuilder to execute the 'ls -al' command.
        Process lsLines = new ProcessBuilder("ls", "-al").start();

        // Wait for the process to complete.
        lsLines.waitFor();

        // Although getOutputStream() is called, it's not used in this context.
        // This line could be removed without affecting the program's functionality.
        lsLines.getOutputStream();

        // Set up a BufferedReader to read the output of the process.
        BufferedReader brd = new BufferedReader(new InputStreamReader(lsLines.getInputStream()));

        // Variables to keep track of the number of lines and characters.
        int sumOfChars = 0;
        int sumOfLines = 0;

        // Read the output line by line.
        while (true) {
            String s = brd.readLine();
            if (s == null) break; // Exit the loop if the end of the stream is reached.
            sumOfLines += 1; // Increment the line count.
            sumOfChars += s.length(); // Add the length of the line to the character count.
        }

        // Print the total number of lines and characters in the output.
        System.out.println("Lines: " + sumOfLines + " Chars: " + sumOfChars);
    }
}
