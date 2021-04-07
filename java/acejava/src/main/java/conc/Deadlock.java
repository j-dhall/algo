package conc;

//This program demonstrates deadlock.
public class Deadlock {
	
	static class Friend {
		String name; //name of this friend
		int i; //checking effect of absence of synchronization
		//NOTE: In the absence of synchronized, I once got the following:
		//Notice **19**.
		/*
		 * 
				Attempt 10:
					Gaston: Alphonse bowed.
					Alphonse: Gaston bowed back.19  <---
					Alphonse: Gaston bowed.
					Gaston: Alphonse bowed back.20
		*/
		//constructor
		public Friend (String name) {
			this.name = name;
			i = 0;
		}
		
		//getter for name
		public String getName() {
			return name;
		}

		//your friend bowed to you
		public synchronized void bow (Friend bower) {
			i++;
			System.out.printf("%s: %s bowed.%d\n", this.name, bower.getName(), i);
			bower.bowBack(this); //bow back
		}
		
		//your friend bowed back to you
		public synchronized void bowBack (Friend bower) {
			i++;
			System.out.printf("%s: %s bowed back.%d\n", this.name, bower.getName(), i);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Friend alphonse = new Friend("Alphonse");
		Friend gaston = new Friend("Gaston");
		
		//loop this scenario to see if the threads deadlock
		//(avoiding repeated launching of this program/scenario)
		for (int i = 0; i < 10; i++) {
			
			System.out.printf("\nAttempt %d:\n", i+1);
			
			//thread 1
			Thread t1 = new Thread (new Runnable() {
				
				@Override
				public void run() {
					alphonse.bow(gaston);
				}
			});
			t1.start(); //spawn the thread
			
			//thread 2
			Thread t2 = new Thread (new Runnable() {
				
				@Override
				public void run() {
					gaston.bow(alphonse);
				}
			});
			t2.start(); //spawn the thread
			
			//wait for threads to complete
			t1.join();
			t2.join();
		}

	}

}
