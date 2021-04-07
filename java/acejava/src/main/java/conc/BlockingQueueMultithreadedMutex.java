package conc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import util.ArrayUtil;

public class BlockingQueueMultithreadedMutex<T> {
	
	//mutex
	private final Lock lock = new ReentrantLock(); 
	
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
	public BlockingQueueMultithreadedMutex(int capacity) {
		this.capacity = capacity;
		queue = (T[]) new Object[capacity + 1]; //tail is one past the last item
		head = 0;
		tail = 0; //to begin with, when there are no items, tail = head
		size = 0;
		
		//exception handling message text
		excepQueueFull = new String ("Error: Queue is full.");
		excepQueueEmpty = new String ("Error: Queue is empty.");
	}
	
	//not synchronized. using lock instead
	public void enqueue (T item) throws InterruptedException {

		lock.lock(); //acquire a lock
		//wait if queue is full
		while (size == capacity) {
			//wait();
			//unlock and relock, trying to have another thread enqueue
			lock.unlock();
			lock.lock();
		}
		
		queue[tail] = item; //enqueue the item at the tail
		tail++; //move the tail further to accommodate the item
		//check if tail crossed storage boundary
		if (tail > capacity) {
			tail = 0; //round back the tail to the beginning of the storage
		}
		
		size ++; //update the size
		//notify other waiting threads of the change in size
		//notifyAll(); //now using lock
		
		//release the lock
		lock.unlock();
	}
	
	//not synchronized. using lock instead
	public T dequeue () throws InterruptedException{
		
		lock.lock(); //acquire a lock
		//wait if queue is empty
		while (size == 0) {
			//wait();
			//unlock and relock, trying to have another thread enqueue
			lock.unlock();
			lock.lock();
		}
		
		T item = queue[head]; //enqueue the item
		head++; //move the head further to release the item
		//check if head crossed storage boundary
		if (head > capacity) {
			head = 0; //round back the tail to the beginning of the storage
		}

		size --; //update the size
		//notify other waiting threads of the change in size
		//notifyAll(); //now using lock
		//release the lock
		lock.unlock();

		return item;
	}
	
	/*
	 * older main method from BlockingQueueMulththreaded.java 
	 
	
	//main method to spawn threads
	public static void main (String args[]) throws Exception {
		int capacity = 5; //capacity of the queue
		int numItems = 100; //number of items to enqueue. provide an even number so that the two dequeue threads divide it equally
		BlockingQueueMultithreadedMutex<Integer> queue = new BlockingQueueMultithreadedMutex<Integer>(capacity); //queue
		int[] items = ArrayUtil.getIntArray(numItems, 100); //get an array of 100 integers in the range 0..99
		
		//thread 1: enqueue items
		Thread tEnqueue = new Thread (new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < numItems; i++) {
					try {
						queue.enqueue(items[i]);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		//thread 2: dequeue half of the items
		Thread tDequeue1 = new Thread (new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int j = 0; j < numItems/2; j++) {
					int item = -1; //initialize with sentinel
					try {
						item = queue.dequeue();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.printf("Dequeued %d from Dequeue Thread 1.\n", item);
				}
			}
		});
		
		//thread 3: dequeue another half of the items
		Thread tDequeue2 = new Thread (new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int j = 0; j < numItems/2; j++) {
					int item = -1; //initialize with sentinel
					try {
						item = queue.dequeue();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.printf("Dequeued %d from Dequeue Thread 2.\n", item);
				}
			}
		});
		
		//spawn the threads
		tEnqueue.start();
		Thread.sleep(1000);
		tDequeue1.start();
		Thread.sleep(1000);
		tDequeue2.start();
		Thread.sleep(1000);
		
		//wait for threads to complete
		tEnqueue.join();
		tDequeue1.join();
		tDequeue2.join();
		
		//dummy for debug breakpoint
		System.out.println("Finished!");
	}

	*/
	
	/*
	 * More rigorous main method
	 */
    public static void main( String args[] ) throws InterruptedException {
        final BlockingQueueMultithreadedMutex<Integer> q = new BlockingQueueMultithreadedMutex<Integer>(5);

        Thread producer1 = new Thread(new Runnable() {
            public void run() {
                try {
                    int i = 1;
                    while (true) { //daemon threads will terminate
                        q.enqueue(i);
                        System.out.println("Producer thread 1 enqueued " + i);
                        i++;
                    }
                } catch (InterruptedException ie) {
                }
            }
        });

        Thread producer2 = new Thread(new Runnable() {
            public void run() {
                try {
                    int i = 5000;
                    while (true) { //daemon threads will terminate
                        q.enqueue(i);
                        System.out.println("Producer thread 2 enqueued " + i);
                        i++;
                    }
                } catch (InterruptedException ie) {

                }
            }
        });

        Thread producer3 = new Thread(new Runnable() {
            public void run() {
                try {
                    int i = 100000;
                    while (true) { //daemon threads will terminate
                        q.enqueue(i);
                        System.out.println("Producer thread 3 enqueued " + i);
                        i++;
                    }
                } catch (InterruptedException ie) {

                }
            }
        });

        Thread consumer1 = new Thread(new Runnable() {
            public void run() {
                try {
                    while (true) { //daemon threads will terminate
                        System.out.println("Consumer thread 1 dequeued " + q.dequeue());
                    }
                } catch (InterruptedException ie) {

                }
            }
        });

        Thread consumer2 = new Thread(new Runnable() {
            public void run() {
                try {
                    while (true) { //daemon threads will terminate
                        System.out.println("Consumer thread 2 dequeued " + q.dequeue());
                    }
                } catch (InterruptedException ie) {

                }
            }
        });

        Thread consumer3 = new Thread(new Runnable() {
            public void run() {
                try {
                    while (true) { //daemon threads will terminate
                        System.out.println("Consumer thread 3 dequeued " + q.dequeue());
                    }
                } catch (InterruptedException ie) {

                }
            }
        });

        //daemon threads will terminate as soon as this 'main' non-daemon thread terminates.
        producer1.setDaemon(true);
        producer2.setDaemon(true);
        producer3.setDaemon(true);
        consumer1.setDaemon(true);
        consumer2.setDaemon(true);
        consumer3.setDaemon(true);

        producer1.start();
        producer2.start();
        producer3.start();

        consumer1.start();
        consumer2.start();
        consumer3.start();

        Thread.sleep(2);       
    }
	
}
