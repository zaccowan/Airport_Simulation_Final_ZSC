import java.util.PriorityQueue;

//import java.util.PriorityQueue;
/**
 * Runway class for storing planes and managing landing of planes.
 * @author Zachary Cowan
 * @version 11/1/22
 * Fall/2022
 */
public class Runway {
	
	//
	//
	//Constructors
	
	Runway(int runwayId) {
		this.runwayId = runwayId;
	}
	
	//
	//
	//Runway data
	
	/**
	 * Stores planes to process whenever a plane is sent to the runway.
	 */
	private PriorityQueue<Airplane> readyToLand = new PriorityQueue<Airplane>();
//	private PriorityQueue<Airplane> readyToLand = new PriorityQueue<Airplane>();
	/**
	 * Stores the most recently processed plane.
	 */
	private Airplane previousLanded;
	/**
	 * Identification number for a Runway object
	 */
	private int runwayId;
	
	/**
	 * Stores the number of planes processed by a runway.
	 */
	private int numPlanesProcessed = 0;
	/**
	 * Stores the time in seconds at which a Runway object first recieved its first plane.
	 */
	private int timeOfFirstPlane = 0;
	
	/**
	 * Time needed to process a plane at runway.
	 */
	private int LANDING_TIME_SEC = 10;
	/**
	 * Flag to prevent overriding of timeOfFirstPlane
	 */
	private boolean timeHasBeenSet = false;
	
	
	
	
	//
	//
	//Runway Specific Methods
	
	/**
	 * Recieves an airplane and adds it to end of wait list.
	 * @param plane Plane to put in runway processing queue
	 */
	public void sendToRunway(Airplane plane) {
		readyToLand.add(plane);
	}
	/**
	 * Recieves an airplane and adds it to front of wait list.
	 * @param plane Plane to put in runway processing queue
	 */
	public void prioritySendToRunway(Airplane plane) {
		readyToLand.add(plane);
	}
	/**
	 * Prints the runway id and all Airplanes waiting in queue
	 */
	public void printWaitingQueue() {
		System.out.println("Runway " + runwayId + ":");
		for( Airplane plane : readyToLand ) {
			System.out.print("\tPlane " + plane.toString() + "\n");
		}
	}
	/**
	 * Processes a plane and dequeues it from the wait list
	 * @return Airplane plane at top of list.
	 */
	public Airplane planeProcessed() {
		if( (readyToLand.peek().getDistance() == 0) || (readyToLand.peek().isEmergency())) {
			numPlanesProcessed++;
			previousLanded = readyToLand.poll();
			return previousLanded;
		}
		return null;
	}
	/**
	 * See if runway has any planes to process.
	 * @return True if no planes are waiting in runway
	 */
	public boolean isEmpty() {
		return readyToLand.isEmpty();
	}

	
	
	
	//
	//
	//Getters and Setters
	
	/**
	 * Get the last airplane that was dequeued.
	 * @return Airplane that was last dequeued.
	 */
	public Airplane getPreviousLanded() {
		return previousLanded;
	}

	
	/**
	 * Get the id of the runway.
	 * @return Id value for runway
	 */
	public int getRunwayId() {
		return runwayId;
	}

	/**
	 * Set the id of the runway
	 * @param runwayId Id to set for runway
	 */
	public void setRunwayId(int runwayId) {
		this.runwayId = runwayId;
	}

	/**
	 * Get the number of planes processed by the runway
	 * @return The number of planes processed by the runway
	 */
	public int getNumPlanesProcessed() {
		return numPlanesProcessed;
	}
	/**
	 * Get plane at the front of runway queue
	 * @return Airplane at the front of the queue.
	 */
	public Airplane getTopPlane() {
		return readyToLand.peek();
	}

	/**
	 * Get the time in which the runway recieved its first plane.
	 * Recieved from global clock inside of airport.
	 * @return the time in seconds
	 */
	public int getTimeOfFirstPlane() {
		return timeOfFirstPlane;
	}

	/**
	 * Set the time in which the runway recieved its first plane.
	 * Recieved from global clock inside of airport.
	 * @param timeOfFirstPlane Global clock time in seconds at which the first plane was received.
	 */
	public void setTimeOfFirstPlane(int timeOfFirstPlane) {
		this.timeOfFirstPlane = timeOfFirstPlane;
	}

	/**
	 * Get the time needed to land a plane
	 * @return The time needed to land a plane
	 */
	public int getLANDING_TIME_SEC() {
		return LANDING_TIME_SEC;
	}

	/**
	 * Set the time needed to land a plane.
	 * @param LANDING_TIME_SEC Time in seconds
	 */
	public void setLANDING_TIME_SEC(int LANDING_TIME_SEC) {
		LANDING_TIME_SEC = LANDING_TIME_SEC;
	}

	/**
	 * Get the flag that identifies that a runway has had a time set for first plane received.
	 * Prevents overwriting of the initial time.
	 * @return True if timeOfFirstPlane has been set.
	 */
	public boolean getTimeHasBeenSet() {
		return timeHasBeenSet;
	}

	/**
	 * Set the flag that identifies that a runway has had a time set for first plane received.
	 * Prevents overwriting of the initial time.
	 * @param timeHasBeenSet True if timeOfFirstPlane has been set.
	 */
	public void setTimeHasBeenSet(boolean timeHasBeenSet) {
		this.timeHasBeenSet = timeHasBeenSet;
	}

	
	
	
}
