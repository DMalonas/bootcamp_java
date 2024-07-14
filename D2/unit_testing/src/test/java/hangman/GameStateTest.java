import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Test;
import hangman.*;


public class GameStateTest {
  @Test
  public void checkShowWordDashesOnly() {
    GameState gameState = new GameState("Dimitrios", 10, 2);
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    gameState.showWord();
    assertEquals("---------\r\n", outContent.toString());
    System.setOut(System.out);
  }
  
  @Test
  public void checkLost() {
    GameState gameState = new GameState("Dimitrios", 0, 2);
    assertTrue(gameState.lost());
    assertFalse(gameState.won());
  }
  
  @Test
  public void checkWon() {
    GameState gameState = new GameState("Dimitrios", 10, 2);
    ArrayList<Character> list = new ArrayList<Character>();
    gameState.setNotFoundCharacters(list);
    assertTrue(gameState.won());
    assertFalse(gameState.lost());
  }
  
  @Test
  public void checkGetWord() {
    GameState gameState = new GameState("Dimitrios", 10, 2);
    assertEquals("Dimitrios", gameState.getWordToFind());
  }
  
  @Test
  public void checkCanProvideMoreHints() {
    GameState gameState = new GameState("Dimitrios", 10, 10);
    HashSet<Character> hints = new HashSet();
    hints.add('d');
    hints.add('i');
    hints.add('m');
    hints.add('t');
    hints.add('r');
    gameState.setHintsGiven(hints);
    assertTrue(gameState.canProvideMoreHints());
    hints.add('s');
    hints.add('o');
    gameState.setHintsGiven(hints); 
    hints.add('s');
    hints.add('s');
    gameState.setHintsGiven(hints);

    assertFalse(gameState.canProvideMoreHints());
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    gameState.giveHint();
    assertEquals("\nNo more hints left to give\n\r\n", outContent.toString());
    System.setOut(System.out);
  }
  
  @Test
  public void checkHintRequestedState() throws FileNotFoundException {
    ByteArrayInputStream in = new ByteArrayInputStream("?".getBytes());
    System.setIn(in);
    GameState gameState = new GameState("Dimitrios", 10, 2);
    gameState.guessLetter();
    assertTrue(gameState.wasHint());
    assertEquals(1, gameState.getHints());
    assertEquals(0, gameState.getGuesses());
    System.setIn(System.in);
  }
  
  @Test
  public void checkNoMoreHintsAllowed() {
    GameState gameState = new GameState("Dimitrios", 10, 10);
    gameState.setHints(0);
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    gameState.giveHint();
    assertEquals("\nNo more hints allowed\n\r\n", outContent.toString());
    System.setOut(System.out);
  }
  
  @Test
  public void checkShowWord() throws FileNotFoundException {
    ByteArrayInputStream in = new ByteArrayInputStream("D".getBytes());
    System.setIn(in);
    GameState gameState = new GameState("Dimitris", 10, 2);
    gameState.guessLetter();
    assertFalse(gameState.wasHint());
    assertEquals(2, gameState.getHints());
    assertEquals(1, gameState.getGuesses());
    System.setIn(System.in);
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    gameState.showWord();
    assertEquals("D-------\r\n", outContent.toString());
    System.setOut(System.out);
  }
  
  @Test
  public void checkWrongGuess() throws FileNotFoundException {
    ByteArrayInputStream in = new ByteArrayInputStream("a".getBytes());
    System.setIn(in);
    GameState gameState = new GameState("Dimitris", 10, 2);
    gameState.guessLetter();
    assertEquals(1, gameState.getGuesses());
    assertEquals(9, gameState.getWrong());
    System.setIn(System.in);
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    gameState.showWord();
    assertEquals("--------\r\n", outContent.toString());
    System.setOut(System.out);
  }
  
  @Test
  public void checkDuplicateLetter() throws FileNotFoundException {
    ByteArrayInputStream in = new ByteArrayInputStream("i".getBytes());
    System.setIn(in);
    HashSet<Character> list = new HashSet<Character>();
    GameState gameState = new GameState("Dimitris", 10, 2);
    list.add('i');
    gameState.setCharactersGiven(list);
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    gameState.guessLetter();
    assertEquals(0, gameState.getGuesses());
    assertEquals(10, gameState.getWrong());
    assertEquals("Guess a letter or word (? for a hint): ", outContent.toString());
    System.setIn(System.in);
    System.setOut(System.out);
  }
  
  @Test
  public void checkDuplicateHint() throws FileNotFoundException {
    ByteArrayInputStream in = new ByteArrayInputStream("i".getBytes());
    System.setIn(in);
    GameState gameState = new GameState("Dimitris", 10, 10);
    HashSet<Character> hints = new HashSet();
    hints.add('d');
    hints.add('i');  
    hints.add('m');
    hints.add('t');
    hints.add('r');
    gameState.setHintsGiven(hints);
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    gameState.giveHint();
    assertEquals("Try: s\r\n", outContent.toString());
    System.setIn(System.in);
    System.setOut(System.out);
  }
  
  @Test
  public void checkCorrectWordGuess() throws FileNotFoundException {
    ByteArrayInputStream in = new ByteArrayInputStream("Dimitris".getBytes());
    System.setIn(in);
    GameState gameState = new GameState("Dimitris", 10, 2);
    assertTrue(gameState.guessLetter());
    System.setIn(System.in);
  }
  
  @Test
  public void checkWrongWordGuess() throws FileNotFoundException {
    ByteArrayInputStream in = new ByteArrayInputStream("Dimitris".getBytes());
    System.setIn(in);
    GameState gameState = new GameState("Malonas", 10, 2);
    assertFalse(gameState.guessLetter());
    assertEquals(1, gameState.getGuesses());
    assertEquals(9, gameState.getWrong());
    System.setIn(System.in);
  }
  
  @Test
  /**
   * When we give enter. For java change line is \r\n
   * @throws FileNotFoundException
   */
  public void checkUnacceptableInput() throws FileNotFoundException {
    ByteArrayInputStream in = new ByteArrayInputStream("\r\no".getBytes()); // The keyboard input will be enter and o.
    System.setIn(in); // Redirect the input so we can pass input from the test instead from keyboard.
    GameState gameState = new GameState("Dimitris", 10, 2);
    ByteArrayOutputStream outContent = new ByteArrayOutputStream(); // Prepare my output stream.
    System.setOut(new PrintStream(outContent)); // Redirect output so we can get the output here.dont print in the screen but on outContent.
    gameState.guessLetter();
    assertEquals(1, gameState.getGuesses()); //I have to add a letter as well so guessLetter will close. 
    assertEquals(9, gameState.getWrong());	//How many guesses I have been left with
    assertEquals("Guess a letter or word (? for a hint): The key \"Enter\""
        + " is not an acceptable input\nGuess a "
        + "letter or word (? for a hint): ", outContent.toString());
    System.setOut(System.out); //Deactivate redirection.
    System.setIn(System.in); // Deactivate redirection. 
  }

}
