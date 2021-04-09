package util;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

import java.lang.System;

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
	
	public static int[] getIntArraySorted (int len, int bound) {
		int[] arr = getIntArray (len, bound); //get the array
		Arrays.sort(arr); //sort the array
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
	
	public static int[] concatArrays (int[] arr1, int[] arr2) {
		
		//check if input arrays have values
		if (arr1 == null) {
			if (arr2 == null) {
				return null;
			} else {
				return arr2.clone();
			}
		}
		if (arr2 == null) {
			//if we are here, arr1 cannot be null
			return arr1.clone();
		}
		
		int len = arr1.length + arr2.length;
		int[] concatArr = new int[len];
		System.arraycopy(arr1, 0, concatArr, 0, arr1.length);
		System.arraycopy(arr2, 0, concatArr, arr1.length, arr2.length);
		
		return concatArr;
	}
}
