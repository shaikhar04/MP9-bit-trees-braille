import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.NoSuchElementException;


/**
 * TODO Title.
 *
 * TODO description.
 * 
 * @author Arsal Shaikh
 * @author Samuel A. Rebelsky
 */
public class BitTree {
    // +--------+------------------------------------------------------------
    // | Fields |
    // +--------+
    
    int height;
    BitTreeInteriorNode root;
    
    
    // +--------------+------------------------------------------------------
    // | Constructors |
    // +--------------+

    /**
     * Builds a tree which stores that stores the mappings from the strings of n bits to strings.
     * Implements only interior nodes. Leaf Nodes created in set()
     */
    public BitTree(int n) {
        this.height = n;
        this.root = new BitTreeInteriorNode();        
    } // BitTree(int)


    // +---------+-------------------------------------------------------
    // | Methods |
    // +---------+
    
    /**
     * Traverses the tree using the bits provided and sets the value into the leaf. Throws an exception for invalid arguments.
     * @param bits
     */
    public void set(String bits, String value) {
        // Argument validation
        if (bits.length() != this.height) {
            throw new IllegalArgumentException("Incorrect number of bits.");
        } // if

        if (!(bits instanceof String) || (!bits.contains("0") && !bits.contains("1"))) {
            throw new IllegalArgumentException("Please enter a string of zeros of ones.");
        } // if

        // Parse the bit string
        Character currentBit;
        BitTreeInteriorNode walker = root;
        int i = 0;
        while(i < bits.length() - 1) {
            currentBit = bits.charAt(i);
            
            // if bit is Zero
            if (currentBit.equals('0')) {
                // Create new node if null
                if (walker.childZero == null) {
                    walker.childZero = new BitTreeInteriorNode();
                } // if

                walker = (BitTreeInteriorNode) walker.childZero;
            } // if

            // if bit is One
            if (currentBit.equals('1')) {
                // Create a new node if null
                if (walker.childOne == null) {
                    walker.childOne = new BitTreeInteriorNode();
                } // if
                
                walker = (BitTreeInteriorNode) walker.childOne;
            } // if

            i++;
        } // for

        // LeafNode Step
        currentBit = bits.charAt(i);
        if (currentBit.equals('0')) {
            walker.childZero = new BitTreeLeaf(value);
        } // if
        if (currentBit.equals('1')) {
            walker.childOne = new BitTreeLeaf(value);
        } // if

    } // set(String, String)

    /**
     * Traverses the tree using the bits provided. Returns associated value string. Throws an exception of not found
     * @param bits
     */
    public String get(String bits) {
        // Argument validation
        if (bits.length() != this.height) {
            throw new IllegalArgumentException("Incorrect number of bits.");
        } // if

        if (!(bits instanceof String) || (!bits.contains("0") && !bits.contains("1"))) {
            throw new IllegalArgumentException("Please enter a string of zeros of ones.");
        } // if

        // Parse the bit string
        Character currentBit;
        BitTreeInteriorNode walker = root;
        int i = 0;
        while(i < bits.length() - 1) {
            currentBit = bits.charAt(i);
            
            // if bit is Zero
            if (currentBit.equals('0')) {
                // If null then not found
                if (walker.childZero == null) {
                    throw new NoSuchElementException();
                } // if

                walker = (BitTreeInteriorNode) walker.childZero;
            } // if

            // if bit is One
            if (currentBit.equals('1')) {
                // if null then not found
                if (walker.childOne == null) {
                    throw new NoSuchElementException();
                } // if
                
                walker = (BitTreeInteriorNode) walker.childOne;
            } // if

            i++;
        } // for

        // LeafNode Step
        currentBit = bits.charAt(i);
        if (currentBit.equals('0')) {
            return ((BitTreeLeaf) walker.childZero).value;
        } // if
        if (currentBit.equals('1')) {
            return ((BitTreeLeaf) walker.childOne).value;
        } // if

        // If still not found
        throw new NoSuchElementException();
    } // get(String)
    
    /**
     * Dumps the contents of the tree in CSV format using the PrintWriter.
     */
    public void dump(PrintWriter pen) {
        dump(pen, root, "");
    } // dump(PrintWriter)

    /**
     * Reads a series of lines of the form "bits,value" and stores them in the tree.
     * @param source
     */
    public void load(InputStream source) {
        try {
            int input = 0;
            Character nextChar = ' ';
            
            String line;
            String[] lineTokens;
            String bits;
            String value;
            while (input != -1) {
                // Read each line
                line = "";
                while (true) {;
                    input = source.read();
                    nextChar = (char) input;
                    
                    // Break condition
                    if (nextChar == '\n') {
                        break;
                    }

                    line += nextChar;
                } // while

                lineTokens = line.split(",", 2);
                bits = lineTokens[0];
                value = lineTokens[1];   
                
                // Finally set the pair
                this.set(bits, value);    
            } // while
        } catch (IOException e) {
            e.printStackTrace();
        } // try catch
    } // load(InputStream)

    // +-----------------+-----------------------------------------------
    // | Private Methods |
    // +-----------------+

    private void dump(PrintWriter pen, BitTreeNode node, String path) {
        // if reached a leaf
        if (node instanceof BitTreeLeaf) {
            BitTreeLeaf leafNode = (BitTreeLeaf) node;
            pen.println(path + "," + leafNode.getValue());
        } // if

        // if reached an interior node
        if (node instanceof BitTreeInteriorNode) {
            BitTreeInteriorNode interiorNode = (BitTreeInteriorNode) node;
            if (interiorNode.childZero != null) {
                dump(pen, interiorNode.childZero, path + "0");
            } // if
            if (interiorNode.childOne != null) {
                dump(pen, interiorNode.childOne, path + "1");
            } // if
        } // if
    } // dump(PrintWriter)
} // class BitTree
