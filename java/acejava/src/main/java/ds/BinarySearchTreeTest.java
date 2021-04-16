package ds;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ds.BinarySearchTree.TreeNode;
import util.ArrayUtil;

class BinarySearchTreeTest {
	
	//{100,50,200,25,75,125,350};//
	private int[] nodeVals = {25,20,10,5,12,22,36,30,28,40,38,48};
	private int[] levelledNodes = {25,20,36,10,22,30,40,5,12,28,38,48};
	private BinarySearchTree tree;
	private int[] nodeValsFixed = {15, 85, 6, 38, 13, 70};
	private int[] nodeValsInOrder = {6, 13, 15, 38, 70, 85};
	private int[] nodeValsPreOrder = {15, 6, 13, 85, 38, 70};
	private int[] nodeValsPostOrder = {13, 6, 70, 38, 85, 15};

	@BeforeEach
	void setUp() throws Exception {
		//create an array of random node values to populate the tree
		int len = 6;
		int bound = 100;
		//nodeVals = ;//ArrayUtil.getIntArray(len, bound);
		
		//create a tree
		tree = new BinarySearchTree();
		
		//populate the tree
		testAddNode();
	}

	@Test
	void testAddNode() {
		for (int val: nodeVals) {
			tree.addNode(val);
		}
	}
	
	@Test
	void testAddNodes() {
		BinarySearchTree treePrivate = new BinarySearchTree(); //create a tree
		treePrivate.addNodes(nodeVals); //add nodes
		//compare treePrivate with tree
		int[] nodeValsThis = tree.inOrder();
		int[] nodeValsPrivate = treePrivate.inOrder();
		assertArrayEquals(nodeValsThis, nodeValsPrivate);
	}

	@Test
	void testInOrder() {
		int[] inOrderNodeVals = tree.inOrder(); //traverse in-order
		
		assertEquals(nodeVals.length, inOrderNodeVals.length); //assert node count
		//assert node order
		for (int i = 0; i < inOrderNodeVals.length - 1; i++) {
			assert(inOrderNodeVals[i] <= inOrderNodeVals[i+1]);
		}
	}
	
	@Test
	void testInOrderIterative() {
		int[] inOrderNodeVals = tree.inOrderIterative();
		
		assertEquals(nodeVals.length, inOrderNodeVals.length); //assert node count
		//assert node order
		for (int i = 0; i < inOrderNodeVals.length - 1; i++) {
			assert(inOrderNodeVals[i] <= inOrderNodeVals[i+1]);
		}
	}
	
	@Test
	void testPreOrder() {
		BinarySearchTree treePrivate = new BinarySearchTree(); //create a tree
		treePrivate.addNodes(this.nodeValsFixed); //add nodes
		
		int[] preOrderNodeVals = treePrivate.preOrder(); //traverse pre-order
		
		assertEquals(this.nodeValsFixed.length, preOrderNodeVals.length); //assert node count
		assertArrayEquals(this.nodeValsPreOrder, preOrderNodeVals); //assert node order
	}

	@Test
	void testPostOrder() {
		BinarySearchTree treePrivate = new BinarySearchTree(); //create a tree
		treePrivate.addNodes(this.nodeValsFixed); //add nodes
		
		int[] postOrderNodeVals = treePrivate.postOrder(); //traverse post-order
		
		assertEquals(this.nodeValsFixed.length, postOrderNodeVals.length); //assert node count
		assertArrayEquals(this.nodeValsPostOrder, postOrderNodeVals); //assert node order
	}

	@Test
	void testInorderSuccessor() {
		for (int predVal : nodeVals) {
			//int predVal = 5;
			BinarySearchTree.InorderSuccessor objSucc = new BinarySearchTree.InorderSuccessor();
			BinarySearchTree.BinaryTreeNode succ = objSucc.inorderSuccessorBST(tree.getRoot(), predVal);
			if (succ != null) {
				System.out.printf("Predessor: %d; Successor: %d\n", predVal, succ.data);
			} else {
				System.out.printf("Predessor: %d; Successor: NULL\n", predVal);
			}
		}
	}
	
	@Test
	void testInorderSuccessorBSTUsingParent() {
		for (int predVal : nodeVals) {
			//int predVal = 5;
			BinarySearchTree.InorderSuccessor objSucc = new BinarySearchTree.InorderSuccessor();
			BinarySearchTree.BinaryTreeNode succ = objSucc.inorderSuccessorBSTUsingParent(tree.getRoot(), predVal);
			if (succ != null) {
				System.out.printf("Predessor: %d; Successor: %d\n", predVal, succ.data);
			} else {
				System.out.printf("Predessor: %d; Successor: NULL\n", predVal);
			}
		}
	}
	
	@Test
	void testLevelOrderTraversal() {
		String strlevelledNodes = Arrays.toString(levelledNodes);
		strlevelledNodes = strlevelledNodes.replace(',', ' ');
		strlevelledNodes = strlevelledNodes.replace('[', ' ');
		strlevelledNodes = strlevelledNodes.replace(']', ' ');
		//https://stackoverflow.com/questions/2932392/java-how-to-replace-2-or-more-spaces-with-single-space-in-string-and-delete-lead#:~:text=replaceAll(%22%5C%5Cs%7B,quicker%20as%20someone%20pointed%20out).&text=This%20will%20match%20more%20than%20one%20space.
		strlevelledNodes = strlevelledNodes.replaceAll("\\s{2,}", " ").trim();
		System.out.printf("%s\n", strlevelledNodes);
		
		String strlevelledNodesComputed = tree.levelOrderTraversal(tree.getRoot());
		System.out.printf("%s\n", strlevelledNodesComputed);
		assertEquals(strlevelledNodes, strlevelledNodesComputed);
	}
	
	//ReverseLevelOrderTraversal
	@Test
	void testReverseLevelOrderTraversal() {
		BinarySearchTree.ReverseLevelOrderTraversal obj = new BinarySearchTree.ReverseLevelOrderTraversal();
		
		TreeNode root = new TreeNode(12);
	    root.left = new TreeNode(7);
	    root.right = new TreeNode(1);
	    root.left.left = new TreeNode(9);
	    root.right.left = new TreeNode(10);
	    root.right.right = new TreeNode(5);
		
		
		List<List<Integer>> result = obj.traverse(root);
		int levels = result.size();
		for (List<Integer> levelNodes: result) {
			System.out.printf("Level %d: ", levels);
			levels--;
			for (Integer i: levelNodes) {
				System.out.printf("%d ", i);
			}
			System.out.println();
		}
	}
}
