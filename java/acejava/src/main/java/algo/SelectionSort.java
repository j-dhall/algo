package algo;

import java.util.Arrays;

public class SelectionSort {
	public void sort (int[] items) {
		for (int i = 0; i < items.length - 1; i++) { //from first to last but 1
			//System.out.printf("Iter %d: %s\n", i+1, Arrays.toString(items));
			//find the minimum in the items starting from i till end
			//consider item at index i to be the minimum to begin with
			int minIdx = i;
			int minVal = items[i];
			for (int j = i+1; j < items.length; j++) { //from i+1 to last
				//did we find a smaller item?
				if (items[j] < minVal) {
					//mark that item as minimum found so far
					minIdx = j;
					minVal = items[j];
				}
			}
			
			//now, we have item at minIdx the least among the items from i till end
			//swap item at i with the minimum item
			//check if we found an item smaller than the item at i
			if (minVal < items[i]) {
				int temp = items[i];
				items[i] = minVal;
				items[minIdx] = temp;
			}
		}
	}
}
