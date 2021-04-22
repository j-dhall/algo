package conc;

import java.util.HashSet;
import java.util.Set;

public class MultithreadedTokenBucketFilter {
	
	private long possibleTokens = 0; //number of currently available tokens
	private final int MAX_TOKENS; //maximum number of tokens that can be issued
	private final int ONE_SECOND = 1000;
	
	public MultithreadedTokenBucketFilter(int maxTokens) {
		MAX_TOKENS = maxTokens;
	}
	
	private void daemonThread() {
		//make a token available every second, perpetually (for ever)
		while (true) {
			
			synchronized (this) {
				if (possibleTokens < MAX_TOKENS) {
					possibleTokens++; //issue a token
				}
				this.notify(); //notify the waiting consumers
			}
			
			//gap between creating tokens
			try {
				Thread.sleep(ONE_SECOND);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace(); //ignore the exception
			}
//			
//			//create a token
//			if (possibleTokens < MAX_TOKENS) {
//				possibleTokens++;
//				System.out.printf("Token created by %s.\n", Thread.currentThread().getName());				
//			}
//			//gap between creating tokens
//			try {
//				Thread.sleep(ONE_SECOND);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				//e.printStackTrace(); //ignore the exception
//			}
		}
	}
	
	void getToken() throws InterruptedException {
//		//if there is a token available, issue it
//		//else, sleep for a second, hoping that a token will be available
//		//keep trying for eternity
//		while (true) {
//			if (possibleTokens > 0) {
//				possibleTokens--; //issue a token
//				System.out.printf("Issued token to %s at time %d.\n", Thread.currentThread().getName(), System.currentTimeMillis()/1000);
//				return;
//			} else {
//				Thread.sleep(ONE_SECOND);
//			}
//		}
		
		//synchronize the shared state 'possibleTokens'
		synchronized (this) {
			//wait till a token is available
			while (possibleTokens == 0) {
				this.wait(); //wait to be notified by the token producers
			}
			
			possibleTokens--; //issue a token
		}
		
		System.out.printf("Issued token to %s at time %d.\n", Thread.currentThread().getName(), System.currentTimeMillis()/1000);
	}

	public static void main(String[] args) throws InterruptedException {
		Set<Thread> allConsumers = new HashSet<Thread> (); //all consumers of tokens
		Set<Thread> allIssuers = new HashSet<Thread> (); //all issuers of tokens
		MultithreadedTokenBucketFilter tokenBucket = new MultithreadedTokenBucketFilter(5);
		
		int numIssuers = 1;
		int numConsumers = 10;
		
		//create daemon threads to issue tokens
		for (int i = 0; i < numIssuers; i++) {
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					tokenBucket.daemonThread();
				}
			});
			t.setName("Issuer Thread:" + Integer.toString(i));
			t.setDaemon(true);
			allIssuers.add(t);
		}
		
		//create consumer threads
		for (int i = 0; i < numConsumers; i++) {
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						tokenBucket.getToken();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			t.setName("Consumer Thread:" + Integer.toString(i));
			allConsumers.add(t);
		}

		//start all threads
		for (Thread t: allIssuers)
			t.start();
		for (Thread t: allConsumers)
			t.start();
		//join all threads
		for (Thread t: allIssuers)
			t.join();
		for (Thread t: allConsumers)
			t.join();
	}

}

/*
 * Issued token to Consumer Thread:9 at time 1619069773.
Token created by Issuer Thread:0.
Token created by Issuer Thread:0.
Issued token to Consumer Thread:5 at time 1619069775.
Token created by Issuer Thread:0.
Issued token to Consumer Thread:1 at time 1619069776.
Issued token to Consumer Thread:6 at time 1619069776.
Token created by Issuer Thread:0.
Issued token to Consumer Thread:8 at time 1619069777.
Token created by Issuer Thread:0.
Issued token to Consumer Thread:0 at time 1619069778.
Token created by Issuer Thread:0.
Issued token to Consumer Thread:4 at time 1619069779.
Token created by Issuer Thread:0.
Issued token to Consumer Thread:2 at time 1619069780.
Token created by Issuer Thread:0.
Issued token to Consumer Thread:7 at time 1619069781.
Issued token to Consumer Thread:3 at time 1619069781.
Token created by Issuer Thread:0.
Token created by Issuer Thread:0.
Token created by Issuer Thread:0.
Token created by Issuer Thread:0.
Token created by Issuer Thread:0.
*/
