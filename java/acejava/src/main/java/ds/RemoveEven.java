package ds;

public class RemoveEven {
	public int[] removeEven(int[] arr) {
		//traverse the array, counting the number of even numbers encountered,
		//and moving the odd numbers that much in front
		int nEven = 0; //count even numbers
		for (int i = 0; i < arr.length; i++) {
			//is the number even
			if (arr[i] % 2 == 0) {
				//increment the even count
				nEven++;
			} else {
				//move the encountered odd number left by even count
				arr[i-nEven] = arr[i];
			}
		}
		
		//now we have first (arr.length - nEven) numbers as odd
		//we can move them to another array of size (arr.length - nEven)
		int nOdd = arr.length - nEven;
		int[] arrOdd = new int[nOdd];
		for (int i = 0; i < nOdd; i++) {
			arrOdd[i] = arr[i];
		}
		
		//return the odd array
		return arrOdd;
	}
}
