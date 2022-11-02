
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Airport class for managing runways and simulating plane processing.
 * @author Zachary Cowan
 * @version 11/1/22
 * Fall/2022
 */
public class Airport extends TimerTask {

	/**
	 * Default Constructor for airport.
	 * Resets all processes to default.
	 * Default uses 3 runways.
	 */
	Airport() {
		planeApproaching = new PriorityQueue<Airplane>();
		runwayStorage = new Runway[3];
		runwayStorage[0] = new Runway(1);
		runwayStorage[1] = new Runway(2);
		runwayStorage[2] = new Runway(3);
		
	}
	/**
	 * Initialize Default Airport with custom simulation values.
	 * @param maxPlanes Max planes to spawn in simulation.
	 * @param spawnRate Rate of spawning planes.
	 * @param emergencyRate Rate of making emergency planes.
	 * @param maxSpawnDistance Max distance to spawn planes.
	 */
	Airport(int maxPlanes, double spawnRate, double emergencyRate, double maxSpawnDistance) {
		setMaxPlanes(maxPlanes);
		setSpawnRate(spawnRate);
		setEmergencyRate(emergencyRate);
		planeApproaching = new PriorityQueue<Airplane>();
		runwayStorage = new Runway[3];
		runwayStorage[0] = new Runway(1);
		runwayStorage[1] = new Runway(2);
		runwayStorage[2] = new Runway(3);
	}
	/**
	 * Initialize airport with custom number of runways.
	 * Max number of runways is 10.
	 * @param numberOfRunways Number of runways to create.
	 */
	Airport(int numberOfRunways) {
		if( (numberOfRunways > 0) && (numberOfRunways <= 10)) {
			runwayStorage = new Runway[numberOfRunways];
			planeApproaching = new PriorityQueue<Airplane>();
			for( int index = 0 ; index < numberOfRunways ; index++ ) {
				runwayStorage[index] = new Runway(index+1);
			}
		}
	}
	
	//
	//
	//Queues for managing planes
	private PriorityQueue<Airplane> planeApproaching = new PriorityQueue<Airplane>();
	private Runway[] runwayStorage;
	private Airplane newPlane;
	
	private int indexOfLastRunway = 0;
	private boolean runwaysEmpty = false;
	
	//
	//
	//Settings for simulation
	private int MAX_PLANES = 10;
	private double SPAWN_RATE = 0.7;
	private double EMERGENCY_RATE = 0.1;
	private int MAX_DISTANCE = 10;
	
	//
	//
	// Simulation Timer
	/**
	 * Timer used to run airport simulation.
	 */
	static Timer simClock = new Timer();
	/**
	 * Plane number
	 * Used to count planes spawned and as plane id in Airplane class.
	 */
	public int planeNum = 1;
	/**
	 * Number of seconds that have passed in simulation.
	 */
	public static int simTime = 0;
	
	/**
	 * Recursive method called based on time set in simClock.
	 * Default is 1000ms.
	 * Airport simulation logic.
	 */
	@Override
	public void run() {
		clear();
		double spawnSeed = Math.random();
		int distance = (int) ((Math.random() * MAX_DISTANCE) +1);
		if( (spawnSeed < SPAWN_RATE) && (planeNum <= MAX_PLANES) ) {
			//Newly spawned plane info
			newPlane = new Airplane();
			newPlane.setPlaneId(planeNum);
			newPlane.setDistance(distance);
			planeNum++;
			
			
			//If Emergency Plane prioritize approach
			if( spawnSeed < EMERGENCY_RATE ) {
				newPlane.setEmergency(true);
				planeApproaching.enqueueFront(newPlane);
				addToLeastBusyRunway();
			} else {
				planeApproaching.enqueue(newPlane);
				addToLeastBusyRunway();
			}
			
		}
		//Printing Queues.
		planeApproaching.printQueue("Approaching:");
		for( Runway runway : runwayStorage) {
			runway.printWaitingQueue();
			if( runway.isEmpty() && (planeNum == MAX_PLANES) ) {
				runwaysEmpty = true;
			} else {
				runwaysEmpty = false;
			}
		}
		
		if( runwaysEmpty) {
			System.out.println("All " + planeNum + " planes processed in " + simTime + " seconds.\n\tAirport used " + runwayStorage.length + " runways.");
			cancel();
		}
		
		if( planeNum == MAX_PLANES ) {
			addToLeastBusyRunway();
		}
		
		
		
		//Tick is every 1000 milliseconds
		simTime++;
	}
	
