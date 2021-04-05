package conc;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import util.ArrayUtil;

class BlockingQueueTest {
	
	int queueSize = 5;
	int[] queueItems;
	BlockingQueue<Integer> blkQueue;

	@BeforeEach
	void setUp() throws Exception {
		blkQueue = new BlockingQueue<Integer>(queueSize); //create the queue
		queueItems = ArrayUtil.getIntArray(queueSize, 100);
	}

	//fill the queue fresh
	private void enqueueItems () {
		for (int item: queueItems) {
			try {
				blkQueue.enqueue(item);
			} catch (QueueFullException e) {
				e.printStackTrace();
			}
		}
	}
	
	private int[] dequeueEnqueueItems (int[] enqueueitems) {
		//number of items to enqueue/dequeue for boundary test
		int numItems = enqueueitems.length;
		
		//storage for numItems items that are dequeued
		int[] queuedItems = new int[numItems];
		
		//dequeue numItems items
		for (int i = 0; i < numItems; i++) {
			try {
				queuedItems[i] = blkQueue.dequeue();
			} catch (QueueEmptyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//enqueue numItems items
		for (int i = 0; i < numItems; i++) {
			try {
				blkQueue.enqueue(enqueueitems[i]);
			} catch (QueueFullException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return queuedItems;
	}
	
	@Test
	void testEnqueueBoundary() {
		//freshly enqueue items
		enqueueItems ();
		
		//number of items to enqueue/dequeue for boundary test
		int numItems = 4;
		
		//for assert, keep track of first numItems items originally added to the queue 
		int[] originalQueuedItems = new int[numItems];
		for (int i = 0; i < numItems; i++) {
			//https://stackoverflow.com/questions/372250/generics-arrays-and-the-classcastexception
			//the language specification does not support generic array creation. 
			//See this forum post for more - in order to be backwards compatible, 
			//nothing is known about the type of T at runtime.
			Object[] queue =  blkQueue.getQueue();
			originalQueuedItems[i] = (Integer) queue[i];//blkQueue.queue[i];
		}

		
		//numItems items to enqueue
		int[] enqueueitems = ArrayUtil.getIntArray(numItems, 100);
		
		//storage for numItems items that are dequeued
		int[] queuedItems = dequeueEnqueueItems (enqueueitems);
		
		//assert queued items
		//assertArrayEquals(originalQueuedItems, queuedItems);
		
		//assert items enqueued around boundary correctly

		//start past original items not dequeued
		//(blkQueue.size - numItems) is the number of original items not dequeued
		//"numItems" is where 'head' is.
		//so, we begin at 'head' + number of original items not dequeued
		int newItemIndex = numItems + (blkQueue.capacity - numItems);
		Object[] queue =  blkQueue.getQueue();
		for (int i = 0; i < numItems; i++) {
			Object queueVal = queue[newItemIndex];
			assertEquals(enqueueitems[i], (Integer) queueVal);//blkQueue.queue[newItemIndex]);
			//(blkQueue.size + 1) because the storage is one more than size.
			//enqueue/dequeue is limited to size by using the BlockingQueue.numItems variable.
			newItemIndex++; //move to the index of the next new item
			if (newItemIndex == (blkQueue.capacity + 1)) {
				newItemIndex %= (blkQueue.capacity + 1);
			}
		}
	}
	
	@Test
	void testDequeueBoundary() {
		//freshly enqueue items
		enqueueItems ();
		
		//number of items to enqueue/dequeue for boundary test
		int numItems = 4;

		//numItems items to enqueue
		int[] enqueueitems = ArrayUtil.getIntArray(numItems, 100);
		
		//storage for numItems items that are dequeued
		//int[] queuedItems = 
		dequeueEnqueueItems (enqueueitems);
		
		//now we have a queue with 'head' somewhere other than 0
		
		//since we removed and added numItems, the queue is full
		
		//dequeue all items, and check that the last numItems match enqueue items
		int[] queuedItems = new int[blkQueue.capacity]; //storage for dequeued items
		//dequeue all items
		for (int i = 0; i < blkQueue.capacity; i++) {
			try {
				queuedItems[i] = blkQueue.dequeue();
			} catch (QueueEmptyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//check if last numItems are same as enqueued items
		for (int i = blkQueue.capacity - numItems; i < blkQueue.capacity; i++) {
			//[i - (blkQueue.size - numItems)] effectively makes i start from 0,
			// which is the correct index for enqueueitems
			assertEquals(enqueueitems[i - (blkQueue.capacity - numItems)], queuedItems[i]);
		}
	}
	
	@Test
	void testEnqueueException() {
		//freshly enqueue items
		enqueueItems ();
		
		//add one more item so that an exception is thrown
		try {
			blkQueue.enqueue(queueItems[0]);
		} catch (QueueFullException e) {
			assertEquals(blkQueue.excepQueueFull, e.getMessage());
		}
	}

	@Test
	void testDequeueException() {
		//freshly enqueue items
		enqueueItems ();
		
		int[] queuedItems = new int[queueSize];
		
		for (int i = 0; i < queueSize; i++) {
			try {
				queuedItems[i] = blkQueue.dequeue();
			} catch (QueueEmptyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//remove one more item so that an exception is thrown
		try {
			int item = blkQueue.dequeue();
		} catch (QueueEmptyException e) {
			assertEquals(blkQueue.excepQueueEmpty, e.getMessage());
		}
	}
}
