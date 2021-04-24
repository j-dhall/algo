package conc;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class Barrier {
	
	private synchronized void pleaseWait () {
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private synchronized void pleaseNotifyAll () {
		notifyAll();
	}
	
	private static class MyThread implements Runnable {
		
		//private static Semaphore threadStart = new Semaphore(10);
		private static Semaphore threadStop = new Semaphore(10);

		//private static final int totalThreads = 10; //total number of threads to reach the barrier
		//private static int numThreadsCompleted = 0; //number of threads reached the barrier
		
		Barrier barrier;
		int workload; //how much time to sleep
		
		public MyThread(Barrier barrier, int workload) {
		//public MyThread(int workload) {
			this.barrier = barrier;
			this.workload = workload;
		}
		
		@Override
		public void run() {
			try {
				//threadStart.acquire(); //acquire a semaphore before starting to work
				System.out.printf("Thread %s started.\n", Thread.currentThread().getName());
				Thread.sleep(workload);
				threadStop.acquire(); //release the semaphore after completing the work
				//check if we are the last thread (all but one semaphore available)
				if (threadStop.availablePermits() == 0) { //yes, we are the last thread
					//threadStart.release(10);
					threadStop.release(10);
					System.out.printf("Last Thread %s notifying other waiting threads.\n", Thread.currentThread().getName());
					barrier.pleaseNotifyAll(); //notify all waiting threads to complete
					System.out.printf("Last Thread %s complete.\n", Thread.currentThread().getName());
				} else { //no, there are other threads running
					System.out.printf("Thread %s waiting.\n", Thread.currentThread().getName());
					barrier.pleaseWait(); //wait on the barrier sync object
					System.out.printf("Thread %s complete.\n", Thread.currentThread().getName());
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Barrier barrier = new Barrier(); //create an object for synchronization
		Random rand = new Random(); //random sleep time for threads
		Set<Thread> threads = new HashSet<Thread> (); //set of threads
		int totalThreads = 10;
		int baseSleepTime = 1000; //minimum sleep time for threads (in milliseconds)
		int boundSleepTime = 1000; //additional bounded sleep time
		for (int i = 0; i < totalThreads; i++) { //create threads
			Thread t = new Thread(new MyThread(barrier, baseSleepTime + rand.nextInt(boundSleepTime)));
			t.setName(Integer.toString(i));
			threads.add(t);
		}
		for (Thread t: threads) { //start threads
			t.start();
			//UPDATE: The program now works, see Semaphore threadStop.acquire().
			//*** F A U L T Y ***
			Thread.sleep(2000); //uncomment this to see ***FAULTY*** implementation of barrier
		}
		for (Thread t: threads) { //join threads to main
			t.join();
		}
		Thread.sleep(3000);
		System.out.println("Program ends.");
	}

}