	//
	// Simulation
	
	/**
	 * Method to start the simulation.
	 */
	public void runSimulation() {
		simClock.schedule(this, 0, 1000);
	}
	
	
	/**
	 * Method to start the simulation.
	 * @param spawnRate Value to set for spawn rate of planes in simulation.
	 * @param emergencyRate Value to set for emergency rate of simulation.
	 */
	public void runSimulation(double spawnRate, double emergencyRate) {
		setSpawnRate(spawnRate);
		setEmergencyRate(emergencyRate);
		simClock.schedule(this, 0, 1000);
	}
	
	//
	//Getters and Setters
	/**
	 * Get simulation settings for max number of planes to spawn.
	 * @return int Max number of planes to spawn
	 */
	public int getMaxPlanes() {
		return MAX_PLANES;
	}
	/**
	 * Set simulation settings for max number of planes to spawn.
	 * @param maxPlanes Max number of planes to spawn
	 */
	public void setMaxPlanes(int maxPlanes) {
		this.MAX_PLANES = maxPlanes;
	}
	/**
	 * Get simulation settings for plane spawn rate.
	 * Values range from 0 to 1.
	 * Value of 1 spans a plane every iteration.
	 * @return double P
	 */
	public double getSpawnRate() {
		return SPAWN_RATE;
	}
	/**
	 * Set simulation settings for plane spawn rate.
	 * Values range from 0 to 1.
	 * Value of 1 spans a plane every iteration.
	 * @param spawnRate Rate at which to spawn planes.
	 */
	public void setSpawnRate(double spawnRate) {
		if( (0 <= spawnRate) && (spawnRate <= 1.0) ) {
			this.SPAWN_RATE = spawnRate;
		}
	}
	/**
	 * Get simulation settings for spawn rate of emergency plane.
	 * Values range from 0 to 1.
	 * Value of 1 spans an emergency plane every iteration.
	 * @return double P
	 */
	public double getEmergencyRate() {
		return EMERGENCY_RATE;
	}
	/**
	 * Set simulation settings for plane spawn rate.
	 * Values range from 0 to 1.
	 * Value of 1 spawns an emergency plane every iteration.
	 * @param emergencyRate Rate at which to spawn emergency plane.
	 */
	public void setEmergencyRate(double emergencyRate) {
		this.EMERGENCY_RATE = emergencyRate;
	}
	/**
	 * Set simulation settings for max distance to spawn plane from airport.
	 * Distance in miles.
	 * @param maxDistance Max distance to spawn plane.
	 */
	public void setMaxSpawnDistance(int maxDistance) {
		this.MAX_DISTANCE = maxDistance;
	}
	
	/**
	 * Conditional Logic to send plane in approaching queue to the least busy runway.
	 */
	private void addToLeastBusyRunway() {
		if( !planeApproaching.isEmpty() ) {
			if( indexOfLastRunway < (runwayStorage.length) ) {
				runwayStorage[indexOfLastRunway].sendToRunway(planeApproaching.dequeue().getData());
				indexOfLastRunway++;
			} else {
				indexOfLastRunway = 0;
				runwayStorage[indexOfLastRunway].sendToRunway(planeApproaching.dequeue().getData());
				indexOfLastRunway++;
			}
		}
	}
	
	
	/**
	 * Method to clear command line output
	 */
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
