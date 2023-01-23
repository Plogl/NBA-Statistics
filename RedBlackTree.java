import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Stack;


/**
 * Red-Black Tree implementation with a Node inner class for representing
 * the nodes of the tree. Currently, this implements a Binary Search Tree that
 * we will turn into a red black tree by modifying the insert functionality.
 * In this activity, we will start with implementing rotations for the binary
 * search tree insert algorithm. You can use this class' insert method to build
 * a regular binary search tree, and its toString method to display a level-order
 * traversal of the tree.
 */
public class RedBlackTree<T extends Comparable<T>> {

    /**
     * This class represents a node holding a single value within a binary tree
     * the parent, left, and right child references are always maintained.
     */
    protected static class Node<T> {
        public T data;
        public INBAPlayer playerData; 

        //TODO I store all the data in one map
        public Node<T> parent; // null for root node
        public Node<T> leftChild;
        public Node<T> rightChild;

        public Node(T data, INBAPlayer player) {
            this.data = data;
            this.playerData = player;
        }//***

        public int blackHeight = 0;//****
		public boolean isBlack = false;

        public Node(int blackHeight) {
            this.blackHeight = blackHeight;
        }//****

        /**
         * @return true when this node has a parent and is the left child of
         * that parent, otherwise return false
         */
        public boolean isLeftChild() {
            return parent != null && parent.leftChild == this;
        }

        public boolean isRightChild() {
            return parent != null && parent.rightChild == this;
        }

    }

    protected Node<T> root; // reference to root node of tree, null when empty
    protected int size = 0; // the number of values in the tree




