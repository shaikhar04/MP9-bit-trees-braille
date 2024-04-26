import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Initializes tree objects and loads data from table files.
 * 
 * @author Arsal Shaikh
 * @author Samuel A. Rebelsky
 */
public class BrailleASCIITables {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  public static BitTree brailleToASCII = new BitTree(6);
  public static BitTree brailleToUnicode = new BitTree(6);
  public static BitTree ASCIIToBraille = new BitTree(8);

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /** 
  * Loads data from files into the tree objects
  */
  public static void loadTrees() {
    try {
      brailleToASCII.load(new FileInputStream("src/BrailleToASCII.txt"));
      brailleToUnicode.load(new FileInputStream("src/BrailleToUnicode.txt"));
      ASCIIToBraille.load(new FileInputStream("src/ASCIIToBraille.txt"));
    } catch (FileNotFoundException fnfe) {
      fnfe.printStackTrace();
    } // try catch
  } // loadTrees()
  
  public static String toBraille(char letter) {
    String bits = "0" + Integer.toBinaryString((int) letter);
    return ASCIIToBraille.get(bits);
  } // toBraille(char letter)
  public static String toASCII(String bits) {
    return brailleToASCII.get(bits);
  } // toASCII(String bits)
  public static String toUnicode(String bits) {
    return brailleToUnicode.get(bits);
  } // toUnicode(String bits)

} // class BrailleASCIITables
