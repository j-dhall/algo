package algo;

import java.util.Arrays;

public class PeakElement {
	
	//O(n)
	public static int findPeak (int arr[]) {
		int iPeak = 0; //set peak at first element
		//while we can probe iPeak and iPeak+1 within bounds, and
		//we are monotonically increasing
		while ((iPeak < arr.length-1) && (arr[iPeak] < arr[iPeak+1])) {
			iPeak++; //move peak to the successor bigger element
		}
		return iPeak;
	}
	
	private static int isPeak (int i, int arr[]) {
		//if there is only one element in the array
		if (arr.length == 1) {
			return i;
		}
		
		//if i is the index of the left-most element
		if (i == 0) {
			if (arr[i] >= arr[i+1])
				return i;
			else
				return -1;
		} else if (i == arr.length - 1) { //if i is the index of the right-most element
			if (arr[i] >= arr[i-1])
				return i;
			else
				return -1;
		} else { //if i is the index of a non-boundary element
			if (arr[i] >= arr[i-1] && arr[i] >= arr[i+1])
				return i;
			else 
				return -1;
		}
	}
	
	public static int findPeakDnC (int low, int high, int arr[]) {
		//if left and right have crossed, we haven't found a peak
		if (low > high) {
			return -1;
		}
		
		int middle = low + (high - low + 1) / 2; //get the midpoint
		int middleCandidate = isPeak(middle, arr); //is midpoint a candidate?
		if (middleCandidate != -1) {
			return middleCandidate;
		}
		
		//do we have a monotonic increase from the left? probe right
		if (arr[middle-1] <= arr[middle]) {
			return findPeakDnC (middle+1, high, arr);
		} else {
			return findPeakDnC(low, middle-1, arr);
		}
	}
	
	 // This function is based on the recursive binary search algorithm returning the index of the peak element in the given array
	 public static int findPeakRecursive(int low, int high, int size, int array[]) {
	  // Just as in binary search, we will first find the middle element 
	  
	  int middle = low + (high - low) / 2;

	  // If neighbors of the middle element exist, compare them with the element 
	  if ((middle == size - 1 || array[middle + 1] <= array[middle]) &&
	   (middle == 0 || array[middle - 1] <= array[middle]))
	   return middle;

	  // If the left neighbor of the middle element is greater, The peak element must be in the left subarray    
	  else if (array[middle - 1] > array[middle] && middle > 0)
	   return findPeakRecursive(low, (middle - 1), size, array);

	  // If the right neighbor of the middle element is greater, The peak element must be in the right subarray    
	  else
	   return findPeakRecursive((middle + 1), high, size, array);
	 }

	public static void main(String[] args) {
		int[][] arrs = {{1,2,3,4,5}, {5,4,3,2,1}, {5,4,5,6,7}, {3,3,3,3,3}, {1,2,3,2,3}};
		for (int[] arr: arrs) {
			int iPeak = findPeakDnC(0, arr.length-1, arr);
			System.out.printf("%s has peak element %d at index %d.\n", Arrays.toString(arr), arr[iPeak], iPeak);
		}
	}
}
