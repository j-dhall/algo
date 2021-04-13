package algo;

import java.util.Arrays;

import util.ArrayUtil;

public class QuickSort {
	int[] sort (int[] arr) {
		return sortInner (arr, 0, arr.length - 1);
	}
	
	void reverse (int[] arr, int begin, int end) {
		int len = end - begin + 1;
		for (int i = begin; i < begin + len/2; i++) {
			int temp = arr[i];
			arr[i] = arr[begin+(len-1)-(i-begin)];
			arr[begin+(len-1)-(i-begin)] = temp;
		}
	}
	
	int[] sortInner (int[] arr, int begin, int end) {
		
		System.out.printf("%s\n",  Arrays.toString(arr));
		
		//1. leaf conditions to return single element (implicitly sorted) array or empty 
		
		//if end < begin, we have an empty array
		if (end < begin) {
			return null;
		} else if (end == begin) { //if end == begin, we have a single element array
			int[] arrSorted = new int[1];
			arrSorted[0] = arr[begin];
			return arrSorted;
		} else { //recursively break into subarrays, sort, and merge
			
		
			//2. Non-leaf nodes
			
			//2.1 traverse down, partitioning based on pivot 
			
			int pivot = arr[end];
			//create an array to store the partitioned elements and the pivot
			int[] arrPartitioned = new int[end - begin + 1]; //this array size will decrease for deeper levels
			//length of the array
			int len = end - begin + 1;
			//initialize indexes of the partitions
			int iSmaller = -1; //begin - 1;
			//since we do not know how many elements are smaller or greater than the pivot,
			//reverse fill the greater elements
			int iGreater = len; //end + 1;

			//iterate over all but the last element (pivot) 
			for (int i = 0; i < len - 1; i++) {
				try {
					if (arr[begin + i] <= pivot) {
						++iSmaller;
						arrPartitioned[iSmaller] = arr[begin + i];
					} else {
						--iGreater;
						arrPartitioned[iGreater] = arr[begin + i];
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println(e.getMessage());
				}
				System.out.printf("arr: %s; begin: %d; end: %d; Pivot: %d; iSmaller: %d; iGreater: %d; arrPartitioned: %s\n",  Arrays.toString(arr), begin, end, pivot, iSmaller, iGreater, Arrays.toString(arrPartitioned));
			}
			arrPartitioned[iSmaller+1] = pivot; //place the pivot
			
			//since we reverse filled the greater partition, reverse that partition 
			if (iGreater != len) {
				reverse(arrPartitioned, iGreater, len - 1);
			}
			
			//get the recursively sorted subarrays
			int[] arrLeft = sortInner(arrPartitioned, 0, iSmaller); //begin, iSmaller
			int[] arrRight = sortInner(arrPartitioned, iGreater, len - 1); //end);
			
			//2.2 merge the partitions while retracting back to higher levels
			
			//combine the subarrays with the pivot
			int[] arrPivot = new int[1];
			arrPivot[0] = pivot;
			int[] leftAndPivot = ArrayUtil.concatArrays(arrLeft, arrPivot);
			int[] complete = ArrayUtil.concatArrays(leftAndPivot, arrRight);
			
			//return the sorted subarray
			return complete;
		}
	}
}
