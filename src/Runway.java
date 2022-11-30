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
	
	public Airplane getPreviousLanded() {
		return previousLanded;
	}

	public int getRunwayId() {
		return runwayId;
	}

	public void setRunwayId(int runwayId) {
		this.runwayId = runwayId;
	}

	public int getNumPlanesProcessed() {
		return numPlanesProcessed;
	}
	public Airplane getTopPlane() {
		return readyToLand.peek();
	}

	public int getTimeOfFirstPlane() {
		return timeOfFirstPlane;
	}

	public void setTimeOfFirstPlane(int timeOfFirstPlane) {
		this.timeOfFirstPlane = timeOfFirstPlane;
	}

	public int getLANDING_TIME_SEC() {
		return LANDING_TIME_SEC;
	}

	public void setLANDING_TIME_SEC(int lANDING_TIME_SEC) {
		LANDING_TIME_SEC = lANDING_TIME_SEC;
	}

	public boolean getTimeHasBeenSet() {
		return timeHasBeenSet;
	}

	public void setTimeHasBeenSet(boolean timeHasBeenSet) {
		this.timeHasBeenSet = timeHasBeenSet;
	}

	
	
	
}
