package conc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class UnisexBathroom {
	
	int capacity = 3; //capacity of the bathroom
	int nMen = 0; //current number of men in the bathroom
	int nWomen = 0; //current number of women in the bathroom
	boolean bMenLock = false; //lock acquired means at least 1 man in the bathroom
	boolean bWomenLock = false; //lock acquired means at least 1 woman in the bathroom
	
	public UnisexBathroom(int capacity) {
		this.capacity = capacity; //set the capacity of the bathroom in the constructor
	}
	
	public synchronized void acquireMenLock() throws InterruptedException { //a man wants to use the bathroom
		//wait for women lock to be released
		while (bWomenLock) {
			System.out.println("Man cannot enter.");
			wait();
		}
		
		//wait if the bathroom is being used at full capacity
		while (nMen == capacity)
			wait();
		
		nMen++; //use the bathroom
		System.out.printf("Man entered. Men: %d. Women: %d.\n", nMen, nWomen);
		
		//set men lock to true (even if already set
		bMenLock = true;
	}
	
	public synchronized void releaseMenLock() { //a man leaves the bathroom
		nMen--; //leave the bathroom
		System.out.println("Man left.");
		//if the bathroom becomes empty, release the men lock
		if (nMen == 0)
			bMenLock = false;
		notifyAll(); //notify other waiting men (nMen) and women (bMenLock)
	}

	public synchronized void acquireWomenLock() throws InterruptedException { //a woman wants to use the bathroom
		//wait for men lock to be released
		while (bMenLock) {
			System.out.println("Woman cannot enter.");
			wait();
		}
		
		//wait if the bathroom is being used at full capacity
		while (nWomen == capacity)
			wait();
		
		nWomen++; //use the bathroom
		System.out.printf("Woman entered. Men: %d. Women: %d.\n", nMen, nWomen);
		
		//set men lock to true (even if already set
		bWomenLock = true;
	}
	
	public synchronized void releaseWomenLock() { //a woman leaves the bathroom
		nWomen--; //leave the bathroom
		System.out.println("Woman left.");
		//if the bathroom becomes empty, release the women lock
		if (nWomen == 0)
			bWomenLock = false;
		notifyAll(); //notify other waiting men (nMen) and women (bMenLock)
	}
	
//	static class Person {
//		public boolean isMan;
//		public int name;
//		public Person(boolean isMan, int name) {
//			this.isMan = isMan;
//			this.name = name;
//		}
//	}
	
	static class EnterLeave implements Runnable {
		UnisexBathroom bathroom;
		boolean isMan;
		public EnterLeave(UnisexBathroom bathroom, boolean isMan) {
			this.bathroom = bathroom;
			this.isMan = isMan;
		}
		
		@Override
		public void run() {
			Random rand = new Random();
			int count = 0; //make sure people leaving the bathroom are not more than they entered
			//men coming to using bathroom continuously at random intervals
			while (true) {
				int enterLeave = rand.nextInt(2); //0=enter, 1=leave
				if (enterLeave == 0) { //enter the bathroom
					if (count < bathroom.capacity) {
						try {
							if (isMan)
								bathroom.acquireMenLock();
							else
								bathroom.acquireWomenLock();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						count++;
					}
				} else {
					//leave the bathroom
					if (count > 0) {
						if (isMan)
							bathroom.releaseMenLock();
						else
							bathroom.releaseWomenLock();
						count--;
					}
				}
				try {
					Thread.sleep(1000 + rand.nextInt(1000));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} //sleep for a random amount of time
			}
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		UnisexBathroom bathroom = new UnisexBathroom(3);
		//men and women gathering in front of the bathroom at random intervals
		Random rand = new Random();
		while (true) {
			int isMan = rand.nextInt(2);
			if (isMan == 1) { //man thread
				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							bathroom.acquireMenLock(); //wait to enter the bathroom
							Thread.sleep(rand.nextInt(1000)); //sleep reflecting bathroom usage
							bathroom.releaseMenLock(); //leave the bathroom
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				});
				t.start();
			} else { //woman thread
				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							bathroom.acquireWomenLock(); //wait to enter the bathroom
							Thread.sleep(rand.nextInt(1000)); //sleep reflecting bathroom usage
							bathroom.releaseWomenLock(); //leave the bathroom
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				});
				t.start();
			}
			Thread.sleep(rand.nextInt(1000)); //sleep between arrivals
		}
		
//		Thread tMen = new Thread(new EnterLeave(bathroom, true)); //men entering and leaving the bathroom
//		Thread tWomen = new Thread(new EnterLeave(bathroom, false)); //women entering and leaving the bathroom
//		tMen.start();
//		tWomen.start();
//		tMen.join();
//		tWomen.join();
//		List<Person> people = new ArrayList<Person> ();
//		people.add(new Person(true, 1));
//		people.add(new Person(true, 2));
//		people.add(new Person(false, 1));
//		people.add(new Person(false, 2));
//		int idx = rand.nextInt(people.size());
//		Person p = people.remove(idx);
//		System.out.printf("Male=%b. Removed %d.\n", p.isMan, p.name);
	}

}
