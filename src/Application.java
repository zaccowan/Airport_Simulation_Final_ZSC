import java.util.Scanner;

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
		
		Airport airport = new Airport();
		try (Scanner scan = new Scanner(System.in)) {
			String response = "";
			System.out.println("Would you like to enter custom settings for airport simulation? Y / N ?");
			
			int userRunways = 0;
			int userMaxPlanes = 0;
			double userSpawnRate = 0.0;
			double userEmergencyRate = 0.0;
			int userMaxDistance = 0;
			
			response = scan.nextLine();
			if( response.equalsIgnoreCase("Y") || response.equalsIgnoreCase("yes")) {
				//User Enters custom simulation settings
				
				//Do while loop prevents out of bounds arguments from being entered
				do {
					System.out.println("Please enter number of runways to use between 1 and 20. Or 0 for default settings.");
					userRunways = scan.nextInt();
				} while ( userRunways > 20 );
				
				System.out.println("Please enter max number of planes to spawn. Or 0 for default settings.");
				userMaxPlanes = scan.nextInt();
				do {
					System.out.println("For spawn rates 1, results in the highest rate of spawn; zero never spawns.");
					System.out.println("Please enter spawn rate of planes between 0 and 1. Or 2 for default settings.");
					userSpawnRate = scan.nextDouble();
				} while( (userSpawnRate > 1) && (userSpawnRate != 2 ));
				
				do {
					System.out.println("For emergency rates 1, results in the highest rate of emegencies; zero never spawns.");
					System.out.println("Please enter emergency spawn rate between 0 and 1. Or 2 for default.");
					userEmergencyRate = scan.nextDouble();
				} while( (userSpawnRate > 1) && (userSpawnRate != 2 ));
				
				System.out.println("Please enter max distance to spawn plane from airport. Greater distance increases simulation time so be reasonable.");
				userMaxDistance = scan.nextInt();
				
				airport = new Airport(userRunways, userMaxPlanes, userSpawnRate, userEmergencyRate, userMaxDistance);
			} else {
				System.out.println("Running Simulation.");
			}
		}//End Try
		airport.runSimulation();
	}// end main
}// end class
