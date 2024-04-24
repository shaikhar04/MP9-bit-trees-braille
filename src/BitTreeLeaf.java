/**
 * Implements a node to store the leaves. Stores the value corrosponding to its bit position in the tree. 
 * 
 * @author Arsal Shaikh
 * @author Samuel A. Rebelsky
 */
public class BitTreeLeaf implements BitTreeNode {

  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+
  String value;
  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+
  public BitTreeLeaf(String value) {
    this.value = value;
  } // BitTreeLeaf()

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  public String getValue() {
    return this.value;
  } // getValue()
  
} // class BitTreeLeaf
