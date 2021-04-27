package algo;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class MaxThief {
	static final char POLICE = 'P';
	static final char THIEF = 'T';
	Queue<Character> qPoliceThief; //queue of police and thief as got from the input
	Stack<Character> stackThief; //stack of thieves who are queued before the police who can catch them
	public MaxThief() {
		qPoliceThief = new ArrayDeque<Character>();
		stackThief = new Stack<Character>();
	}
	void queueInput(char[] arr) { //queue the input
		for (char ch: arr)
			qPoliceThief.add(ch);
	}
	public int catchThieves(char[] arr, int k) { //catch the thieves
		int result = 0; //number of thieves caught
		queueInput(arr); //queue the input
		//iterate while the queue is not empty
		while(!qPoliceThief.isEmpty()) {
			char pt = qPoliceThief.poll(); //dequeue an element
			if (pt == THIEF) { //stack the thief
				stackThief.add(pt);
			} else {
				//catch k thieves
				int i = 0;
				while (i < k && !stackThief.isEmpty()) { //catch thieves from stack
					stackThief.pop(); //catch a thief
					result++; //update the number of thieves caught
					i++; //update the number of thieves caught by this policeman
				}
				while (i < k && !qPoliceThief.isEmpty() && qPoliceThief.peek()==THIEF) { //catch thieves from queue
					qPoliceThief.poll(); //catch a thief
					result++; //update the number of thieves caught
					i++; //update the number of thieves caught by this policeman
				}
			}
		}
		return result; //return the number of thieves caught
	}
	public static int policeThief(char[] arr, int k) { //static wrapper function
		MaxThief objMT = new MaxThief(); //create an object of MaxThief
		return objMT.catchThieves(arr, k); //catch the thieves
	}
	public static void main(String[] args) {
		char[] arr = {'P', 'T', 'T', 'P', 'T'}; //input array of police and thieves
		int k = 1; //number of thieves each policeman can catch
		System.out.printf("Number of thieves caught: %d\n", MaxThief.policeThief(arr, k)); //catch the thieves
	}

}
