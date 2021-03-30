package bigo;

import java.util.Arrays;

/*
 * Sort an array in-place using insertion sort.
 */
public class InsertionSort {
	int[] sort_unoptimized (int[] input) {
		
		//Big-O
		int steps = 0;
		
		//sorted array to return
		int[] arr = input.clone();
		
		//iterate starting from second element, till last element
		for (int i = 1; i < arr.length; i++) {
			//Big-O
			steps++;
			
			int key = arr[i];
			
			//keep moving the key leftwards till 
			
			//Iterate j leftwards starting from element before i.
			//As long as the element at j is bigger than the key,
			// move the element at j one position right, and,
			// place the key at j.
			//When the element at j is not bigger than the key,
			// all elements to the left of the key
			// (which was placed at now j+1 position in the previous step)
			// are smaller than the key.
			//This is the loop invariant.
			for (int j = i-1; j >= 0; j--) {
				//Big-O
				steps++;
				
				
				if (arr[j] > key) {
					int tmp = arr[j];
					arr[j] = key;
					arr[j+1] = tmp;//moving the element at j one position right
				}
			}
			//System.out.println("Intermediate: " + Arrays.toString(arr)); //print intermediate array
		}
		
		//System.out.println("Number of steps: " + String.valueOf(steps));
		
		return arr;
	}
}
