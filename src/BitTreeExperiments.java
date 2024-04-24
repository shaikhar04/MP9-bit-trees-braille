import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;

public class BitTreeExperiments {
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    // BitTree treeSet = new BitTree(3);
    // treeSet.set("010", "Dog");
    // treeSet.set("001", "Fish");
    // treeSet.set("101", "Sparrow");
    
    // pen.println();
    // pen.println(treeSet.get("010"));
    // pen.println(treeSet.get("001"));

    // Testing load(InputStream source) method
    pen.println("------------------------------------------------");
    BitTree treeLoad = new BitTree(6);
    try {
    InputStream source = new FileInputStream("src/treeMappings.txt");
    treeLoad.load(source);
      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } // try catch


  } // main method
} // class
