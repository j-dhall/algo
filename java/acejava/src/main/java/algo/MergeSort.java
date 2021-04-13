package algo;

public class MergeSort {
	public int[] sort (int[] items) {
		return sortInner (items, 0, items.length - 1);
	}
	
	private int[] sortInner (int[] items, int begin, int end) {
		
		//a single item is sorted
		if (begin == end) {
			int[] sortedItem = new int[1];
			sortedItem[0] = items[begin];
			return sortedItem;
		} else {
			//we have more than one item,
			//so, sort recursively and merge the results
			
			//recursively break the list into smaller parts
			//and sort the smaller sublists 
			int idxMiddle = begin + (end - begin)/2;
			int[] leftItems = sortInner (items, begin, idxMiddle);
			int[] rightItems = sortInner (items, idxMiddle+1, end);

			//merge the smaller left and right sublists
			
			//sorted list to return
			int[] sortedItems = new int[leftItems.length + rightItems.length];
			int headLeft = 0; //head of the left sorted sublist
			int headRight = 0; //head of the right sorted sublist
			int iSorted = 0; //index into the sorted items
			//while we have not surpassed merging either of the lists
			while (headLeft < leftItems.length && headRight < rightItems.length) {
				//move head item from the left sublist to the sorted items that is to be returned
				if (leftItems[headLeft] <= rightItems[headRight]) {
					sortedItems[iSorted] = leftItems[headLeft];
					headLeft++; //advance the left sublist head
				} else {
					sortedItems[iSorted] = rightItems[headRight];
					headRight++; //advance the right sublist head
				}
				iSorted++; //increment the sorted items index
			}
			
			//only one of the below two blocks will execute
			//move the remaining left sublist items if any
			while (headLeft < leftItems.length) {
				sortedItems[iSorted] = leftItems[headLeft];
				headLeft++;
				iSorted++;
			}
			//move the remaining right sublist items if any
			while (headRight < rightItems.length) {
				sortedItems[iSorted] = rightItems[headRight];
				headRight++;
				iSorted++;
			}
			
			//return the sorted list of items
			return sortedItems;
		}		
	}
}
