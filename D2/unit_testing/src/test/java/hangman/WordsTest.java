import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;
import hangman.*;

public class WordsTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

  @Test
  public void getWordTest() {
    Words words = new Words();
    assertNotEquals("", words.getRandomWord(1));
    assertNotEquals("", words.getRandomWord(2));
    assertNotEquals("", words.getRandomWord(3));
  }
  
  @Test
  public void fileCorrectTest() {
    Words words = new Words();
    assertNotEquals("", words.getRandomWord("WordsFile.txt"));
  }

  @Test
  public void fileIncorrectTest() {
    Words words = new Words();
    assertEquals("", words.getRandomWord("WordsFil.txt"));
  }

  @Test
  public void fileIncorrectMessageTest() {
    Words words = new Words();
    System.setOut(new PrintStream(outContent));
    words.getRandomWord("WordsFil.txt");
    assertEquals("File error\r\n", outContent.toString());	// https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
    System.setOut(System.out);
  }
}
