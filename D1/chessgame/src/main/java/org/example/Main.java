package org.example;

import model.Board;
import model.Square;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Board b = new Board();  // Create Board object with turn = WHITE
        String input;
        boolean newGame = true;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Chess\n");
        System.out.println("Press key: ");

        int option = scanner.nextInt();

        do {
            if (option == 1) {
                System.out.println("Load previous game\n");
                b.setBoard();
                b.readFromBinFile("./record.dat");
            } else {
                b.setBoard();
            }
            scanner.nextLine(); // Consume newline left-over

            while (b.playGame()) {
                // Continue playing the game
            }

            System.out.println("Play again? (y|Y|yes|Yes : continue), (Press any other key to exit)");
            input = scanner.nextLine();

            if (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("yes")) {
                newGame = false;
            }
        } while (newGame);

        scanner.close();
    }



}
