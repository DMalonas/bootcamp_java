package hangman;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class GameState {
  private String wordToFind;
  private int totalGuessesMadeCounter;
  private int wrongGuessesLeftCounter;
  private int hintsRemaining; // The number of hints allowed to the user.
  private boolean hintStatus; // True means that it is possible to ask for more hints. 
  private boolean duplicateChar;
  private ArrayList<Character> revealedCharacters; // ArrayList with the revealed characters. 
  private ArrayList<Character> notFoundCharacters; // ArrayList with the not found characters.
  private HashSet<Character> charactersGiven; // HashSet to avoid duplicate letters
  private HashSet<Character> hintsGiven; // HashSet so we wont get the same hint more than once.
 

  public Scanner sc = new Scanner(System.in).useDelimiter("\n");
  
  
  /**
   * GameState constructor.
   * @param target String, the correct word.
   * @param guesses int, number of guesses.
   * @param hints int, number of hints.
   */
  public GameState(String target, int guesses, int hints) {
    this.wordToFind = target;
    notFoundCharacters = new ArrayList<Character>();
    revealedCharacters = new ArrayList<Character>();
    charactersGiven = new HashSet<Character>();
    hintsGiven = new HashSet<Character>();
    duplicateChar = false;
    for (int i = 0; i < target.length(); ++i) {
      if (!notFoundCharacters.contains(Character.toLowerCase(target.charAt(i)))) {
        notFoundCharacters.add(Character.toLowerCase(target.charAt(i)));
      }
    }
    this.totalGuessesMadeCounter = 0;
    wrongGuessesLeftCounter = guesses;
    this.hintsRemaining = hints;
  }



  public void showWord() {
    for (int i = 0; i < wordToFind.length(); ++i) {
      if (revealedCharacters.contains(Character.toLowerCase(wordToFind.charAt(i)))) {
        System.out.print(wordToFind.charAt(i));
      } else {
        System.out.print("-");
      }
    }
    System.out.println("");
    // System.out.println(missing);
  }

  public boolean guessLetter() {
    String inputString = null;
    System.out.print("Guess a letter or word (? for a hint): ");
    inputString = sc.nextLine().toLowerCase();
    setDuplicateChar(false);

    char firstInputLetter;
    if (inputString.length() > 1) {
      setHintStatus(false); // So "Wrong guess" will be printed on the console.	
      totalGuessesMadeCounter++;
      if (inputString.equals(wordToFind.toLowerCase())) {
        notFoundCharacters.clear();
        revealedCharacters.clear();
        for (int i = 0; i < wordToFind.length(); i++) { 
          revealedCharacters.add(inputString.charAt(i));
        }
        return true;
      } else {
        wrongGuessesLeftCounter--;
        return false;
      }
    }
    try {
      firstInputLetter = inputString.charAt(0);
      if (firstInputLetter == '?') {
        giveHint();
        return false;
      }
      setHintStatus(false); // So "Wrong guess" will be printed on the console.
      if (charactersGiven.contains(firstInputLetter)) {
        setDuplicateChar(true);
        return false;
      }
      charactersGiven.add(firstInputLetter);
      char charFromNotFoundArrayList;
      for (int i = 0; i < notFoundCharacters.size(); ++i) { // Loop over the not
        charFromNotFoundArrayList = notFoundCharacters.get(i);
        if (charFromNotFoundArrayList == firstInputLetter) {
          notFoundCharacters.remove(i);
          revealedCharacters.add(firstInputLetter);
          totalGuessesMadeCounter++;
          return true;
        }
      }
    } catch (StringIndexOutOfBoundsException e) {
      System.out.print("The key \"Enter\" is not an acceptable input\n");
      return guessLetter();
    }
    totalGuessesMadeCounter++; // One more guess
    wrongGuessesLeftCounter--;
    return false;
  }

  public boolean won() {
    if (notFoundCharacters.size() == 0) {
      return true;
    } else {
      return false;
    }
  }


  public boolean lost() {
    if (notFoundCharacters.size() > 0 && wrongGuessesLeftCounter == 0) { 
      return true; 
    } else {
      return false;
    }
  }

  public void giveHint() {
    char hintCharacter;
    setHintStatus(true); // So "Wrong guess" will not be printed on the console.
    if (hintsRemaining > 0 && canProvideMoreHints()) {
      hintCharacter = notFoundCharacters.get((int)(Math.random() * notFoundCharacters.size()));
      if (hintsGiven.contains(hintCharacter)) {
        giveHint();
      } else {
        hintsGiven.add(hintCharacter);
        hintsRemaining--;
        System.out.print("Try: " + hintCharacter);
        System.out.println();
      }
    } else if (hintsRemaining == 0) {
      System.out.println("\nNo more hints allowed\n");
    } else {
      System.out.println("\nNo more hints left to give\n");
    }
  }

  /**
  * When the user enter '?' the GameState object calls
  * the setHintStatus method which sets the hintStatus
  * attribute to true. Then the Hangman object calls
  * the wasHint method to check if the value entered
  * by the user was a wrong guess - and if that is 
  * the case the message "Wrong guess" appears - or
  * if it was the '?' character which is used to 
  * request a hint.
  * @param hint boolean, sets the hintStatus
  *                      attribute value.
  */
  public void setHintStatus(boolean hint) {
    this.hintStatus = hint;
  }
  
  /**
   * The method was hint returns true if the character 
   * provided by the user was '?' and false if it 
   * was not.
   * @return hintStatus, boolean.
   */
  public boolean wasHint() {
    return hintStatus;
  }

  /**
   * Get number of guesses made.
    * @return the guesses
   */
  public int getGuesses() {
    return totalGuessesMadeCounter;
  }


  /**
   * Get the number of wrong attempts.
   * @return the wrong
  */
  public int getWrong() {
    return wrongGuessesLeftCounter;
  }

  /**
   * Get the number of hints.
   * @return the hints
  */
  public int getHints() {
    return hintsRemaining;
  }

  /**
   * Set the number of hints.
   * @param hints the hints to set
  */
  public void setHints(int hints) {
    this.hintsRemaining = hints;
  }

  /**
   * Return word to find.
   * @return the wordToFind
   */
  public String getWordToFind() {
    return wordToFind;
  }
  
  /**
   * 
   */
  public boolean canProvideMoreHints() {
    for (int i = 0; i < notFoundCharacters.size(); i++) {
      if (!hintsGiven.contains(notFoundCharacters.get(i))) {
        return true;
      }
    }
    return false;
  }



  /**
   * @return the notFoundCharacters
   */
  public ArrayList<Character> getNotFoundCharacters() {
    return notFoundCharacters;
  }

  /**
  * @param notFoundCharacters the notFoundCharacters to set
 */
  public void setNotFoundCharacters(ArrayList<Character> notFoundCharacters) {
    this.notFoundCharacters = notFoundCharacters;
  }

  /**
  * @return the charactersGiven
  */
  public HashSet<Character> getCharactersGiven() {
    return charactersGiven;
  }

  /**
  * @param charactersGiven the charactersGiven to set
  */
  public void setCharactersGiven(HashSet<Character> charactersGiven) {
    this.charactersGiven = charactersGiven;
  }

  /**
  * @return the hintsGiven
  */
  public HashSet<Character> getHintsGiven() {
    return hintsGiven;
  }

  /**
  * @param hintsGiven the hintsGiven to set
  */
  public void setHintsGiven(HashSet<Character> hintsGiven) {
    this.hintsGiven = hintsGiven;
  }

  /**
  * @return the duplicateChar
  */
  public boolean isDuplicateChar() {
    return duplicateChar;
  }

  /**
  * @param duplicateChar the duplicateChar to set
  */
  public void setDuplicateChar(boolean duplicateChar) {
    this.duplicateChar = duplicateChar;
  }
}
