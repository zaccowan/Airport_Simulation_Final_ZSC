
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
	final private int MAX_PLANES = 1000;
	private double spawnRate = 0.7;
	private double emergencyRate = 0.1;
	
	//
	//
	// Simulation Timer
	Timer simClock = new Timer();
	public static int planesSpawned = 1;
	public static int displayTick = 0;
	
	@Override
	public void run() {
		double spawnSeed = Math.random();
		if( spawnSeed < spawnRate ) {
			clear();
			newPlane = new Airplane();
			newPlane.setPlaneId(planesSpawned);
			//If Emergency Plane prioritize approach
			if( spawnSeed < emergencyRate ) {
				planeApproaching.enqueueFront(newPlane);
				planesSpawned++;
				System.out.println("Emergency with plane: " + newPlane.getPlaneId() + ". Prioritizing Landing.");
				priorityAddToLeastBusyRunway();
				planeApproaching.printQueue("Approaching:");
				runway1.printWaitingQueue();
				
			}
			//If not emergency plane add to queue
			else {
				planeApproaching.enqueue(newPlane);
				planesSpawned++;
				System.out.println("Airplane: " + newPlane.getPlaneId() + " approaching.");
				addToLeastBusyRunway();
			}
			
			planeApproaching.printQueue("Approaching");
			System.out.println();
			runway1.printWaitingQueue();
			runway2.printWaitingQueue();
			runway3.printWaitingQueue();
		}
		
		

//		System.out.println(displayTick);
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
