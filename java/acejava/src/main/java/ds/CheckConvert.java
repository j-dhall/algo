package ds;

import java.util.Arrays;

public class CheckConvert {
	
	int lenMinHeap;
	int lenMaxHeap;
	int[] minHeap;

	public CheckConvert(int len) {
		this.lenMinHeap = 0; //empty min heap
		this.lenMaxHeap = len; //store length of max heap
		this.minHeap = new int[len]; //allocate min heap of size max heap
	}

	void insert(int item) {
		//if min heap is already full
		if (lenMinHeap >= lenMaxHeap) {
			throw new IndexOutOfBoundsException("ERROR: insert(): Exceeded heap capacity.");
		}
		
		//assign item to the end of heap
		int idxItem = lenMinHeap;
		minHeap[idxItem] = item;
		lenMinHeap++; //increment the length of min heap
		
		//push up the item in the parents hierarchy to its correct spot
		int idxParentItem = (idxItem - 1) / 2;
		while (minHeap[idxParentItem] > minHeap[idxItem]) { //works for idxItem = 0 too
			int temp = minHeap[idxParentItem];
			minHeap[idxParentItem] = minHeap[idxItem];
			minHeap[idxItem] = temp;
			
			//move the indexes up towards the root
			idxItem = idxParentItem;
			idxParentItem = (idxItem - 1) / 2;
		}
	}
	
	int heapify(int idxNode) {
		//indicate (by returning an invalid index) that no more heapify needed
		//since leaf has been reached
		if (idxNode >= lenMinHeap) {
			return -1;
		}
		
		int idxLeft = 2*idxNode+1; //left child
		int idxRight = 2*idxNode+2; //right child
		
		int idxMin = idxNode; //index of minimum child, invalid index to begin with
		
		//if we have a left child
		if (idxLeft < lenMinHeap) {
			//if left child is smaller
			if (minHeap[idxLeft] < minHeap[idxMin]) {
				idxMin = idxLeft; //mark minimum as left child
			}
		}
		//if we have a right child
		if (idxRight < lenMinHeap) {
			//if right child is smaller
			if (minHeap[idxRight] < minHeap[idxMin]) {
				idxMin = idxRight; //mark minimum as right child
			}
		}
		
		//if we found a lower valued child, swap
		if (idxMin != idxNode) {
			int temp = minHeap[idxNode];
			minHeap[idxNode] = minHeap[idxMin];
			minHeap[idxMin] = temp;
			
			return idxMin; //return index of the swapped child
		} else {
			return -1; //indicate (by returning an invalid index) that no more heapify needed
		} 
	}
	
	int removeMin() {
		//if heap is empty
		if (lenMinHeap == 0) {
			throw new IndexOutOfBoundsException("ERROR: removeMin(): Trying removing item from an empty heap.");
		}
		
		//mark root for return
		int ret = minHeap[0];
		
		//move last item to root, and heapify
		minHeap[0] = minHeap[lenMinHeap-1];
		//heapify
		int heapifyNode = heapify(0);
		while (heapifyNode != -1) {
			heapifyNode = heapify(heapifyNode);
		}
		
		//reduce the heap length
		lenMinHeap--;
		
		return ret;
	}
	
	public static int[] findKSmallest (int[] arr, int k) {
		CheckConvert obj = new CheckConvert(arr.length); //instantiate a min heap
		//populate the min heap
		for (int i: arr) {
			obj.insert(i);
		}
		//extract k minimum elements from the min heap
		int[] ret = new int[k];
		for (int i = 0; i < k; i++) {
			ret[i] = obj.removeMin();
		}
		return ret; //return the extracted k minimum elements
	}
	
	public static void convertMax(int[] maxHeap) {
		CheckConvert obj = new CheckConvert(maxHeap.length);
		
		//reverse add the heap items
		for (int i = 0; i < maxHeap.length; i++) {
			obj.insert(maxHeap[i]);
		}
		
		buildMinHeap(maxHeap, maxHeap.length);

		System.out.printf("maxHeap: %s", Arrays.toString(maxHeap));
		System.out.printf("minHeap: %s", Arrays.toString(obj.minHeap));
	}
	
    //

	  private static void buildMinHeap(int[] heapArray, int heapSize) {
	
	    // swap smallest child to parent node 
	    for (int i = (heapSize - 1) / 2; i >= 0; i--) {
	      minHeapify(heapArray, i, heapSize);
	    }
	  }
	
	  private static void minHeapify(int[] heapArray, int index, int heapSize) {
	    int smallest = index;
	
	    while (smallest < heapSize / 2) { // check parent nodes only
	      int left = (2 * index) + 1; //left child
	      int right = (2 * index) + 2; //right child
	      if (left < heapSize && heapArray[left] < heapArray[index]) {
	        smallest = left;
	      }
	
	      if (right < heapSize && heapArray[right] < heapArray[smallest]) {
	        smallest = right;
	      }
	
	      if (smallest != index) { // swap parent with smallest child
	        int temp = heapArray[index];
	        heapArray[index] = heapArray[smallest];
	        heapArray[smallest] = temp;
	        index = smallest;
	      } else {
	        break; // if heap property is satisfied
	      }
	    } //end of while
	  }
}
