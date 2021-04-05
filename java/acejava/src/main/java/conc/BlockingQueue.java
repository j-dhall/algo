package conc;

public class BlockingQueue<T> {
	
	int capacity;
	T[] queue;
	public T[] getQueue() {
		return queue;
	}

	int head, tail;
	int size;
	
	//exception handling message text
	//public to be used by test cases
	//public 
	String excepQueueFull, excepQueueEmpty;
	
	@SuppressWarnings("unchecked")
	public BlockingQueue(int capacity) {
		this.capacity = capacity;
		queue = (T[]) new Object[capacity + 1]; //tail is one past the last item
		head = 0;
		tail = 0; //to begin with, when there are no items, tail = head
		size = 0;
		
		//exception handling message text
		excepQueueFull = new String ("Error: Queue is full.");
		excepQueueEmpty = new String ("Error: Queue is empty.");
	}
	
	public void enqueue (T item) throws QueueFullException {

		//check if queue is full
		if (size == capacity) {
			throw new QueueFullException(excepQueueFull);
		}
		
		queue[tail] = item; //enqueue the item at the tail
		tail++; //move the tail further to accommodate the item
		//check if tail crossed storage boundary
		if (tail > capacity) {
			tail = 0; //round back the tail to the beginning of the storage
		}
		
		size ++;
	}
	
	public T dequeue () throws QueueEmptyException{
		
		//check if queue is empty
		if (size == 0) {
			throw new QueueEmptyException(excepQueueEmpty);
		}
		
		T item = queue[head]; //enqueue the item
		head++; //move the head further to release the item
		//check if head crossed storage boundary
		if (head > capacity) {
			head = 0; //round back the tail to the beginning of the storage
		}

		size --;
		return item;
	}
}
