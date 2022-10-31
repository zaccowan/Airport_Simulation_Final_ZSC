
public class Runway {

	private PriorityQueue<Airplane> readyToLand = new PriorityQueue<Airplane>();
	private Airplane previousLanded;
	private int runwayId;
	private int numPlanesProcessed = 0;
	private int numWaiting = 0;
	
	Runway(int runwayId) {
		this.setRunwayId(runwayId);
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
