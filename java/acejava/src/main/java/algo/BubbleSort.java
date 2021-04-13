package algo;

import java.util.Arrays;

public class BubbleSort {
	public void sort (int[] items) {
		
		//bubble through the elements starting at 0, right till the end.
		//then start at 0 again and bubble through till end-1.
		//then start at 0 and bubble through till end-2.
		//keep repeating till we bubble only till second element.
		
		//while bubbling through, swap the adjacent element,
		//moving greater element to the right.
		
		//with every bubbling, an element gets placed at its correct position,
		//starting from the greatest element, then second-greatest, etc
		
		//at the end of bubbling n-1 times, all items will be sorted.
		
		//loop from 1st to second last element
		for (int i = 0; i < items.length - 1; i++) {
			System.out.printf("Iter %d: %s\n", i+1, Arrays.toString(items));
			//loop from 0 till last but i element
			for (int j = 1; j < items.length - i; j++) {
				//if item at (j-1)-th location is greater than the item at j-th location,
				//bubble up the greater element to the j-th location.
				//this is done by swapping the elements in the (j-1)-th and j-th locations.
				if (items[j-1] > items[j]) {
					int temp = items[j-1];
					items[j-1] = items[j];
					items[j] = temp;
				}
			}
		}
	}
}
