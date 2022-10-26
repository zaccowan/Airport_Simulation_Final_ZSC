
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Airport extends TimerTask {

	//
	//
	//Queues for managing planes
	private PriorityQueue<Airplane> planeApproaching = new PriorityQueue<Airplane>();
	private PriorityQueue<Airplane> readyToLand = new PriorityQueue<Airplane>();
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
			if( spawnSeed < emergencyRate ) {
				planeApproaching.enqueueFront(newPlane);
				planesSpawned++;
				System.out.println("Emergency with plane: " + newPlane.getPlaneId() + ". Prioritizing Landing.");
				readyToLand.enqueue(planeApproaching.dequeue().getNode().getData());
				planeApproaching.printQueue("Approaching:");
				readyToLand.printQueue("Ready To Land:");
				
			}
			else {
				planeApproaching.enqueue(newPlane);
				planesSpawned++;
				System.out.println("Airplane: " + newPlane.getPlaneId() + " approaching.");
				planeApproaching.printQueue("Approaching:");
				readyToLand.printQueue("Ready To Land:");
			}
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
	
	
	

}
