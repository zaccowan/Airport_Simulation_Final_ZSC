
public class Airplane {

	private int planeId = 1;

	public int getPlaneId() {
		return planeId;
	}
	
	public String toString() {
		return "plane " + String.valueOf(getPlaneId());
	}
	public void setPlaneId(int num) {
		planeId = num;
	}
}
