package ds;

public class MergeSortedArrays {
	int[] mergeArrays(int[] arr1, int[] arr2) {
		int combinedLength = arr1.length + arr2.length; //length of merged array
		int[] arrMerged = new int[combinedLength]; //merged array
		
		int headArr1 = 0;
		int headArr2 = 0;

		//while we still have elements in both arrays to be merged
		while (headArr1 < arr1.length && headArr2 < arr2.length) {
			//if element at head of arr1 is smaller than arr2
			if (arr1[headArr1] <= arr2[headArr2]) {
				//move the head element of arr1 to the merged array
				arrMerged[headArr1+headArr2] = arr1[headArr1];
				//progress the head of arr1
				headArr1++;
			} else {
				arrMerged[headArr1+headArr2] = arr2[headArr2];
				headArr2++;
			}
		}
		
		//we may have one of the arrays yet to move 
		//all its elements to the merged array
		if (headArr1 < arr1.length) {
			for (; headArr1 < arr1.length; headArr1++) {
				arrMerged[headArr1+headArr2] = arr1[headArr1];
			}
		} else {
			for (; headArr2 < arr2.length; headArr2++) {
				arrMerged[headArr1+headArr2] = arr2[headArr2];
			}
		}
		
		//return the merged array
		return arrMerged;
	}
}
