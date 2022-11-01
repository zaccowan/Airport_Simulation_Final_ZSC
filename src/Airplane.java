import java.util.Timer;
import java.util.TimerTask;

public class Airplane extends TimerTask {

	private int planeId;
	private int milesFromAirport;
	private boolean isEmergency;
	
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
		}
		
	}

	public int getPlaneId() {
		return planeId;
	}
	
	public String toString() {
		if( (getDistance() == 0) && (!isEmergency) ) {
			return "plane " + String.valueOf(getPlaneId());
		} 
		if( isEmergency ) {
			return "plane " + String.valueOf(getPlaneId()) + " emergency landing";
		}
		
		return "plane " + String.valueOf(getPlaneId()) + " is " + String.valueOf(milesFromAirport) + " miles away.";
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

	
}
