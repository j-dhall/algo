package util;

import java.util.Random;
import java.util.stream.IntStream;

public class ArrayUtil {
	/*
	 * create an array of random integers of length 'len' and 
	 * values bounded by 'bound'
	 */
	public static int[] getIntArray (int len, int bound) {
		int[] arr = new int[len]; //create an array
		Random random = new Random (); //to generate random numbers
		//iterate over 0..len and assign 'bound'-ed random numbers
		IntStream.range(0, len)
		.forEach(i -> arr[i] = random.nextInt(bound));

		return arr;
	}
	
	/*
	 * check if the array is sorted
	 */
	public static boolean isArraySorted (int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] > arr[i+1]) {
				return false;
			}
		}
		
		return true;
	}
	
	/*
	 * shuffle the elements of the array
	 */
	public static void shuffleArray (int[] arr) {
		
		//used to randomly generate an index in the range of array indices
		Random random = new Random ();
		
		for (int i = 0; i < arr.length; i++) {
			//randomly generate an index in the range of array indices
			int shuffleIndex = random.nextInt(arr.length);
			
			//swap the element at index i with the element at index shuffleIndex
			int temp = arr[i];
			arr[i] = arr[shuffleIndex];
			arr[shuffleIndex] = temp;
		}
	}
}
