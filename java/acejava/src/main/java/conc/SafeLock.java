package conc;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SafeLock {
	
	static class Friend {
		private final String name; //name of this friend
		private final Lock lock = new ReentrantLock();

		//constructor
		public Friend (String name) {
			this.name = name;
		}
		
		//getter for name
		public String getName() {
			return name;
		}
		
		public boolean impendingBow (Friend bower) {
			boolean boweeLock = false; //bowee (this) lock
			boolean bowerLock = false; //bower lock
			
			//try acquiring locks for both bowee and bower
			try {
				boweeLock = this.lock.tryLock();
				bowerLock = bower.lock.tryLock();				
			} finally { //finally after exceptions due to inability to acquire locks
				//if both locks could not be acquired
				if (!(boweeLock && bowerLock)) {
					//unlock the acquired bowee lock
					if (boweeLock) {
						this.lock.unlock();
					}
					//unlock the acquired bower lock
					if (bowerLock) {
						bower.lock.unlock();
					}
				}
			}
			
			//inform caller whether both locks could be acquired 
			return (boweeLock && bowerLock);
		}
		
		public void bow (Friend bower) {
			//if we could acquire both the bowee and bower locks
			if (impendingBow(bower)) {
				System.out.printf("%s: %s bowed.\n", this.name, bower.getName());
				bower.bowBack(this); //bow back
				//free the locks
				this.lock.unlock(); //bowee lock
				bower.lock.unlock(); //bower lock
			} else {
				//bower tried to bow, but stopped
				System.out.printf("%s: %s tried to bow, but saw that I had already started bowing.\n", this.name, bower.getName());
			}
		}
		
		public void bowBack (Friend bower) {
			System.out.printf("%s: %s bowed back.\n", this.name, bower.getName());
		}
	}

	static class BowRun implements Runnable {
		//bower will bow to bowee
		private Friend bower;
		private Friend bowee;
		
		public BowRun (Friend bower, Friend bowee) {
			this.bower = bower;
			this.bowee = bowee;
		}
		
		public void run () {
			//for random wait
			Random random = new Random ();
			
			//keep bowing to the bowee
			for (;;) {
			
				try {
					Thread.sleep(random.nextInt(10)); //random wait
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}

				//after sleep, bower bows to the bowee
				bowee.bow(bower);
			}
		}
	}
	
	public static void main(String[] args) {
		//two friends
		Friend alphonse = new Friend ("Alphonse");
		Friend gaston = new Friend ("Gaston");
		
		//threads for each friend to start bowing
		new Thread(new BowRun(alphonse, gaston)).start(); //Alphonse bows to Gaston
		new Thread(new BowRun(gaston, alphonse)).start(); //Gaston bows to Alphonse
	}
}
