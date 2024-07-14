package hangman;



public class CommandOpts {

  private int maxGuesses;
  private int maxHints;
  private String wordSource;


  public CommandOpts(String[] args) {
    maxGuesses = 10;
    maxHints = 2;
    wordSource = "";
    if (!handleCmdLineArgs(args)) {
      System.exit(1);
    }
  }
  
  /**
   * Check if we have user args
   * @param args
   * @return
   */
  public boolean handleCmdLineArgs(String[] args) {
    try { 
      for (int i = 0; i < args.length; ++i) {
        if ("guesses".equals(args[i])) {
          maxGuesses = Integer.parseInt(args[i + 1]);
          if (maxGuesses > 11) {
            maxGuesses = 11;
          }
          i++;
        } else if (args[i].equals("hints")) {
          maxHints = Integer.parseInt(args[i + 1]);
          if (maxHints > 11) {
            maxHints = 11;
          }
          i++;
        } else if (args[i].equals("file")){
          wordSource = args[i + 1];
          i++;
        } else {
          System.out.print("Usage: java Hangman guesses "
                    + "<number of guesses allowed> hints <number of hints allowed> "
                    + "file <file name>\n");
          return false;

        }
      }
    } catch (Exception e) {
      System.out.println("Usage: java Hangman guesses "
            + "<number of guesses allowed> hints <number of hints allowed> "
            + "file <file name> ");
      return false;
    }
    return true;
  }

  public int getMaxGuesses() {
    return maxGuesses;
  }
  
  public void setMaxGuesses(int maxGuesses) {
    this.maxGuesses = maxGuesses;
  }
  
  public int getMaxHints() {
    return maxHints;
  }

  public void setMaxHints(int maxHints) {
    this.maxHints = maxHints; 
  }
  
  /**
   * Get the value of the wordSource attribute.
   * @return the wordSource
  */
  public String getWordSource() {
    return wordSource;
  }

  /**
   * Set the value of the wordSource attribute.
   * @param wordSource the wordSource to set
  */
  public void setWordSource(String wordSource) {
    this.wordSource = wordSource;
  }
}
