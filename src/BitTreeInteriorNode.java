/**
 * Implements a class to store the interior nodes of the Binary BitTree. Contains references to its children nodes
 * 
 * @author Arsal Shaikh
 * @author Samuel A. Rebelsky
 */
public class BitTreeInteriorNode implements BitTreeNode {

  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+
  BitTreeNode childZero;
  BitTreeNode childOne;


  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+
  public BitTreeInteriorNode() {
    this.childZero = null;
    this.childOne = null;
  } // BitTreeInteriorNode()

  public BitTreeInteriorNode(BitTreeNode childZero, BitTreeNode childOne) {
    this.childZero = childZero;
    this.childOne = childOne;
  } // BitTreeInteriorNode()

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  
} // class BitTreeInteriorNode
