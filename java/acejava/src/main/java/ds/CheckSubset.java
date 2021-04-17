package ds;

import java.util.HashSet;
import java.util.Set;

public class CheckSubset {
	
	public static boolean isSubset(int[] arr1, int[] arr2) {
		//add all arr1 elements to a hash set
		//since there is no key-value pair, but only keys,
		//we use a hash set instead of a hash map
		Set<Integer> arr1Set = new HashSet<Integer> ();
		for (int i: arr1) {
			arr1Set.add(i);
		}
		
		//check if all elements of arr2 are in the arr1 hash set
		for (int i: arr2) {
			if (!arr1Set.contains(i)) {
				//there is some element of arr2 not in arr1
				return false; 
			}
		}
		
		//all arr2 elements are present in arr1
		return true;
	}
	
	public static boolean isDisjoint(int[] arr1, int[] arr2) {
		//add all arr1 elements to a hash set
		//since there is no key-value pair, but only keys,
		//we use a hash set instead of a hash map
		Set<Integer> arr1Set = new HashSet<Integer> ();
		for (int i: arr1) {
			arr1Set.add(i);
		}
		
		//check if all elements of arr2 are in the arr1 hash set
		for (int i: arr2) {
			if (arr1Set.contains(i)) {
				//there is some element of arr2 that is in arr1
				return false; 
			}
		}
		
		//none of the arr2 elements are present in arr1
		return true;
	}

	public static void main(String[] args) {
		
		//test isSubset()
		int[] arr1 = {9,4,7,1,-2,6,5};
		int[] arr2 = {7,1,-2};
		int[] arr3 = {1,2,3,11,15};
		int[] arr4 = {1,11};
		int[] arr5 = {1,3,5,7,9,17};
		int[] arr6 = {3,6,9};
		
		boolean bSubset = isSubset(arr1, arr2);
		bSubset = isSubset(arr3, arr4);
		bSubset = isSubset(arr5, arr6);
		
		//test isDisjoint()
		int[] arr7 = {9,4,3,1,-2,6,5};
		int[] arr8 = {7,10,8};
		
		boolean bDisjoint = isDisjoint(arr7, arr8);
		System.out.println();
	}

}
