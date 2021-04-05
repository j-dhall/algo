package conc;

public class QueueFullException extends Exception {
	public QueueFullException (String errorMsg) {
		super (errorMsg);
	}
}

