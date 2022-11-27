
/**
 * Exception for when queue is empty.
 * @author Zachary Cowan
 * @version 9/29/2022
 * Fall/2022
 */
public class EmptyQueueException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * Default message for empty queue exception
	 */
	public EmptyQueueException() {
		this("Queue is empty.");
	}//closes constructor
	
	/**
	 * Custom message for empty queue exception
	 * @param msg Message for exception.
	 */
	public EmptyQueueException(String msg) {
		super(msg);
	}

}
