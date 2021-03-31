package ds;

import util.ArrayUtil;

public class BinarySearchTree {

	class Node {
		int val;
		Node leftChild;
		Node rightChild;
		
		//node constructor
		Node (int val) {
			this.val = val;
		}
		
		//add node
		void addNode (int val) {
			if (val <= this.val) { //add node to the left subtree
				if (leftChild != null) {
					leftChild.addNode(val); //traverse down the left child
				} else {
					leftChild = new Node (val); //create a left child
				}
			} else { //add node to the right subtree
				if (rightChild != null) {
					rightChild.addNode(val); //traverse down the right child
				} else {
					rightChild = new Node (val); //create a right child
				}
			}
		} //Node.addNode ()
		
		int[] inOrder () {
			int[] thisVal = {this.val}; //convert val to integer array
			int[] leftVals = null, rightVals = null; //values from the left and right subtrees
			
			//traverse the left subtree
			if (leftChild != null) {
				leftVals = leftChild.inOrder();
			}
			
			//traverse the right subtree
			if (rightChild != null) {
				rightVals = rightChild.inOrder();
			}
			
			int[] leftAndThis = ArrayUtil.concatArrays(leftVals, thisVal);
			return ArrayUtil.concatArrays(leftAndThis, rightVals);
			
		} //Node.inOrder ()
		
		int[] preOrder () {
			int[] thisVal = {this.val}; //convert val to integer array
			int[] leftVals = null, rightVals = null; //values from the left and right subtrees
			
			//traverse the left subtree
			if (leftChild != null) {
				leftVals = leftChild.preOrder();
			}
			
			//traverse the right subtree
			if (rightChild != null) {
				rightVals = rightChild.preOrder();
			}
			
			int[] thisAndLeft = ArrayUtil.concatArrays(thisVal, leftVals);
			return ArrayUtil.concatArrays(thisAndLeft, rightVals);
			
		} //Node.preOrder ()
		
		int[] postOrder () {
			int[] thisVal = {this.val}; //convert val to integer array
			int[] leftVals = null, rightVals = null; //values from the left and right subtrees
			
			//traverse the left subtree
			if (leftChild != null) {
				leftVals = leftChild.postOrder();
			}
			
			//traverse the right subtree
			if (rightChild != null) {
				rightVals = rightChild.postOrder();
			}
			
			int[] leftAndRight = ArrayUtil.concatArrays(leftVals, rightVals);
			return ArrayUtil.concatArrays(leftAndRight, thisVal);
			
		} //Node.postOrder ()

	} //class Node
	
	private Node root;
	
	Node getRoot () {
		return root;
	}
	
	void addNode (int val) {

		//if this is the first node of the tree
		if (root == null) {
			root = new Node (val);
			return;
		}
		
		//if we already have a root, add the node to the root subtree
		root.addNode(val);
	}
	
	void addNodes (int[] vals) {
		//if this is the first node of the tree
		if (root == null) {
			root = new Node (vals[0]);
			
			//add the non-root nodes
			for (int i = 1; i < vals.length; i++) {
				root.addNode(vals[i]);
			}
		} else {
			//we already have a tree. add nodes to the root's subtree
			for (int val: vals) {
				root.addNode(val);
			}
		}
	}
	
	int[] inOrder () {
		//if the tree is empty
		if (root == null) {
			return null;
		}
		
		return root.inOrder();
	}
	
	int[] preOrder () {
		//if the tree is empty
		if (root == null) {
			return null;
		}
		
		return root.preOrder();
	}
	
	int[] postOrder () {
		//if the tree is empty
		if (root == null) {
			return null;
		}
		
		return root.postOrder();
	}
	
	int[] postOrder (Node n) {
		return null;
	}
}
