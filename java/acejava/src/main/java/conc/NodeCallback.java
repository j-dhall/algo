package conc;

import java.util.function.Consumer;

public class NodeCallback implements Comparable<NodeCallback> {
	public Consumer<String> callback; //callback function
	public String arg; //argument to the callback function
	public long timestamp; //timestamp in seconds on when the callback function will be called
	
	public NodeCallback(Consumer<String> callback, String arg, long timestamp) {
		this.callback = callback;
		this.arg = arg;
		this.timestamp = timestamp;
	}

	@Override
	public int compareTo(NodeCallback o) {
		return (this.timestamp <= o.timestamp) ? 0 : 1;
	}
}
