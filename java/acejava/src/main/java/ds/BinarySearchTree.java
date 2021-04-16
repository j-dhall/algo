package ds;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import util.ArrayUtil;

public class BinarySearchTree {

	class BinaryTreeNode {
		int data;
		BinaryTreeNode left;
		BinaryTreeNode right;
		BinaryTreeNode parent;
		
		//node constructor
		BinaryTreeNode (int val, BinaryTreeNode parent) {
			this.data = val;
			this.parent = parent;
		}
		
		//add node
		void addNode (int val) {
			if (val <= this.data) { //add node to the left subtree
				if (left != null) {
					left.addNode(val); //traverse down the left child
				} else {
					left = new BinaryTreeNode (val, this); //create a left child
				}
			} else { //add node to the right subtree
				if (right != null) {
					right.addNode(val); //traverse down the right child
				} else {
					right = new BinaryTreeNode (val, this); //create a right child
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

	} //class BinaryTreeNode
	
	private BinaryTreeNode root;
	private int numNodes;
	
	public BinarySearchTree() {
		root = null;
		numNodes = 0;
	}
	
	BinaryTreeNode getRoot () {
		return root;
	}
	
	void addNode (int val) {

		//if this is the first node of the tree
		if (root == null) {
			root = new BinaryTreeNode (val, null);
			numNodes++; //increment the node count
			return;
		}
		
		//if we already have a root, add the node to the root subtree
		root.addNode(val);
		numNodes++; //increment the node count
	}
	
	void addNodes (int[] vals) {
		//if this is the first node of the tree
		if (root == null) {
			root = new BinaryTreeNode (vals[0], null);
			
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
		
		numNodes += vals.length; //increment the node count
	}
	
	int[] inOrder () {
		//if the tree is empty
		if (root == null) {
			return null;
		}
		
		return root.inOrder();
	}
	
	void pushLeftChildren (Stack<BinaryTreeNode> stack, BinaryTreeNode node) {
		while (node != null) {
			stack.push(node);
			node = node.left;
		}
	}
	
	int[] inOrderIterative () {
		//if the tree is empty
		if (root == null) {
			return null;
		}
		
		int[] nodeVals = new int[numNodes];
		int i = 0;
		
		Stack<BinaryTreeNode> stack = new Stack<BinarySearchTree.BinaryTreeNode>(); //create a stack
		pushLeftChildren(stack, root); //from the root, go left till the leaf, pushing all nodes on the stack
		BinaryTreeNode popNode = stack.pop();
		while (popNode != null) {
			
			System.out.printf("Node: %d\n", popNode.data);
			nodeVals[i] = popNode.data; //extract the current node value
			i++; //increment the node index
			
			//if the popped node has a right child, push its left children onto the stack
			if (popNode.right != null) {
				pushLeftChildren(stack, popNode.right);
			}
			//pop the left-most leaf of the right child, if any
			if (!stack.isEmpty()) {
				popNode = stack.pop();
			} else {
				popNode = null;
			}
		}
		
		return nodeVals;
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
	
	//for educative.io
	//no test code

	class InorderIterator {
		
		Stack<BinaryTreeNode> stack = new Stack<BinarySearchTree.BinaryTreeNode>(); //create a stack

		public InorderIterator(BinaryTreeNode root) {
			pushLeftChildren(stack, root); //from the root, go left till the leaf, pushing all nodes on the stack
		}

		public boolean hasNext() {
			if (!stack.isEmpty()) {
				return true;
			}
			return false;
		}

		public BinaryTreeNode getNext() {
			BinaryTreeNode popNode = stack.pop();

			//if the popped node has a right child, push its left children onto the stack
			if (popNode.right != null) {
				pushLeftChildren(stack, popNode.right);
			}
			
			return popNode;
		}
	}
	
	//for educative.io
	//no test code
	
	public static class InorderSuccessor {
		
		BinaryTreeNode succ = null;
		
		public BinaryTreeNode inorderSuccessorBST (BinaryTreeNode root, int d) {
			//go left
			if (d < root.data) {
				if (succ == null) {
					succ = root;
				} else {
					if (root.data > d && root.data < succ.data) {
						succ = root;
					} else {
						System.out.printf("We do have this case. Root: %d; Succ: %d", root.data, succ.data);
					}
				}
				return inorderSuccessorBST(root.left, d);
			} else if (d > root.data) { //go right
				return inorderSuccessorBST(root.right, d);
			} else { //found d
				//if d (root) has no right child, 'succ' now has the successor
				if (root.right == null) {
					return succ;
				}
				
				//if d (root) has a right child, successor is the left-most child of the right subtree
				succ = root.right;
				BinaryTreeNode rightLeast = root.right;
				
				while (rightLeast != null) {
					succ = rightLeast;
					rightLeast = rightLeast.left;
				}
				
				return succ;
			}
		}
		
		public BinaryTreeNode inorderSuccessorBSTUsingParent (BinaryTreeNode root, int d) {

			//traverse down to find d
			while (d != root.data) {
				if (d < root.data) {
					root = root.left;
				} else if (d > root.data) {
					root = root.right;
				}
			}
			
			//we found d
				
			//if d (root) has a right child
			if (root.right != null) {
				root = root.right;
				while (root.left != null) {
					root = root.left;
				}
				return root;
			} else {
				//go up the parent hierarchy and
				//find a node which is a left child of its parent.
				//the parent is the successor
				BinaryTreeNode parent = root.parent;
				while (parent != null) {
					if (parent.left == root) {
						//we found a node which is a left child of its parent
						return parent;
					} else {
						//keep moving up
						root = parent;
						parent = root.parent;
					}
				}
				
				//we did not find any successor
				//this happens if d is the last node
				return null;
			}

		}
	}
	
	public String levelOrderTraversal (BinaryTreeNode root) {
		//queue the children during breadth-first traversal
		Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode> ();
		queue.add(root); //begin by adding root to the queue
		
		//levelled nodes
		String levelledNodes = new String();
		
		while (!queue.isEmpty()) {
			BinaryTreeNode node = queue.remove(); //dequeue the front node
			//enqueue its children (if any)
			if (node.left != null) {
				queue.add(node.left);
			}
			if (node.right != null) {
				queue.add(node.right);
			}
			levelledNodes = levelledNodes.concat(Integer.toString(node.data) + " ");
		}
		
		//removing the trailing space
		if (!levelledNodes.isEmpty()) {
			levelledNodes = levelledNodes.stripTrailing();
		}
		
		return levelledNodes;
	}
}
