
/**
 * Exception for when queue is empty.
 * @author Zachary Cowan
 * @version 9/29/2022
 * Fall/2022
 */
public class EmptyQueueException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EmptyQueueException() {
		this("Queue is empty.");
	}//closes constructor
	
	public EmptyQueueException(String msg) {
		super(msg);
	}

}
