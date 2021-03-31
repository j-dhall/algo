package ds;

import util.ArrayUtil;

public class BinarySearchTree {

	class BinaryTreeNode {
		int data;
		BinaryTreeNode left;
		BinaryTreeNode right;
		
		//node constructor
		BinaryTreeNode (int val) {
			this.data = val;
		}
		
		//add node
		void addNode (int val) {
			if (val <= this.data) { //add node to the left subtree
				if (left != null) {
					left.addNode(val); //traverse down the left child
				} else {
					left = new BinaryTreeNode (val); //create a left child
				}
			} else { //add node to the right subtree
				if (right != null) {
					right.addNode(val); //traverse down the right child
				} else {
					right = new BinaryTreeNode (val); //create a right child
				}
			}
		} //Node.addNode ()
		
		int[] inOrder () {
			int[] thisVal = {this.data}; //convert val to integer array
			int[] leftVals = null, rightVals = null; //values from the left and right subtrees
			
			//traverse the left subtree
			if (left != null) {
				leftVals = left.inOrder();
			}
			
			//traverse the right subtree
			if (right != null) {
				rightVals = right.inOrder();
			}
			
			int[] leftAndThis = ArrayUtil.concatArrays(leftVals, thisVal);
			return ArrayUtil.concatArrays(leftAndThis, rightVals);
			
		} //Node.inOrder ()
		
		int[] preOrder () {
			int[] thisVal = {this.data}; //convert val to integer array
			int[] leftVals = null, rightVals = null; //values from the left and right subtrees
			
			//traverse the left subtree
			if (left != null) {
				leftVals = left.preOrder();
			}
			
			//traverse the right subtree
			if (right != null) {
				rightVals = right.preOrder();
			}
			
			int[] thisAndLeft = ArrayUtil.concatArrays(thisVal, leftVals);
			return ArrayUtil.concatArrays(thisAndLeft, rightVals);
			
		} //Node.preOrder ()
		
		int[] postOrder () {
			int[] thisVal = {this.data}; //convert val to integer array
			int[] leftVals = null, rightVals = null; //values from the left and right subtrees
			
			//traverse the left subtree
			if (left != null) {
				leftVals = left.postOrder();
			}
			
			//traverse the right subtree
			if (right != null) {
				rightVals = right.postOrder();
			}
			
			int[] leftAndRight = ArrayUtil.concatArrays(leftVals, rightVals);
			return ArrayUtil.concatArrays(leftAndRight, thisVal);
			
		} //Node.postOrder ()

	} //class Node
	
	private BinaryTreeNode root;
	
	BinaryTreeNode getRoot () {
		return root;
	}
	
	void addNode (int val) {

		//if this is the first node of the tree
		if (root == null) {
			root = new BinaryTreeNode (val);
			return;
		}
		
		//if we already have a root, add the node to the root subtree
		root.addNode(val);
	}
	
	void addNodes (int[] vals) {
		//if this is the first node of the tree
		if (root == null) {
			root = new BinaryTreeNode (vals[0]);
			
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
	
	//for educative.io
	
	public static int[] inOrder (BinaryTreeNode node) {
		int[] thisVal = {node.data}; //convert val to integer array
		int[] leftVals = null, rightVals = null; //values from the left and right subtrees
		
		//traverse the left subtree
		if (node.left != null) {
			leftVals = inOrder(node.left);
		}
		
		//traverse the right subtree
		if (node.right != null) {
			rightVals = inOrder(node.right);
		}
		
		int[] leftAndThis = ArrayUtil.concatArrays(leftVals, thisVal);
		return ArrayUtil.concatArrays(leftAndThis, rightVals);
		
	} //static inOrder ()
	
	//for educative.io
	
	public static boolean areIdentical (BinaryTreeNode root1, BinaryTreeNode root2) {
		
		if (root1 == null) {
			if (root2 == null) {
				return true;
			} else {
				return false;
			}
		}
		if (root2 == null) {
			return false;
		}
		
		int[] nodeValsRoot1 = inOrder(root1);
		int[] nodeValsRoot2 = inOrder(root2);
		
		//compare number of nodes
		if (nodeValsRoot1.length != nodeValsRoot2.length) {
			return false;
		}
		
		for (int i = 0; i < nodeValsRoot1.length; i++) {
			if (nodeValsRoot1[i] != nodeValsRoot2[i]) {
				return false;
			}
		}
		
		return true;
	}

}
