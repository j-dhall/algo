package ds;

public class AddUpToN {
	int[] findSum (int[] arr, int n ) {
		int[] result = new int[2];
		//for every number in the array except last
		//check if any subsequent number adds with this number to become n.
		for (int i = 0; i < arr.length - 1; i++) {
			//inner loop starts at i+1 because for this i,
			//the numbers prior to i have already been checked 
			//when i was pointing to their location. 
			for (int j = i+1; j < arr.length; j++) {
				if (arr[i] + arr[j] == n) {
					//we found two numbers that add up to n
					result[0] = arr[i];
					result[1] = arr[j];
					return result; //return the numbers
				}
			}
		}
		
		//we did not find any combination, so return the whole array
		return arr;
	}
	
	int[] findSumEfficient (int[] arr, int n) {
		int low = 0;
		int high = arr.length - 1;
		
		while (low < high) {
			int sum = arr[low] + arr[high];
			if (sum < n) {
				low++;
			} else if (sum > n) {
				high--;
			} else {
				int[] result = new int[2];
				result[0] = arr[low];
				result[1] = arr[high];
				return result;
			}
		}
		
		return arr;
	}
}
