import java.util.Timer;
import java.util.TimerTask;

/**
 * Runway class for storing planes and simulating processing time.
 * @author Zachary Cowan
 * @version 11/1/22
 * Fall/2022
 */
public class Runway extends TimerTask {

	private PriorityQueue<Airplane> readyToLand = new PriorityQueue<Airplane>();
	private Airplane previousLanded;
	private int runwayId;
	private int numPlanesProcessed = 0;
	private int numWaiting = 0;
	
	private static Timer simClock;
	
	Runway(int runwayId) {
		simClock = new Timer();
		simClock.schedule(this, 0, 10000);
		this.runwayId = runwayId;
	}
	
	@Override
	public void run() {
		planeProcessed();
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
	
	public void planeProcessed() {
		if( !readyToLand.isEmpty() ) {
			numWaiting -= 1;
			numPlanesProcessed++;
			previousLanded = readyToLand.dequeue().getData();
		}
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

	
	
	
}
