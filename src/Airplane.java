import java.util.Timer;
import java.util.TimerTask;

/**
 * Airplane class for storing plane data and simulating distance.
 * @author Zachary Cowan
 * @version 11/1/22
 * Fall/2022
 */
public class Airplane extends TimerTask implements Comparable<Airplane> {
	
	//extends TimerTask

	private int planeId;
	private int milesFromAirport;
	private boolean isEmergency;
	private boolean hasArrived;
	
	private static Timer simClock;
	
	/**
	 * Default Airplane Constructor
	 */
	Airplane() {
		simClock = new Timer();
		simClock.schedule(this, 0, 2000);
		setEmergency(false);
		milesFromAirport = 5;
		hasArrived = false;
	}
	
	
	/**
	 * Decrements the plane distance every 2 seconds and updates corresponding plane data.
	 */
	@Override
	public void run() {
		if( (milesFromAirport > 0) ) {
			milesFromAirport--;;
		} else if( milesFromAirport == 0 ) {
			setHasArrived(true);
		}
		if( isEmergency() ) {
			setDistance(0);
		}
		
	}

	/**
	 * Get plane id
	 * @return The id of the plane
	 */
	public int getPlaneId() {
		return planeId;
	}
	
	/**
	 * Set the plane id
	 * @param num Id to set for plane
	 */
	public void setPlaneId(int num) {
		planeId = num;
	}
	
	/**
	 * String representation of plane.
	 * Gives plane id as well as information about flight: is emergency, is at airport, is x distance away.
	 */
	public String toString() {
		String message = "Plane " + String.valueOf(getPlaneId()) + " is " + String.valueOf(milesFromAirport) + " miles away.";
		if( hasArrived && !isEmergency ) {
			message = "Plane " + String.valueOf(getPlaneId()) + " is at airport.";
		} else if( isEmergency ) {
			message = "Plane " + String.valueOf(getPlaneId()) + " emergency landing";
		}
		return message;
	}

	/**
	 * Get the distance of plane from airport.
	 * @return distance
	 */
	public int getDistance() {
		return milesFromAirport;
	}

	/**
	 * Set the distance of the plane from airport
	 * @param distance Distance to set
	 */
	public void setDistance(int distance) {
		this.milesFromAirport = distance;
	}
	/**
	 * Decrements the planes distance from airport by 1 mile.
	 */
	public void decrementDistance() {
		this.milesFromAirport--;
	}


	/**
	 * Get the state of planes emergency
	 * @return True if plane is emergency plane
	 */
	public boolean isEmergency() {
		return isEmergency;
	}


	/**
	 * Set whether the plane is emergency plane
	 * @param isEmergency True if plane needs emergency landing
	 */
	public void setEmergency(boolean isEmergency) {
		this.isEmergency = isEmergency;
	}


	/**
	 * Get the state of the planes arrival
	 * @return True if planes distance is zero
	 */
	public boolean hasArrived() {
		return hasArrived;
	}
	
	/**
	 * Set the state of the planes arrival
	 * @param hasArrived True if plane has arrived
	 */
	public void setHasArrived(boolean hasArrived) {
		this.hasArrived = hasArrived;
	}


	/**
	 * Implementation of Comparable
	 * Based on distance from airport.
	 */
	@Override
	public int compareTo(Airplane o) {
		if( this.getDistance() < o.getDistance() ) {
			return -1;
		} else if( this.getDistance() > o.getDistance() ) {
			return 1;
		} else  {
			return 0;
		}
	}

	
}
