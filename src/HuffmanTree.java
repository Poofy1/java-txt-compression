import java.util.*;

public class HuffmanTree {
	
	private class Node {
		private Node left;
		private char data;
		private Node right;
		private Node(Node L, char d, Node R) {
			left = L;
			data = d;
			right = R;
		}
	}
	private Node root;
	private Node current; //this value is changed by the move methods
	
	
	
	public HuffmanTree() {
		root = null;
		current = null;
	}
	
	public HuffmanTree(char d) {
		//makes a single node tree
		root = new Node(null, d, null);
	}
	
	public HuffmanTree(String t, char nonLeaf) {
		//Assumes t represents a post order representation of the tree
		//nonLeaf is the char value of the data in the non-leaf nodes
		//in the following I will use (char) 128 for the non-leaf value
		Stack<Node> stack = new Stack<Node>();
		
		for (int i = 0; i < t.length(); i++) {
			
			if(t.charAt(i) != nonLeaf) stack.push(new Node(null, t.charAt(i), null));
			else {
				Node temp = new Node(null, nonLeaf, null);
				temp.right = stack.pop();
				temp.left = stack.pop();
				stack.push(temp);
			}
		}
		
		root = stack.pop();
	}
	
	public HuffmanTree(HuffmanTree b1, char d, HuffmanTree b2) {
		//makes a new tree where b1 is the left subtree and b2 is the right subtree
		//d is the data in the root
		root = new Node(b1.root, d, b2.root);
	}
	
	//the move methods to traverse the tree
	//the move methods change the value of current
	//use these in the decoding process
	
	public void moveToRoot() {
		//change current to reference the root of the tree
		current = root;
	}
	
	public void moveToLeft() {
		//PRE: the current node is not a leaf
		//change current to reference the left child of the current node
		current = current.left;
	}
	
	public void moveToRight() {
		//PRE: the current node is not a leaf
		//change current to reference the right child of the current node
		current = current.right;
	}
	
	public boolean atRoot() {
		//returns true if the current node is the root otherwise returns false
		if(current != root) return false;
		return true;
	}
	
	public boolean atLeaf() {
		//returns true if current references a leaf other wise returns false
		if(current.left == null && current.left == null) return true;
		return false;
	}
	
	public char current() {
		//returns the data value in the node referenced by current
		return current.data;
	}
	
	public String[] pathsToLeaves() {
		/*returns an array of 128 strings (some of which could be null) with all paths from the root to the leaves
		Each string will be a string of 0s and 1s. Store the path for a particular leaf in the array
		at the location of the leaf value’s character code
		*/
		String[] paths = new String[128];
		for(int i = 0; i < 128; i++) paths[i] = "";
		if(root != null) pathsToLeaves(root, paths, "");
		return paths;
	}
	
	//Recursive method to follow paths to leaves
	private void pathsToLeaves(Node n, String[] paths, String p) {
		if(n.right == null && n.left == null) {
			paths[n.data] = p;
			return;
		}
		
		if(n.left != null) pathsToLeaves(n.left, paths, p + "0");
		if(n.right != null) pathsToLeaves(n.right, paths, p + "1");
	}
	
	
	String output;
	public String toString() {
		//returns a string representation of the tree using the postorder format
		// discussed in class
		output = "";
		if(root != null) toString(root);
		return output;
	}
	
	//Recursive method to read post order of tree
	private void toString(Node n) {
		if (n == null) return;
		toString(n.left);
		toString(n.right);
	    output += n.data;

	}
}