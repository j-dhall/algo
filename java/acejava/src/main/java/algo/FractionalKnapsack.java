package algo;

import java.util.PriorityQueue;
import java.util.Queue;

public class FractionalKnapsack {
	class Node implements Comparable<Node>{ //a node in the priority queue
		double w;
		double v;
		double wByV; //weight div by value: this ratio decides priority in this min priority queue
		public Node(double w, double v) { //constructor
			this.w = w;
			this.v = v;
			this.wByV = w/v;
		}
		@Override
		public int compareTo(Node o) {
			return (this.wByV <= o.wByV) ? -1 : +1;
		}
	}
	
	double c; //capacity
	Queue<Node> items = new PriorityQueue<Node>(); //min priority queue, ordered by weight/value ratio
	
	public FractionalKnapsack(double[] w, double[] v, double c) { //constructor
		this.c = c; //store the capacity
		for (int i = 0; i < w.length; i++) { //populate the priority queue
			Node node = new Node(w[i], v[i]);
			items.add(node);
		}
	}
	
	public double getMaxVal() { //get the maximum value of knapsack
		double result = 0; //maximum value got till now
		double remCap = c; //remaining capacity, initialized to full capacity
		
		//1. consider whole items
		
		//while there is capacity left in the sack, and
		//there are items left to be considered, and
		//the next items weight fits the sack capacity
		while (remCap > 0 && !items.isEmpty() && items.peek().w <= remCap) {
			Node dqNode = items.poll(); //remove the top item from the min peiority queue
			result += dqNode.v; //add item's value (equivalent of adding item to knapsack)
			remCap -= dqNode.w; //reduce the remaining capacity
		}
		
		//2. now consider fraction of an item
		
		//note: only one item needs to be considered, since otherwise the item would have been added completely (whole item)
		if (remCap > 0 && !items.isEmpty()) {
			Node dqNode = items.poll(); //remove the top item from the min peiority queue
			double fracToConsider = remCap/dqNode.w; //fraction of item to consider
			result += dqNode.v * fracToConsider; //add item's value (equivalent of adding item to knapsack)
			//remCap -= dqNode.w * fracToConsider; //reduce the remaining capacity
		}
		
		return result;
	}
	
	public static double getMaxValue(double[] w, double[] v, double c) {
		FractionalKnapsack objFK = new FractionalKnapsack(w, v, c); //create an instance of FractionalKnapsack
		return objFK.getMaxVal(); //get the maximum value of knapsack
	}
	
	public static void main(String[] args) {
		double[] weights = {2, 1, 6, 0.5, 0.25, 7}; //weights
		double[] values = {50, 70, 100, 50, 30, 99}; //values
		int capacity = 10; //capacity
		System.out.printf("Maximum Value: %f", FractionalKnapsack.getMaxValue(weights, values, capacity));
	}
}
