/**
 * Application to test airport simulation.
 * @author Zachary Cowan
 * @version 11/1/22
 * Fall/2022
 */
public class Application {
	/**
	 * Main function for application.
	 * @param args Calls
	 * @throws MaxRunwayException Exception thrown if airport object tries to exceed 20.
	 */
	public static void main(String [] args ) throws MaxRunwayException {
	
		Airport airport = new Airport(21);
		airport.runSimulation();
	}
	
	
	
}
