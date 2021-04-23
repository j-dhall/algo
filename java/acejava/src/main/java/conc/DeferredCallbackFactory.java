package conc;

import java.util.PriorityQueue;
import java.util.function.Consumer;

public class DeferredCallbackFactory {
	
	private static class DeferredCallbackImpl extends DeferredCallback {
		private PriorityQueue<NodeCallback> queue; //priority queue of callbacks
		//private Thread daemon; //keep a reference to the daemon to wait for it
		
		public DeferredCallbackImpl() {
			queue = new PriorityQueue<NodeCallback>(); //initialize the queue
		}
		
//		//the main program will call this function when exiting.
//		//we should wait on the daemon thread to keep the program running
//		//to execute any pending callback's.
//		protected void finalize () {
//			try {
//				daemon.join();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
		//spawn the daemon thread to start executing callbacks
		private void initialize() throws InterruptedException {
			Thread daemon = new Thread(new Runnable() {
				
				@Override
				public void run() {
					daemonExecCallbacks();
				}
			});
			//no, we dont set the thread to daemon. 
			//otherwise it is killed when the main program terminates.
			//no callbacks ever get called.
			//daemon.setDaemon(true); //set the thread as a daemon

			daemon.start(); //start the thread
			//daemon.join(); //wait for thread in the main thread
		}
		
		private class DaemonRunnable implements Runnable {

			private Consumer<String> callback;
			private String arg;
			
			public DaemonRunnable(Consumer<String> callback, String arg) {
				this.callback = callback;
				this.arg = arg;
			}
			
			@Override
			public void run() {
				callback.accept(arg);
			}

		}
		
		private void daemonExecCallbacks() {
			//loop for eternity
			while (true) {
				
				//we enter a synchronized block since we are dealing with shared state (queue).
				//this should be ok in an infinite loop since the thread waits
				//whenever the queue is empty or when the next scheduled callback is
				//at a later point in time.
				synchronized (this) {
					//check if there is a callback due for execution
					long currTime = System.currentTimeMillis()/1000; //current time in seconds
					NodeCallback node = queue.peek(); //probe the top of the queue
					if (node != null) { //if the queue is not empty
						if (currTime >= node.timestamp) { //if a callback's execution is due 
							node = queue.poll(); //dequeue the callback
							//spawn a thread to execute the callback
							Thread tCallback = new Thread(new DaemonRunnable(node.callback, node.arg));
							tCallback.start();
							//do not join the thread, let it complete independently
						} else {
							//we come here when the waiting daemon is notified of a callback queued,
							//but the callback at the top of the queue is not due yet.
							//so, the daemon waits for that diff time.
							//while the daemon waits for that diff time,
							//it can be awakened by a callback getting queued,
							//just in case that it happens to be executed at a time prior to
							//the callback on which the wait begun.
							long difftime = node.timestamp - currTime;
							try {
								this.wait(difftime * 1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					} else {
						//wait while the queue is empty
						//get notified when a callback is queued
						//we come here when all callback's have been called, and there are none queued
						//we also come here when the program starts, since the queue is empty
						try {
							this.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} //if-else: node != null
				} //synchronized
			} //while(true)
		} //daemonExecCallbacks()

		@Override
		//since this function is called by multiple threads, keep the shared state (queue) synchronized
		public synchronized void registerCallback(Consumer<String> callback, String arg, long seconds) {
			long callbackTime = System.currentTimeMillis()/1000; //when to call this callback? initialize to current time
			callbackTime += seconds; //move forward the callback time by 'seconds' seconds
			NodeCallback node = new NodeCallback(callback, arg, callbackTime); //create a node for queueing
			queue.add(node); //enqueue the node
			this.notify(); //notify the waiting daemon that the queue has a new callback added
		}		
	}
	
	
	private DeferredCallbackFactory() {} //private constructor, to permit use of object through factory
	private static DeferredCallbackImpl objDeferredCallback; //static reference to hold the factory object
	//static factory function to (create and) return the factory object 
	public static DeferredCallback getDeferredCallback() {
		if (objDeferredCallback == null) {
			objDeferredCallback = new DeferredCallbackImpl(); //create the factory object 
			try {
				objDeferredCallback.initialize(); //initialize the factory object
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return objDeferredCallback;
	}

	private static class SampleCallbacks {
		public static void printHello (String name) {
			System.out.printf("Hello %s!\n", name);
		}
		public static void printHi (String name) {
			System.out.printf("Hi %s!\n", name);
		}
		public static void printHola (String name) {
			System.out.printf("Hola %s!\n", name);
		}
	}
	
	public static void main(String[] args) {
		DeferredCallback callbackReg = DeferredCallbackFactory.getDeferredCallback();
		//Consumer<String> callback = SampleCallbacks::printHello;
		callbackReg.registerCallback(SampleCallbacks::printHello, "Keysha", 2);
		callbackReg.registerCallback(SampleCallbacks::printHi, "Anaya", 2);
		callbackReg.registerCallback(SampleCallbacks::printHola, "Khushi", 3);
//		try {
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
