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
	private boolean hasArrived = false;
	
	static Timer simClock = new Timer();
	
	Airplane() {
		setEmergency(false);
		milesFromAirport = 5;
		simClock.schedule(this, 0, 2000);
	}
	
	
	@Override
	public void run() {
		if( milesFromAirport > 0 ) {
			milesFromAirport--;;
		} else {
			setHasArrived(true);
		}
		
	}

	public int getPlaneId() {
		return planeId;
	}
	
	public String toString() {
		String message = "plane " + String.valueOf(getPlaneId()) + " is " + String.valueOf(milesFromAirport) + " miles away.";
		if( hasArrived ) {
			message = "plane " + String.valueOf(getPlaneId()) + " is at airport.";
		} else if( isEmergency ) {
			message = "plane " + String.valueOf(getPlaneId()) + " emergency landing";
		}
		return message;
	}
	public void setPlaneId(int num) {
		planeId = num;
	}

	public int  getDistance() {
		return milesFromAirport;
	}

	public void setDistance(int distance) {
		this.milesFromAirport = distance;
	}


	public boolean isEmergency() {
		return isEmergency;
	}


	public void setEmergency(boolean isEmergency) {
		this.isEmergency = isEmergency;
	}


	public boolean hasArrived() {
		return hasArrived;
	}


	private void setHasArrived(boolean hasArrived) {
		this.hasArrived = hasArrived;
	}

	
}
