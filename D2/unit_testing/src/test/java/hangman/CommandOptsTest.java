import java.io.ByteArrayOutputStream;
import static org.junit.Assert.*;
import java.io.PrintStream;
import org.junit.Test;
import hangman.*;


public class CommandOptsTest {
	  //private ByteArrayOutputStream outContent;
	  //public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();


	  @Test
	  public void optionsTest() {
	    String[] args = { "guesses", "2", "hints", "4", "file", "WordsFile.txt" };
	    CommandOpts opts = new CommandOpts(args);
	    assertEquals(opts.getMaxGuesses(), 2);
	    assertEquals(opts.getMaxHints(), 4);
	    assertEquals(opts.getWordSource(), "WordsFile.txt");
	  }
	  
	  @Test
	  public void optionsTest2() {
	    String[] args = { "guesses", "2", "hints", "4", "file", "WordsFile.txt" };
	    CommandOpts opts = new CommandOpts(args);
	    assertEquals(opts.getMaxGuesses(), 2);
	    assertEquals(opts.getMaxHints(), 4);
	    assertEquals(opts.getWordSource(), "WordsFile.txt");
	  }
	  
	  @Test
	  public void optionsTestCheckCorrectInput() {
	    String[] args = { "guesses", "2", "hints", "4", "file", "WordsFile.txt" };
	    CommandOpts opts = new CommandOpts(args);
	    assertTrue(opts.handleCmdLineArgs(args));
	  }
	  
	  @Test
	  public void optionsTestCheckDefaultInput() {
	    String[] args = {};
	    CommandOpts opts = new CommandOpts(args);
	    assertEquals(10, opts.getMaxGuesses());
	    assertEquals(2, opts.getMaxHints());
	    assertEquals("", opts.getWordSource());
	  }
	  
	  @Test
	  public void optionsTestCheckgettersSetters() {
	    String[] args = {};
	    CommandOpts opts = new CommandOpts(args);
	    opts.setMaxGuesses(12);
	    opts.setMaxHints(4);
	    opts.setWordSource("MyFile.txt");
	    assertEquals(12, opts.getMaxGuesses());
	    assertEquals(4, opts.getMaxHints());
	    assertEquals("MyFile.txt", opts.getWordSource());
	  }

}
