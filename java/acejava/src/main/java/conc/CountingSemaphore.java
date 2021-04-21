package conc;

public class CountingSemaphore {

	private int capacity; //number of resources (permits) that can be used (issued)
	private int used; //how many permits have been issued?
	
	public CountingSemaphore(int capacity) {
		this.capacity = capacity;
		this.used = 0; //none used, to begin with
	}
	
	//synchronized since the shared state 'used' is being changed
	//If a thread is currently executing acquire() then 
	//another thread can't execute release() on the same semaphore object
	public synchronized void acquire () throws InterruptedException {
		//wait till there is a permit available
		while (used == capacity)
			wait();
		
		//Does it matter if we switch the order of the two statements, 
		//i.e. call notify() first and then manipulate usedPermits ? The answer is no.
		//When notify() is called, the executing thread is still synchronized on the semaphore object
		//and any other thread will not be scheduled until the executing thread
		//exists the release() or acquire() method 
		//and by then the usedPermits variable has already been incremented or decremented
		//even though that is the last statement in each method.
		
		used++; //acquire a permit
		//we are exiting the synchronized function. 
		//notify other threads waiting to read/update this object (shared resource)
		notify(); 
	}
	
	//synchronized since the shared state 'used' is being changed
	public synchronized void release () throws InterruptedException {
		//wait till there is a permit that can be released
		while (used == 0)
			wait();
		
		used--; //release a permit
		//we are exiting the synchronized function. 
		//notify other threads waiting to read/update this object (shared resource)
		notify();
	}
	
	public static void main(String[] args) throws InterruptedException {
        
      final CountingSemaphore cs = new CountingSemaphore(2);

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    for (int i = 0; i < 5; i++) {
                        cs.acquire();
                        System.out.println("Ping " + i);
                    }
                } catch (InterruptedException ie) {

                }
            }
        });

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    try {
                        cs.release();
                        System.out.println("Pong " + i);
                    } catch (InterruptedException ie) {

                    }
                }
            }
        });

        t1.start();
        Thread.sleep(10);
        t2.start();
        
        
        t1.join();
        t2.join();
	}
}
