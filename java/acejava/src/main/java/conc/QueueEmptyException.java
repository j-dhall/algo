package conc;

public class QueueEmptyException extends Exception {
	public QueueEmptyException (String errorMsg) {
		super (errorMsg);
	}
}
