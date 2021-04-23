package conc;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ReadWrite {
	
	private String[] prefixes = {"Butterfly", "Snail", "Sparrow", "Elephant", "Pig", "Frog", "Cat", "Human", "Bee", "Spider"};
	
	private String val; //value to be read and written
	
	public synchronized void write (int val) {
		//String prefix = "This is line ";
		this.val = prefixes[val % 10]; //prefix + Integer.toString(val);
		//System.out.printf("Writer thread %s wrote VALUE: %s\n", Thread.currentThread().getName(), val);
	}
	
	public void read () {
		System.out.printf("Reader thread %s read value: %s\n", Thread.currentThread().getName(), this.val);
	}

	public static void main(String[] args) {
		int valBound = 100; //range of values 0..99
		int[] baseSleep = {10, 10}; //minimum ms sleep (at index 0) between thread writes (thread reads at index 1) from a single thread
		int[] sleepBound = {10, 100}; //range of writer (at index 0, reader range at index 1) thread sleep time in ms: base + 0..999 ms
		int nWriters = 3; //number of writers
		int nReaders = 3; //number of readers
		Set<Thread> tWriters = new HashSet<Thread> (); //set of writer threads
		Set<Thread> tReaders = new HashSet<Thread> (); //set of reader threads
		ReadWrite objRW = new ReadWrite();
		
		for (int i = 0; i < nWriters; i++) { //create writer threads
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					while (true) {
						Random rand = new Random(); //to get random values for writing, and for thread sleep
						objRW.write(rand.nextInt(valBound)); //write a random value
						//writer thread sleeps between writes
						try {
							Thread.sleep(baseSleep[0] + rand.nextInt(sleepBound[0])); //index 0 values are for writes
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});
			t.setName(Integer.toString(i)); //name the thread
			tWriters.add(t); //add thread to the set
		}
		
		for (int i = 0; i < nReaders; i++) { //create reader threads
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					while (true) {
						Random rand = new Random(); //to get random values for thread sleep
						objRW.read(); //read the value
						//reader thread sleeps between reads
						try {
							Thread.sleep(baseSleep[1] + rand.nextInt(sleepBound[1])); //index 1 values are for reads
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});
			t.setName(Integer.toString(i)); //name the thread
			tReaders.add(t); //add thread to the set
		}
		
		//start the threads
		for (Thread t: tWriters)
			t.start();
		for (Thread t: tReaders)
			t.start();
		
		//wait for threads to complete
		for (Thread t: tWriters)
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		for (Thread t: tReaders)
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

}
