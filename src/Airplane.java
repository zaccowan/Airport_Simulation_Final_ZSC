
public class Airplane {

	private int planeId;
	private double milesFromAirport = 5;

	public int getPlaneId() {
		return planeId;
	}
	
	public String toString() {
		return "plane " + String.valueOf(getPlaneId());
	}
	public void setPlaneId(int num) {
		planeId = num;
	}

	public double getDistance() {
		return milesFromAirport;
	}

	public void decrementDistance(double decrementValue) {
		this.milesFromAirport = getDistance() - decrementValue;
	}
}
