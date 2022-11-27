import java.util.Timer;
import java.util.TimerTask;

/**
 * Airplane class for storing plane data and simulating distance.
 * @author Zachary Cowan
 * @version 11/1/22
 * Fall/2022
 */
public class Airplane extends TimerTask {

	private int planeId;
	private int milesFromAirport;
	private boolean isEmergency;
	private boolean hasArrived;
	
	private static Timer simClock;
	
	/**
	 * Creates a default airplane
	 */
	Airplane() {
		simClock = new Timer();
		simClock.schedule(this, 0, 2000);
		setEmergency(false);
		milesFromAirport = 5;
		hasArrived = false;
	}
	
	
	/**
	 * Method that updates airplane distance.
	 */
	@Override
	public void run() {
		if( (milesFromAirport > 0) && !isEmergency ) {
			milesFromAirport--;;
		} else if( milesFromAirport == 0 ) {
			setHasArrived(true);
			cancel();
		}
		
	}

	/**
	 * Get the id assigned to the plane.
	 * @return The id of the plane.
	 */
	public int getPlaneId() {
		return planeId;
	}
	
	/**
	 * Get a string representation of the data of an airplane.
	 */
	public String toString() {
		String message = "plane " + String.valueOf(getPlaneId()) + " is " + String.valueOf(milesFromAirport) + " miles away.";
		if( hasArrived ) {
			message = "plane " + String.valueOf(getPlaneId()) + " is at airport.";
		} else if( isEmergency ) {
			message = "plane " + String.valueOf(getPlaneId()) + " emergency landing";
		}
		return message;
	}
	/**
	 * Set the plane id.
	 * @param num Id to give the plane.
	 */
	public void setPlaneId(int num) {
		planeId = num;
	}

	
	/**
	 * Get the distance of plane from the airport.
	 * @return The distance of the plane from airport.
	 */
	public int getDistance() {
		return milesFromAirport;
	}

	/**
	 * Set the distance of the plane from airport.
	 * @param distance Distance of plane from airport
	 */
	public void setDistance(int distance) {
		this.milesFromAirport = distance;
	}
	
	
	/**
	 * Decrements the distance from airport by unit of 1.
	 */
	public void decrementDistance() {
		this.milesFromAirport--;
	}


	/**
	 * See if plane is an emergency plane.
	 * @return True if plane is an emergency
	 */
	public boolean isEmergency() {
		return isEmergency;
	}


	
	/**
	 * Set the value of a planes emergency.
	 * @param isEmergency True if plane needs emergency landing.
	 */
	public void setEmergency(boolean isEmergency) {
		this.isEmergency = isEmergency;
	}


	/**
	 * Determine if the plane is at the airport.
	 * @return True if the planes distance is zero
	 */
	public boolean hasArrived() {
		return hasArrived;
	}
	
	
	/**
	 * Set if the plane has arrived or not.
	 * @param hasArrived True if distance is zero.
	 */
	private void setHasArrived(boolean hasArrived) {
		this.hasArrived = hasArrived;
	}

	
}