    //TODO remove funtion
    public boolean delete(T data) throws NullPointerException, IllegalArgumentException{
        //This step is like to insert "but the question is to find to fit position"
        if (data == null) throw new NullPointerException(
                "This RedBlackTree cannot store null references.");

        //Node<T> newNode = new Node<>(data);
        if (this.root == null) {
            throw new NullPointerException("No Node exists now");

        } // add first node to an empty tree
        else {
            Boolean targetNode = findHelper(data, this.root); // Though use insert helper to detect whether there are new Node
            if (targetNode) {
                //TODO find the node, to do the delete function
                boolean answer = deleteHelper(data,this.root);
                return answer;
            }

            else {
                throw new IllegalArgumentException("There is not this value");
            }
        }
    }
    public boolean findHelper(T data, Node<T> subtree){
        if (subtree == null) {
            // we are at a null child, value is not in tree
            return false;
        } else {
            int compare = data.compareTo(subtree.data);
            if (compare < 0) {
                // go left in the tree
                return findHelper(data, subtree.leftChild);
            } else if (compare > 0) {
                // go right in the tree
                return findHelper(data, subtree.rightChild);
            } else {
                // we found it :)
                return true;
            }
        }
    }   
    public boolean deleteHelper(T data, Node<T> node) {
        Node<T> current;
        while (node != null) {
            //TODO case 1
            int compare = data.compareTo(node.data);
            if (compare > 0) {
                node = node.rightChild;
            } else if (compare < 0) {
                node = node.leftChild;
            } else {
                //TODO Make sure how many children the node has;
                if(node.leftChild!=null && node.rightChild!=null){
                    //double black
                }

                //TODO No child
                else if(node.leftChild==null && node.rightChild==null){
                    if(node.blackHeight==0){
                        if(node.isLeftChild()){ node.parent.leftChild=null; return true;}//TODO Red node
                        else {node.parent.rightChild=null; return true;}
                        //我觉得这里就出现了释放内存的问题
                    }
                    else{//TODO the most complex situation

                    }

                }
                //TODO One Child
                else{
                    if(node.blackHeight==0){//TODO Red node one child
                        if(node.leftChild!=null){
                            node.leftChild.parent=node.parent;
                            //看看这里的parent能否用
                            return true;
                        }
                        else{node.rightChild.parent=node.parent;}
                            return true;

                    }
                    else{
                        if(node==this.root){//node is root
                            if(node.leftChild!=null){this.root=node.leftChild;this.root.parent=null;this.root.leftChild=null;return true;}
                            else{this.root=node.rightChild;this.root.parent=null;this.root.rightChild=null;return true;}
                        }
                        else{
                            if(node.leftChild!=null){
                                if(node.isLeftChild()){
                                    node.parent.leftChild=node.leftChild;
                                    node.leftChild.parent=node.parent;
                                }
                                else{
                                    node.parent.rightChild=node.leftChild;
                                    node.leftChild.parent=node.parent;
                                }

                            }
                            else{
                                if(node.isLeftChild()){
                                    node.parent.leftChild=node.rightChild;
                                    node.rightChild.parent=node.parent;
                                }
                                else{
                                    node.parent.rightChild=node.rightChild;
                                    node.rightChild.parent=node.parent;
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new RuntimeException("Seems like the node you want find is not in list.");
    }

    /**
     * Performs a naive insertion into a binary search tree: adding the input
     * data value to a new node in a leaf position within the tree. After
     * this insertion, no attempt is made to restructure or balance the tree.
     * This tree will not hold null references, nor duplicate data values.
     *
     * @param data to be added into this binary search tree
     * @return true if the value was inserted, false if not
     * @throws NullPointerException     when the provided data argument is null
     * @throws IllegalArgumentException when the newNode and subtree contain
     *                                  equal data references
     */
    public boolean insert(T data, INBAPlayer player) throws NullPointerException, IllegalArgumentException {
        // null references cannot be stored within this tree
        if (data == null) throw new NullPointerException(
                "This RedBlackTree cannot store null references.");

        Node<T> newNode = new Node<>(data, player);
        if (root == null) {
            root = newNode;
            size++;
            if (root.blackHeight == 0) {
                root.blackHeight = 1;
            }//I just change the value here because the default node is a red Node.
            return true;
        } // add first node to an empty tree
        else {
            boolean returnValue = insertHelper(newNode, root); // recursively insert into subtree
            if (returnValue) size++;
            else throw new IllegalArgumentException(
                    "This RedBlackTree already contains that value.");
            return returnValue;
        }
    }

   /**
     * Recursive helper method to find the subtree with a null reference in the
     * position that the newNode should be inserted, and then extend this tree
     * by the newNode in that position.
     *
     * @param newNode is the new node that is being added to this tree
     * @param subtree is the reference to a node within this tree which the
     *                newNode should be inserted as a descenedent beneath
     * @return true is the value was inserted in subtree, false if not
     */
    private boolean insertHelper(Node<T> newNode, Node<T> subtree) {
        int compare = newNode.data.compareTo(subtree.data);
        // do not allow duplicate values to be stored within this tree

            // store newNode within left subtree of subtree
        if (compare < 0) {
            if (subtree.leftChild == null) { // left subtree empty, add here
                subtree.leftChild = newNode;
                newNode.parent = subtree;
                enforceRBTreePropertiesAfterinsert(newNode); //Test After inserting
                if (this.root.blackHeight == 0) {
                    this.root.blackHeight = 1;
                }
                return true;
                // otherwise continue recursive search for location to insert
            } else return insertHelper(newNode, subtree.leftChild);
        }

        // store newNode within the right subtree of subtree
        else {
            if (subtree.rightChild == null) { // right subtree empty, add here
                subtree.rightChild = newNode;
                newNode.parent = subtree;
                enforceRBTreePropertiesAfterinsert(newNode);//Test After inserting
                if (this.root.blackHeight == 0) {
                    this.root.blackHeight = 1;
                }
                return true;
                // otherwise continue recursive search for location to insert
            } else return insertHelper(newNode, subtree.rightChild);
        }
    }

    protected void enforceRBTreePropertiesAfterinsert(Node<T> childNode) throws IllegalArgumentException {
    	//do not need to check if the node is null because this method will never be called with that as a parameter
    	//checks to see if childNode is the root
        if(childNode == this.root) {
        	childNode.isBlack  = true;
        	childNode.blackHeight = 1;
        			
        	return;
        }
        Node<T> parentNode = childNode.parent; //Parent of childNode
        
      //If parent is root, nothing will be changed
        if(parentNode == this.root) {
        	return;
        }
        
        //If child node is Black, nothing will be changed
        if(parentNode.isBlack) {
        	return;
        }
        
        Node<T> grandparentNode = childNode.parent.parent; //grandparent of childNode
        Node<T> uncleNode;//uncle of childNode
        
        //sets uncleNode
        if(parentNode.isLeftChild()) {
        	uncleNode = grandparentNode.rightChild;
        }
        else {
        	uncleNode = grandparentNode.leftChild;
        }
        
        //If uncle node isn't null and is red, then uncleNode and parentNode will turn black
        //and change their blackHeight to 1. Then it will recurse at the grandparentNode to ensure
        //that red=black properties are still being followed until root is reached.
        if( uncleNode != null && uncleNode.blackHeight == 0) {
            parentNode.blackHeight = 1;
            parentNode.isBlack = true;
            uncleNode.blackHeight = 1;
            uncleNode.isBlack = true;
            grandparentNode.blackHeight = 0;
            grandparentNode.isBlack = false;
            enforceRBTreePropertiesAfterinsert(grandparentNode);
            return;
        }

        
        //If the childNode and parentNode are not on the same side of their respective parents,
        //then childNode and parentNode are rotated to ensure that they are by recursing with 
        // the parent node. Only neccesary when parentNode is red and uncleNode is black.
        if(childNode.isLeftChild() != parentNode.isLeftChild()){
            this.rotate(childNode, parentNode);
            enforceRBTreePropertiesAfterinsert(parentNode);
            return;
        }

        //Occurs when parentNode was red and uncleNode was black. Completes a rotation with
        //the parentNode and the grandparentNode. Causes grandParent Node to become red and 
        //parentNode becomes black.
        rotate(parentNode, grandparentNode);
        boolean color = grandparentNode.isBlack;
        grandparentNode.isBlack = parentNode.isBlack;
        parentNode.isBlack = color;
        int height = grandparentNode.blackHeight;
        grandparentNode.blackHeight = parentNode.blackHeight;
        parentNode.blackHeight = height;
        }
        //Throw a Exception if input is wrong/

   protected void checkbalance(Node<T> newNode) {
        if (newNode.parent.parent != null && newNode.parent.parent.parent != null && newNode.parent.parent.parent.parent != null) {
            if (newNode.parent.parent.parent.isRightChild() == true) {
                if (newNode.parent.parent.parent.leftChild.leftChild == null &&
                        newNode.parent.parent.parent.leftChild.rightChild == null) {
                    rotate(newNode.parent.parent.parent, newNode.parent.parent.parent.parent);
                    newNode.parent.parent.parent.leftChild.blackHeight = 0;
                    newNode.parent.parent.parent.blackHeight = 1;
                }

            } else {
                if (newNode.parent.parent.parent.rightChild.leftChild == null &&
                        newNode.parent.parent.parent.rightChild.rightChild == null) {
                    rotate(newNode.parent.parent.parent, newNode.parent.parent.parent.parent);
                    //newNode.parent.parent.parent.parent.leftChild.blackHeight=0;
                    newNode.parent.parent.parent.rightChild.blackHeight = 0;
                    newNode.parent.parent.parent.blackHeight = 1;
                }
            }

        }
    }

    /**
     * Performs the rotation operation on the provided nodes within this tree.
     * When the provided child is a leftChild of the provided parent, this
     * method will perform a right rotation. When the provided child is a
     * rightChild of the provided parent, this method will perform a left rotation.
     * When the provided nodes are not related in one of these ways, this method
     * will throw an IllegalArgumentException.
     *
     * @param child  is the node being rotated from child to parent position
     *               (between these two node arguments)
     * @param parent is the node being rotated from parent to child position
     *               (between these two node arguments)
     * @throws IllegalArgumentException when the provided child and parent
     *                                  node references are not initially (pre-rotation) related that way
     */
    private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
    	if(parent == null || child == null) {
    		throw new IllegalArgumentException("null object parent or child");
    	}
        if(child.parent != parent) {
        	throw new IllegalArgumentException("Provided child is not a child of provided parent");
        }
        
    	// left-right rotation
    	if (child.isLeftChild() && child.parent.equals(parent)) { 
    	      
    		if (!this.root.equals(parent)) {
    	        child.parent = parent.parent;
    	        
    	        if (parent.isLeftChild()) {
    	          parent.parent.leftChild = child;
    	        } 
    	        else {
    	          parent.parent.rightChild = child;
    	        }
    	      }
    		
    		else {
    	        root = child;
    	        root.parent = null;
    	      }

    	      Node<T> lastChildRight = child.rightChild;
    	      child.rightChild = parent;
    	      parent.parent = child;
    	      
    	      if (lastChildRight != null) {
    	        lastChildRight.parent = parent;
    	        parent.leftChild = lastChildRight;
    	      } 
    	      else {
    	        parent.leftChild = null;
    	      }

    	    } 
    	// right-left rotation
    	else if (!child.isLeftChild() && child.parent.equals(parent)) { // right-left rotation
    	      
    		if (!this.root.equals(parent)) {
    	        child.parent = parent.parent;
    	        
    	        if (parent.isLeftChild()) {
    	          parent.parent.leftChild = child;
    	        } 
    	        
    	        else {
    	          parent.parent.rightChild = child;
    	        }
    	      } 
    		
    		else {
    	        root = child;
    	        root.parent = null;
    	      }

    	      Node<T> lastChildLeft= child.leftChild;
    	      child.leftChild = parent;
    	      parent.parent = child;
    	      
    	      if (lastChildLeft != null) {
    	        lastChildLeft.parent = parent;
    	        parent.rightChild = lastChildLeft;
    	      } 
    	      
    	      else {
    	        parent.rightChild = null;
    	      }

    	    } 
    }


    /**
     * Get the size of the tree (its number of nodes).
     *
     * @return the number of nodes in the tree
     */
    public int size() {
        return size;
    }

    /**
     * Method to check if the tree is empty (does not contain any node).
     *
     * @return true of this.size() return 0, false if this.size() > 0
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Checks whether the tree contains the value *data*.
     *
     * @param data the data value to test for
     * @return true if *data* is in the tree, false if it is not in the tree
     */
    public boolean contains(T data) {
        // null references will not be stored within this tree
        if (data == null) throw new NullPointerException(
                "This RedBlackTree cannot store null references.");
        return this.containsHelper(data, root);
    }

    /**
     * Recursive helper method that recurses through the tree and looks
     * for the value *data*.
     *
     * @param data    the data value to look for
     * @param subtree the subtree to search through
     * @return true of the value is in the subtree, false if not
     */
    private boolean containsHelper(T data, Node<T> subtree) {
        if (subtree == null) {
            // we are at a null child, value is not in tree
            return false;
        } else {
            int compare = data.compareTo(subtree.data);
            if (compare < 0) {
                // go left in the tree
                return containsHelper(data, subtree.leftChild);
            } else if (compare > 0) {
                // go right in the tree
                return containsHelper(data, subtree.rightChild);
            } else {
                // we found it :)
                return true;
            }
        }
    }


    /**
     * This method performs an inorder traversal of the tree. The string
     * representations of each data value within this tree are assembled into a
     * comma separated string within brackets (similar to many implementations
     * of java.util.Collection, like java.util.ArrayList, LinkedList, etc).
     * Note that this RedBlackTree class implementation of toString generates an
     * inorder traversal. The toString of the Node class class above
     * produces a level order traversal of the nodes / values of the tree.
     *
     * @return string containing the ordered values of this tree (in-order traversal)
     */
    public String toInOrderString() {
        // generate a string of all values of the tree in (ordered) in-order
        // traversal sequence
        StringBuffer sb = new StringBuffer();
        sb.append("[ ");
        sb.append(toInOrderStringHelper("", this.root));
        if (this.root != null) {
            sb.setLength(sb.length() - 2);
        }
        sb.append(" ]");
        return sb.toString();
    }

    private String toInOrderStringHelper(String str, Node<T> node) {
        if (node == null) {
            return str;
        }
        str = toInOrderStringHelper(str, node.leftChild);
        str += (node.data.toString() + ", ");
        str = toInOrderStringHelper(str, node.rightChild);
        return str;
    }

    /**
     * This method performs a level order traversal of the tree rooted
     * at the current node. The string representations of each data value
     * within this tree are assembled into a comma separated string within
     * brackets (similar to many implementations of java.util.Collection).
     * Note that the Node's implementation of toString generates a level
     * order traversal. The toString of the RedBlackTree class below
     * produces an inorder traversal of the nodes / values of the tree.
     * This method will be helpful as a helper for the debugging and testing
     * of your rotation implementation.
     *
     * @return string containing the values of this tree in level order
     */
    public String toLevelOrderString() {
        String output = "[ ";
        if (this.root != null) {
            LinkedList<Node<T>> q = new LinkedList<>();
            q.add(this.root);
            while (!q.isEmpty()) {
                Node<T> next = q.removeFirst();
                if (next.leftChild != null) q.add(next.leftChild);
                if (next.rightChild != null) q.add(next.rightChild);
                output += next.data.toString();
                if (!q.isEmpty()) output += ", ";
            }
        }
        return output + " ]";
    }

    public String toString() {
        return "level order: " + this.toLevelOrderString() +
                "\nin order: " + this.toInOrderString();
    }
}
