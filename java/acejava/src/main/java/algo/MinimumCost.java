package algo;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

import util.ArrayUtil;

public class MinimumCost {
	
	public static int minCost(int[] pipes) {
		int cost = 0; //total cost of joining the pipes so far
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>(); //form a priority queue of pipes
		for (int pipe: pipes) {
			queue.add(pipe);
		}
		
		while (queue.size() > 1) {  //while the queue has more than one pipe left
			//join top 2 smallest pipes, add to the cost
			int minPipe = queue.poll();
			int secondMinPipe = queue.poll();
			int joinedPipe = minPipe + secondMinPipe; 
			cost += joinedPipe;
			
			queue.add(joinedPipe); //put the joined pipe back into the queue	
		}
		
		return cost; //return the cost
	}

	public static void main(String[] args) {
		//test configuration
		Random rand = new Random(); //random number of pipes, random sizes
		int maxPipes = 5; //maximum number of pipes in a test
		int maxLength = 10; //maximum length of a pipe
		int nTests = 5; //how many pipe combinations to test?
		
		MinimumCost objMC = new MinimumCost(); //create an object of MinimumCost
		for (int i = 0; i < nTests; i++) { 
			//maximum maxPipes of maximum length maxLength
			int[] pipes = ArrayUtil.getIntArray(1 + rand.nextInt(maxPipes), 1 + maxLength);
			System.out.printf("Minimum cost of joining pipes %s is %d.\n", Arrays.toString(pipes), objMC.minCost(pipes));
		}
	}

}
