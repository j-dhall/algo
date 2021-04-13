package algo;

import java.util.Arrays;

public class InsertionSort {
	public void sort (int[] items) {
		//starting from 2nd element till last element
		//for each element, keep swapping it with its left element
		//as long as the left element is greater.
		for (int i = 1; i < items.length; i++) {
			System.out.printf("Iter %d: %s\n", i, Arrays.toString(items));
			for (int j = i; j > 0; j--) {
				if (items[j-1] > items[j]) {
					int temp = items[j-1];
					items[j-1] = items[j]; //move left
					items[j] = temp;
				}
			}
		}
	}
}
