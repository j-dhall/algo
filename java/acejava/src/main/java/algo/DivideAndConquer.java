package algo;

public class DivideAndConquer {

	public static int BinarySearch (int[] arr, int left, int right, int key) {
		//if left and right have crossed, we do not have a match
		if (left > right) {
			return -1;
		}
		
		//explore left or right sub-array

		int middle = left + (right-left+1)/2; //get the midpoint
		if (key == arr[middle]) { //if we have a match at the midpoint
			return middle;
		} else if (key <= arr[middle]) { //go down the left half
			return BinarySearch(arr, left, middle - 1, key);
		} else { //go down the right half
			return BinarySearch(arr, middle + 1, right, key);
		}
	}
	
	public static void main(String[] args) {
		 int arr[] = { 3, 5, 7, 7, 7, 15, 25 }; 

		 int key = 7; // to find, feel free to change this
		 int result = BinarySearch(arr, 0, arr.length - 1, key);

		 if (result != -1)
		  System.out.println("Key " + "\"" + key + "\" found at index = " + result);
		 else
		  System.out.println("Key " + "\"" + key + "\"not found!");

		 key = 0; // Trying for a different value
		 result = BinarySearch(arr, 0, arr.length - 1, key);

		 if (result != -1)
		  System.out.println("Key " + "\"" + key + "\" found at index = " + result);
		 else
		  System.out.println("Key " + "\"" + key + "\"not found!");
		 
		 key = 20; // Trying for a different value
		 result = BinarySearch(arr, 0, arr.length - 1, key);

		 if (result != -1)
		  System.out.println("Key " + "\"" + key + "\" found at index = " + result);
		 else
		  System.out.println("Key " + "\"" + key + "\"not found!");
	}
}
