import java.io.PrintWriter;

/**
 *
 * Main method class for the BrailleASCII project.
 * 
 * @author Arsal Shaikh
 * @author Samuel A. Rebelsky
 */
public class BrailleASCII {

  // +-------------+-------------------------------------------------------
  // | Main Method |
  // +-------------+

  public static void main(String[] args) {
    if (args.length != 2) {
      System.err.print("Usage: java BrailleASCII [braille/ascii/unicode] [text]");
      System.exit(0);
    } // if

    // Setting up objects
    BrailleASCIITables.loadTrees();
    PrintWriter pen = new PrintWriter(System.out, true);
    
    String command = args[0];
    String input = args[1];
    String result = "";
    if (command.equals("braille")) {
      for (int i = 0; i < input.length(); i++) {
        result += BrailleASCIITables.toBraille(input.charAt(i));
      } // for
      pen.println(result);
    } // if
    else if (command.equals("ascii")) {
      String[] letters = BrailleASCII.splitString(input, 6);
      for (String letter : letters) {
        result += BrailleASCIITables.toASCII(letter);
      } // for
      pen.println(result);
    } // if
    else if (command.equals("unicode")) {
      String letterBraille;
      String hexUnicode;
      for (int i = 0; i < input.length(); i++) {
        // first braille to ascii
        letterBraille = BrailleASCIITables.toBraille(input.charAt(i));
        // then ascii to unicode
        hexUnicode = BrailleASCIITables.toUnicode(letterBraille);
        // convert to unicode characters
        pen.println(Character.toChars(Integer.parseInt(hexUnicode, 16)));
      } // for
    } // if
    else {
      // Command line arg not recognized
      System.err.print("Usage: java BrailleASCII [braille/ascii/unicode] [text]");
      System.exit(0);
      return;
    }
  } // main(String[] args)

  // +----------------+----------------------------------------------------
  // | Helper Methods |
  // +----------------+

  /**
   * Splits a string according to length of intervals provided.
   * Returns a String[] with each interval.
   */  
  private static String[] splitString(String input, int interval) {
    int length = input.length();
    int numberOfSubstrings = (length + interval - 1) / interval; // Round up division
    String[] substrings = new String[numberOfSubstrings];
        
    for (int i = 0; i < numberOfSubstrings; i++) {
      int start = i * interval;
      int end = Math.min(start + interval, length);
      substrings[i] = input.substring(start, end);
    }
        
    return substrings;
  } // splitString(String, int)
} // class BrailleASCII
