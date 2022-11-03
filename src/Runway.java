
/**
 * Runway class for storing planes and managing landing of planes.
 * @author Zachary Cowan
 * @version 11/1/22
 * Fall/2022
 */
public class Runway {

	private PriorityQueue<Airplane> readyToLand = new PriorityQueue<Airplane>();
	private Airplane previousLanded;
	private int runwayId;
	private int numPlanesProcessed = 0;
	private int numWaiting = 0;
	private int timeOfFirstPlane = 0;
	private int LANDING_TIME_SEC = 10;
	private boolean timeHasBeenSet = false;
	
	Runway(int runwayId) {
		this.runwayId = runwayId;
	}
	
	public void sendToRunway(Airplane plane) {
		numWaiting += 1;
		readyToLand.enqueue(plane);
	}
	public void prioritySendToRunway(Airplane plane) {
		numWaiting += 1;
		readyToLand.enqueueFront(plane);
	}
	
	
	public void printWaitingQueue() {
		readyToLand.printQueue("Runway " + runwayId + " queue:");
	}
	
	public Airplane planeProcessed() {
		numWaiting -= 1;
		numPlanesProcessed++;
		previousLanded = readyToLand.dequeue().getData();
		return previousLanded;
	}
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
	public int getNumWaiting() {
		return numWaiting;
	}
	public Airplane getTop() {
		return readyToLand.getFrontData();
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
