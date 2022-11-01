
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Airport extends TimerTask {

	//
	//
	//Queues for managing planes
	private PriorityQueue<Airplane> planeApproaching = new PriorityQueue<Airplane>();
	private Runway runway1 = new Runway(1);
	private Runway runway2 = new Runway(2);
	private Runway runway3 = new Runway(3);
	private Airplane newPlane;
	
	//
	//
	//Settings for simulation
	final private int MAX_PLANES = 20;
	private double spawnRate = 0.7;
	private double emergencyRate = 0.1;
	
	//
	//
	// Simulation Timer
	static Timer simClock = new Timer();
	public int planesSpawned = 1;
	public static int displayTick = 0;
	
	@Override
	public void run() {
		clear();
		double spawnSeed = Math.random();
		if( (spawnSeed < spawnRate) && (planesSpawned <= MAX_PLANES) ) {
			
			//Newly spawned plane info
			newPlane = new Airplane();
			planesSpawned++;
			newPlane.setPlaneId(planesSpawned);
			newPlane.setDistance(5);
			
			
			//If Emergency Plane prioritize approach
			if( spawnSeed < emergencyRate ) {
				newPlane.setDistance(0);
				planeApproaching.enqueueFront(newPlane);
				
				System.out.println("Emergency with plane: " + newPlane.getPlaneId() + ". Prioritizing Landing.");
				System.out.println();
				
				priorityAddToLeastBusyRunway();
			}
			//If not emergency plane add to queue
			else {
				planeApproaching.enqueue(newPlane);
				
				System.out.println("Airplane: " + newPlane.getPlaneId() + " approaching.");
				System.out.println();
				
				if( planeApproaching.getFront().getData().getDistance() == 0 ) {
					addToLeastBusyRunway();
				}
			}
		}
		
		
		
		//Handling Last plane to approach airport
		if ( planesSpawned == MAX_PLANES ) {
			addToLeastBusyRunway();
		}
		
		//Printing Runway Queues
		if( !runway1.isEmpty() || !runway2.isEmpty() || !runway3.isEmpty() ) {
			planeApproaching.printQueue("Approaching:");
			runway1.printWaitingQueue();
			runway2.printWaitingQueue();
			runway3.printWaitingQueue();
		}
		
		//Tick is every 1000 milliseconds
		displayTick++;
	}
	
	//
	//
	// Simulation
	public void runSimulation() {
		simClock.schedule(this, 0, 1000);
	}
	public void runSimulation(double spawnRate, double emergencyRate) {
		setSpawnRate(spawnRate);
		setEmergencyRate(emergencyRate);
		simClock.schedule(this, 0, 1000);
	}
	
	//
	//
	//Getters and Setters
	public int getMaxPlanes() {
		return MAX_PLANES;
	}
	public double getSpawnRate() {
		return spawnRate;
	}
	public void setSpawnRate(double spawnRate) {
		this.spawnRate = spawnRate;
	}

	public double getEmergencyRate() {
		return emergencyRate;
	}

	public void setEmergencyRate(double emergencyRate) {
		this.emergencyRate = emergencyRate;
	}
	
	public static void clear()
    {
        try
        {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }
	
	private void addToLeastBusyRunway() {
		if( (runway1.getNumWaiting() <= runway2.getNumWaiting()) && (runway1.getNumWaiting() <= runway3.getNumWaiting()) ) {
			runway1.sendToRunway(planeApproaching.dequeue().getNode().getData());
		}
		else if( (runway2.getNumWaiting() <= runway1.getNumWaiting()) && (runway1.getNumWaiting() <= runway3.getNumWaiting()) ) {
			runway2.sendToRunway(planeApproaching.dequeue().getNode().getData());
		}
		else if( (runway3.getNumWaiting() <= runway1.getNumWaiting()) && (runway3.getNumWaiting() <= runway2.getNumWaiting()) ) {
			runway3.sendToRunway(planeApproaching.dequeue().getNode().getData());
		}
		else {
			runway1.sendToRunway(planeApproaching.dequeue().getNode().getData());
		}
	}
	
	private void priorityAddToLeastBusyRunway() {
		if( (runway1.getNumWaiting() <= runway2.getNumWaiting()) && (runway1.getNumWaiting() <= runway3.getNumWaiting()) ) {
			runway1.prioritySendToRunway(planeApproaching.dequeue().getNode().getData());
		}
		else if( (runway2.getNumWaiting() <= runway1.getNumWaiting()) && (runway1.getNumWaiting() <= runway3.getNumWaiting()) ) {
			runway2.prioritySendToRunway(planeApproaching.dequeue().getNode().getData());
		}
		else if( (runway3.getNumWaiting() <= runway1.getNumWaiting()) && (runway3.getNumWaiting() <= runway2.getNumWaiting()) ) {
			runway3.prioritySendToRunway(planeApproaching.dequeue().getNode().getData());
		}
		else {
			runway1.prioritySendToRunway(planeApproaching.dequeue().getNode().getData());
		}
	}
	
	
	

}
