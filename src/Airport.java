import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.PriorityQueue;

/**
 * Airport class for managing runways and simulating plane processing.
 * @author Zachary Cowan
 * @version 11/1/22
 * Fall/2022
 */
public class Airport extends TimerTask {
	//
	//
	//Constructors
	
	/**
	 * Default Constructor for airport.
	 * Resets all processes to default.
	 * Default uses 3 runways.
	 */
	Airport() {
		simClock = new Timer();
		planesApproaching = new PriorityQueue<Airplane>();
		runwayStorage = new Runway[3];
		runwayStorage[0] = new Runway(1);
		runwayStorage[1] = new Runway(2);
		runwayStorage[2] = new Runway(3);
		
	}
	/**
	 * Initialize Default Airport with custom simulation values.
	 * Max number of runways is 10.
	 * Rates are between 1 and 0.
	 * Max distance is 10.
	 * @param numberOfRunways Number of runways to create.
	 * @param maxPlanes Max planes to spawn in simulation.
	 * @param spawnRate Rate of spawning planes.
	 * @param emergencyRate Rate of making emergency planes.
	 * @param maxSpawnDistance Max distance to spawn planes.
	 * @throws MaxRunwayException if number of runways to use exceeds 20.
	 */
	Airport(int numberOfRunways, int maxPlanes, double spawnRate, double emergencyRate, double maxSpawnDistance) throws MaxRunwayException {
		simClock = new Timer();
		if( (numberOfRunways > 0) && (numberOfRunways <= 20)) {
			runwayStorage = new Runway[numberOfRunways];
			planesApproaching = new PriorityQueue<Airplane>();
			for( int index = 0 ; index < numberOfRunways ; index++ ) {
				runwayStorage[index] = new Runway(index+1);
			}
		} 
		else if( numberOfRunways == 0) {
			runwayStorage = new Runway[3];
			runwayStorage[0] = new Runway(1);
			runwayStorage[1] = new Runway(2);
			runwayStorage[2] = new Runway(3);
		} 
		else {
			throw new MaxRunwayException("Cannot instantiate " + numberOfRunways + " runways. Max is 20." );
		}
		
		if( maxPlanes == 0 ) {
			setMaxPlanes(10);
		} else {
			setMaxPlanes(maxPlanes);
		}
		
		if( spawnRate == 2 ) {
			setSpawnRate(0.7);
		} else {
			setSpawnRate(spawnRate);
		}
		
		if( emergencyRate == 2 ) {
			setEmergencyRate(0.1);
		} else {
			setSpawnRate(emergencyRate);
		}
		planesApproaching = new PriorityQueue<Airplane>();
	}
	/**
	 * Initialize airport with custom number of runways.
	 * Max number of runways is 20.
	 * @param numberOfRunways Number of runways to create.
	 * @throws MaxRunwayException if number of runways to use exceeds 20.
	 */
	Airport(int numberOfRunways) throws MaxRunwayException {
		simClock = new Timer();
		if( (numberOfRunways > 0) && (numberOfRunways <= 20)) {
			runwayStorage = new Runway[numberOfRunways];
			planesApproaching = new PriorityQueue<Airplane>();
			for( int index = 0 ; index < numberOfRunways ; index++ ) {
				runwayStorage[index] = new Runway(index+1);
			}
		} else {
			throw new MaxRunwayException("Cannot instantiate " + numberOfRunways + " runways. Max is 20." );
		}
	}
	
	
	
	
	//
	//
	//Plane management
	
	/**
	 * Linked List Deque to store newly spawned airplanes on approach to airport.
	 */
	private PriorityQueue<Airplane> planesApproaching = new PriorityQueue<Airplane>();
	/**
	 * Stores runways for airport
	 * Allows for a variable number of runways to be instantiated upon creation of an aiport object.
	 */
	private Runway[] runwayStorage;
	/**
	 * New plane spawned within the simulation
	 */
	private Airplane newPlane;
	/**
	 * Keeps track of number of runways empty during simulation.
	 * Used to terminate simulation once all planes have been processed.
	 */
	private int runwaysEmpty;
	
	
	
	
	//
	//
	//Simulation Settings
	
	/**
	 * Max number of planes to spawn during simulation.
	 */
	private int MAX_PLANES = 10;
	/**
	 * Rate at which to spawn planes in simulation.
	 */
	private double SPAWN_RATE = 0.7;
	
	/**
	 * Rate at which to spawn emergency planes in simulation.
	 */
	private double EMERGENCY_RATE = 0.1;
	/**
	 * Max distance to spawn a plane from airport.
	 */
	private int MAX_DISTANCE = 5;
	
	/**
	 * Keeps track of the number of emergency planes during the simulation.
	 */
	private int numEmergencyPlanesSpawned = 0;
	
	
	//
	//
	// Simulation Timer
	/**
	 * Timer used to run airport simulation.
	 */
	static Timer simClock;
	/**
	 * Plane number
	 * Used to count planes spawned and as plane id in Airplane class.
	 */
	public int planeNum = 1;
	/**
	 * Number of seconds that have passed in simulation.
	 */
	public static int simTime = 0;
	
	
	
	
	//
	// Simulation Wrapper for running
	
	/**
	 * Method to start the simulation.
	 * Starts game clock that calls run().
	 */
	public void runSimulation() {
		simClock.schedule(this, 0, 1000);
	}
	
	
	
	
	//
	//
	//Main Simulation Method
	//
	//
	
