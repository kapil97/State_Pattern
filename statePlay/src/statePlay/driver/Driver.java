package statePlay.driver;
/**
 * @author Kamleshwar Ragava
 *
 */

public class Driver {
	/**
	 * Main Method
	 * Command line validation happens here
	 * takes input as command line arguments
	 * @param args
	 */
	public static void main(String[] args) {

		final int REQUIRED_NUMBER_OF_ARGS = 3;
		if ((args.length != REQUIRED_NUMBER_OF_ARGS) || 
				(args[0].equals("${inputNumStream}")) || 
				(args[1].equals("${runAvgWindowSize}")) || 
				(args[2].equals("${outputNumFile}"))) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_ARGS);
			System.exit(0);
		}

	}
	@Override
	public String toString(){
		String returnValue="Main Class";
		return returnValue;
	}
}
