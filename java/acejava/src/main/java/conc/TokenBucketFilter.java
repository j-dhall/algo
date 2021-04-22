package conc;

import java.util.HashSet;
import java.util.Set;

public class TokenBucketFilter {
	
	private int maxTokens;
	
	private long lastRequestTime = System.currentTimeMillis();
	
	int possibleTokens = 0;
	
	public TokenBucketFilter(int maxTokens) {
		this.maxTokens = maxTokens;
	}
	
	public synchronized void getToken() throws InterruptedException {
		
		long currRequestTime = System.currentTimeMillis();
		long diffTime = currRequestTime - lastRequestTime;
		//lastRequestTime = System.currentTimeMillis(); //update the last request time
		possibleTokens += diffTime/1000; //add difftime tokens to possible tokens.
		//case 1: time elapsed since last call is > maxTokens seconds
		if (possibleTokens > maxTokens) { //cap the excess tokens
			possibleTokens = maxTokens;
		}
		
		//case 2: time elapsed is < maxTokens seconds
		//add difftime tokens to possible tokens.
		//see if there is a token that can be issued.
		//if yes, issue and return,
		//case 3: else wait for 1 second and return.
		if (possibleTokens > 0) { //see if there is a token that can be issued.
			possibleTokens--; //if yes, issue and return
			System.out.println("Granting " + Thread.currentThread().getName() + " token at " + (System.currentTimeMillis() / 1000));
		} else {
			Thread.sleep(1000); //else wait for 1 second and return.
			System.out.println("Slept. Granting " + Thread.currentThread().getName() + " token at " + (System.currentTimeMillis() / 1000));
		}
		//if we move this line to after the 2nd line, then every other thread
		//will get a token without sleeping.
		//this is because last request time gets recorded before the sleep of 1 second.  
		lastRequestTime = System.currentTimeMillis(); //update the last request time
	}
	
	public synchronized void getToken2() throws InterruptedException {

        possibleTokens += (System.currentTimeMillis() - lastRequestTime) / 1000;

        if (possibleTokens > maxTokens) {
            possibleTokens = maxTokens;
        }

        if (possibleTokens == 0) {
            Thread.sleep(1000);
            System.out.println("Sleep Granting " + Thread.currentThread().getName() + " token at " + (System.currentTimeMillis() / 1000));
        } else {
            possibleTokens--;
            System.out.println("Granting " + Thread.currentThread().getName() + " token at " + (System.currentTimeMillis() / 1000));
        }
        lastRequestTime = System.currentTimeMillis();

        //System.out.println("Granting " + Thread.currentThread().getName() + " token at " + (System.currentTimeMillis() / 1000));
    }


	public static void main(String[] args) throws InterruptedException {
        Set<Thread> allThreads = new HashSet<Thread>();
        final TokenBucketFilter tokenBucketFilter = new TokenBucketFilter(1);

        for (int i = 0; i < 10; i++) {

            Thread thread = new Thread(new Runnable() {
                public void run() {
                    try {
                        tokenBucketFilter.getToken();
                    } catch (InterruptedException ie) {
                        System.out.println("We have a problem");
                    }
                }
            });
            thread.setName("Thread_" + (i + 1));
            allThreads.add(thread);
        }

        for (Thread t : allThreads) {
            t.start();
        }

        for (Thread t : allThreads) {
            t.join();
        }
	}

}
