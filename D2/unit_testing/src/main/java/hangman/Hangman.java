package hangman;

import java.util.Scanner;

public class Hangman {
  /**
   * Main method of the project.
   * @param args String, command line arguments.
   */
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    GameState game; 
    CommandOpts opts; // Initialise CommandOpts object
    Words wordObject;
    boolean correctChoice;
    boolean nextGame = false; // true if the user wants to play again.
    String answer; // So the user will say if they want to play again.
    int inputNum;
    opts = new CommandOpts(args);
    wordObject = new Words();
    
    do { //menu driven program
      //1. Show options	
      if ("".equals(opts.getWordSource())) {
        System.out.println("  1. Counties");
        System.out.println("  2. Countries");
        System.out.println("  3. Cities");
        System.out.print("Please pick a category (1, 2, 3): ");
        //2. get input 
        do {
          while (!sc.hasNextInt()) {
            String input = sc.next();
            System.out.printf("\"%s\" is not a valid input.\n", input);
          }
          inputNum = sc.nextInt();
          if ((inputNum != 1) && (inputNum != 2) && (inputNum != 3)) {
            System.out.printf("\"%s\" is not a valid number.\n", inputNum);
          }
        } while ((inputNum != 1) && (inputNum != 2) && (inputNum != 3));
 
        //3. Create game
        game = new GameState(wordObject.getRandomWord(inputNum), 
          opts.getMaxGuesses(), opts.getMaxHints());
      } else {
        game = new GameState(wordObject.getRandomWord(opts.getWordSource()), 
          opts.getMaxGuesses(), opts.getMaxHints());
      }
      
      
      //4. read guesses
      while (!game.won() && !game.lost()) {
        game.showWord();
        System.out.println("Guesses remaining: " + game.getWrong());
        correctChoice = game.guessLetter();
        if (correctChoice) {
          System.out.println("Good guess!");
        }
        if ((!correctChoice) && (game.isDuplicateChar())) {
          System.out.println("\nInput has already been given");
        }
        if ((!correctChoice) && (!game.wasHint()) && (!game.isDuplicateChar())) {
          System.out.println("Wrong guess!");
        }
      }
      
      //5. handle outcome
      if (game.won()) {
        System.out.println();
        game.showWord();
        System.out.println("Well done!");
        System.out.println("You took " + game.getGuesses() + " guesses");
      } else {
        System.out.println("You lost! The word was \"" + game.getWordToFind() + "\"");
      }
      
      //6. Ask if player wants to play again
      System.out.print("Play again? (Yes / No): ");
      for (;;) {
        answer = sc.next();
        if (("Yes".equals(answer)) || ("yes".equals(answer)) || ("YES".equals(answer))) {
          nextGame = true;
          System.out.println("\nNext Round");
          break;
        }
        if (("No".equals(answer)) || ("no".equals(answer)) || "NO".equals(answer)) {
          nextGame = false;
          System.out.println("Thank you for playing");
          break;
        }
      }
    } while (nextGame == true);
  } 
}
