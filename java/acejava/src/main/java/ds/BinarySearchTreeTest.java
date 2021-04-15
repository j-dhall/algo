package ds;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import util.ArrayUtil;

class BinarySearchTreeTest {
	
	private int[] nodeVals = {25,20,10,5,12,22,36,30,28,40,38,48};
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

}
