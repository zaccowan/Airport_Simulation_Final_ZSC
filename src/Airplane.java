import java.util.Timer;
import java.util.TimerTask;

public class Airplane extends TimerTask {

	private int planeId;
	private int milesFromAirport;
	
	static Timer simClock = new Timer();
	
	Airplane() {
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
		if( getDistance() == 0 ) {
			return "plane " + String.valueOf(getPlaneId());
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

	
}