	/**
	 * Recursive method called based on time set in simClock.
	 * Default is 1000ms, aka every second.
	 * Airport simulation logic.
	 */
	@Override
	public void run() {
		clear();
		
		//Plane Spawning
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
				numEmergencyPlanesSpawned++;
				newPlane.setEmergency(true);
				planesApproaching.add(newPlane);
			} 
			
			//Not emergency add to approach queue as normal
			else {
				planesApproaching.add(newPlane);
			}

		}
		
		//Approach Queue to Runway
		if( !planesApproaching.isEmpty() ) {
			//Assign planes less than 2 miles out a runway
			if(planesApproaching.peek().getDistance() < 2 ) {
				addToLeastBusyRunway();
			}
			//Send last plane to a runway
			if( (planeNum == (MAX_PLANES+1) ) ) {
				addToLeastBusyRunway();
			}
		}
		
		System.out.println("Simulation time (seconds):\t" + simTime);
		System.out.println("Runway processing time (seconds):\t" + runwayStorage[0].getLANDING_TIME_SEC() + "\n");
		printLine(45, "*");
		
		System.out.println("Planes Approaching:");
		for( Airplane plane : planesApproaching ) {
			System.out.print("\t" + plane.toString() + "\n");
		}
		System.out.println();
		
		printLine(45, "*");
		
		//Printing Runway Queues and Processing planes on runway
		setRunwaysEmpty(0);
		for( Runway runway : runwayStorage) {
			if( !runway.isEmpty() ) {
				if( simTime > 0 ) { 
					runway.setGlobalSimTime(simTime); 
				}
				if( (simTime > 0) && ((simTime - runway.getTimeOfFirstPlane()) % runway.getLANDING_TIME_SEC() == 0) ) {
					runway.planeProcessed();
				}
			}
			if( runway.isEmpty() && (simTime > 5)) {
				setRunwaysEmpty(getRunwaysEmpty() + 1);
			}
			runway.printWaitingQueue();
			System.out.println();
			printLine(45, "-");
		}
		System.out.println("Runways empty: " + getRunwaysEmpty());
		System.out.println("Runways avaliable: " + runwayStorage.length);
		
		//
		//Ends the simulation when all planes processed
		if( (getRunwaysEmpty() == runwayStorage.length) && planesApproaching.isEmpty() && (planeNum == MAX_PLANES+1) ) {
			clear();
			System.out.println("Finished simulation!"
					+ "\n\n"
					+ "Processed " + (planeNum - 1) + " planes in " + simTime  + " seconds.\n");
			System.out.println("Simulation settings and fun data:\n"
					+ "\tMax Planes: " + getMaxPlanes() + "\n"
					+ "\tSpawn Rate: " + getSpawnRate() + "\n"
					+ "\tEmergency Rate: " + getEmergencyRate() + "\n"
					+ "\tMax Spawn Distance: " + getMaxDistance() + "\n"
					+ "\tNumber of Emergencies: " + getnumEmergencyPlanesSpawned() + "\n");
			cancel();
		}
		
		//Increments every second.
		simTime++;
	}//end run()
	
	
	
	
	/**
	 * Conditional Logic to send plane in approaching queue to the least busy runway
	 */
	private void addToLeastBusyRunway() {
		int indexOfLeastBusy = 0;
		if( !planesApproaching.isEmpty() ) {
			for( int index = 0 ;  index < runwayStorage.length ; index++ ) {
				if( runwayStorage[index].numberInWait() < runwayStorage[indexOfLeastBusy].numberInWait() ) {
					indexOfLeastBusy = index;
				}
			}
			runwayStorage[indexOfLeastBusy].sendToRunway(planesApproaching.poll());
			if( !runwayStorage[indexOfLeastBusy].getTimeHasBeenSet() ) {
				runwayStorage[indexOfLeastBusy].setTimeOfFirstPlane(simTime);
				runwayStorage[indexOfLeastBusy].setTimeHasBeenSet(true);
			}
		}
	}
	
	
	
	
	//
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
	 * Value of 1 spawns a plane every iteration.
	 * @return double P
	 */
	public double getSpawnRate() {
		return SPAWN_RATE;
	}
	/**
	 * Set simulation settings for plane spawn rate.
	 * Values range from 0 to 1.
	 * Value of 1 spawns a plane every iteration.
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
	 * Value of 1 spawns an emergency plane every iteration.
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
	 * Get simulation settings for max distance to spawn plane from airport.
	 * Values range from 0 to 10.
	 * @return double P
	 */
	public double getMaxDistance() {
		return MAX_DISTANCE;
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
	 * Get the number of runways empty.
	 * @return Number of runways empty
	 */
	public int getRunwaysEmpty() {
		return runwaysEmpty;
	}
	
	/**
	 * Set the number of runways empty.
	 * @param runwaysEmpty Number of runways empty
	 */
	public void setRunwaysEmpty(int runwaysEmpty) {
		this.runwaysEmpty = runwaysEmpty;
	}
	/**
	 * Get the number of emergency planes spawned in the simulation.
	 * @return the numEmergencyPlanesSpawned
	 */
	public int getnumEmergencyPlanesSpawned() {
		return numEmergencyPlanesSpawned;
	}
	
	
	public static void printLine(int lineLength, String character) {
		for( int col = 0 ; col < lineLength ; col++ ) {
			System.out.print(character + " ");
		}
		System.out.println();
	}
	public static void printBlock(int blockWidth, int blockHeight, String character) {
		for( int row = 0 ; row < blockHeight ; row++ ) {
			for( int col = 0 ; col < blockWidth ; col++ ) {
				System.out.print(character + " ");
			}
			System.out.println();
		}
		System.out.println();
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
