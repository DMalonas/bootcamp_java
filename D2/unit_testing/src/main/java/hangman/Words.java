package hangman;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Words {
  private static final int choice1 = 1;
  private static final int choice2 = 2;
  
  private String[] counties = { 
    "Argyll and Bute", "Caithness",  "Kingdom of Fife",
    "East Lothian", "Highland", "Dumfries and Galloway",
    "Renfrewshire", "Scottish Borders", "Perth and Kinross" 
  };
  private String[] countries = { 
    "Scotland", "England", "Wales", "Northern Ireland", "Ireland", 
    "France", "Germany", "Netherlands", "Spain", "Portugal",
    "Belgium", "Luxembourg", "Switzerland", "Italy", "Greece" 
  };
  private String[] cities = { 
    "St Andrews", "Edinburgh", "Glasgow", "Kirkcaldy", "Perth",
    "Dundee", "Stirling", "Inverness", "Aberdeen", "Falkirk"
  };
  private ArrayList<String> customwords;
  
  /**
   * This method picks a random word.
   * @param category int, one of the three categories.
   * @return
   */
  public String getRandomWord(int category) {
    if (category == choice1) {
      return counties[(int)(Math.random() * counties.length)];
    }
    if (category == choice2) {
      return countries[(int)(Math.random() * countries.length)];
    }
    return cities[(int)(Math.random() * cities.length)];
  }

  /**
   * This method is responsible for choosing a random word.
   * @param wordsource the String value of the wordsource variable.
   * @return String, a word.
   */
  public String getRandomWord(String wordsource) {
    String line;
    customwords = new ArrayList<String>();
    try {
      FileReader file = new FileReader(wordsource);
      BufferedReader reader = new BufferedReader(file);
      while ((line = reader.readLine()) != null) {
        customwords.add(line);
      }
      return customwords.get((int)(Math.random() * customwords.size()));
    } catch (FileNotFoundException e) {
      System.out.println("File error");
      return "";
    } catch (IOException e) {
      System.out.println("IO error");
      return "";
    }
  }
}
