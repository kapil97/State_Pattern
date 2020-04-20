package statePlay.driver;

import statePlay.states.BudgetStateI;
import statePlay.states.ContextState;
import statePlay.util.FileProcessor;
import statePlay.util.FileProcessorI;
import statePlay.util.RunningAverage;
import statePlay.util.RunningAverageI;

import java.io.IOException;
import java.nio.file.InvalidPathException;

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

		final int REQUIRED_NUMBER_OF_ARGS = 4;
		if ((args.length != REQUIRED_NUMBER_OF_ARGS)||
				(args[0].equals("${baseFile}")) ||
				(args[1].equals("${inputFile}")) ||
				(args[2].equals("${runAvgWindowSize}"))||
				(args[3].equals("${outputFile}"))) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments. Provided: %d " , REQUIRED_NUMBER_OF_ARGS, args.length );
			System.exit(0);
		}
		ContextState contextState= new ContextState();
		RunningAverageI runningAverage=new RunningAverage(args[2]);

		try {
			FileProcessorI fileProcessor=new FileProcessor(args[0]);
			String line= fileProcessor.poll();
			while (line != null) {
				contextState.categorizeItems(line);
				line = fileProcessor.poll();

			}
			fileProcessor.close();

		} catch (IOException | InvalidPathException e) {
			e.printStackTrace();
		}
		contextState.printList();

		try {
			FileProcessorI fileProcessor=new FileProcessor(args[1]);
			String line= fileProcessor.poll();
			//contextState.insertCategories(line);
			while (line != null) {
				if(line.contains("money")){
					int index=line.indexOf(':');
					String money=line.substring(index+1);
					double moneyValue=Double.parseDouble(money);
					runningAverage.update(moneyValue);
				}
				else contextState.purchaseActionPerformed(line);
				line = fileProcessor.poll();
			}
			fileProcessor.close();

		} catch (IOException | InvalidPathException e) {
			e.printStackTrace();
		}

	}
	@Override
	public String toString(){
		String returnValue="Main Class";
		return returnValue;
	}
}
