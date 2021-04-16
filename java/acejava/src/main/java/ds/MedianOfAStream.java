package ds;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianOfAStream {

	PriorityQueue<Integer> maxHeap; //max heap
	PriorityQueue<Integer> minHeap; //min heap
	
	public MedianOfAStream() {
		maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder()); //reverse order for max heap
		minHeap = new PriorityQueue<Integer>();
	}
	
	public void insertNum(int i) {
		//if max heap is empty, insert i in max heap
		if (maxHeap.isEmpty()) {
			maxHeap.add(i);
			return;
		}
		
		//if i is less than top max heap, insert in max heap
		//else insert in min heap
		if (i <= maxHeap.peek()) {
			maxHeap.add(i);
		} else {
			minHeap.add(i);
		}
		
		//if max heap has 2 more elements than min heap, move top max heap to min heap
		//else if min heap is 1 more than max heap, move top min to max heap
		if (maxHeap.size() == minHeap.size() + 2) {
			minHeap.add(maxHeap.poll());
		} else if (minHeap.size() == maxHeap.size() + 1) {
			maxHeap.add(minHeap.poll());
		}
	}
	
	public double findMedian() {
		//if number of elements in max and min heaps are equal,
		//return the average of top max and top min
		//else, return the top max (since it has one extra element, and hence, the top max is the median)
		if (maxHeap.size() == minHeap.size()) {
			return ((double)(maxHeap.peek() + minHeap.peek())) / 2;
		} else {
			return (double)(maxHeap.peek());
		}
	}
	
	public static void main(String[] args) {
	    MedianOfAStream medianOfAStream = new MedianOfAStream();
	    medianOfAStream.insertNum(3);
	    medianOfAStream.insertNum(1);
	    System.out.println("The median is: " + medianOfAStream.findMedian());
	    medianOfAStream.insertNum(5);
	    System.out.println("The median is: " + medianOfAStream.findMedian());
	    medianOfAStream.insertNum(4);
	    System.out.println("The median is: " + medianOfAStream.findMedian());
	}

}
