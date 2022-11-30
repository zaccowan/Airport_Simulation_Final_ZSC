/**
 * Exception for when an airport tries to create too many runways.
 * @author Zachary Cowan
 * @version 9/29/2022
 * Fall/2022
 */
public class MaxRunwayException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * Default message for max runways exception
	 */
	public MaxRunwayException() {
		this("Too many runways.");
	}//closes constructor
	
	/**
	 * Custom message for max runways exception
	 * @param msg Message for execption.
	 */
	public MaxRunwayException(String msg) {
		super(msg);
	}

}

