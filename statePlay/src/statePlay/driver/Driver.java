package statePlay.driver;

import statePlay.states.BudgetStateI;
import statePlay.states.ContextState;
import statePlay.states.ContextStateI;
import statePlay.util.*;

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

		ValidatorI validator=new Validator(args[0],args[1],args[2]);
		if(validator.valid()) {
			ContextStateI contextState = new ContextState();
			BudgetStateI budgetState=new ContextState();

			RunningAverageI runningAverage = new RunningAverage(args[2]);
			/**
			 * Processing Available item file.
			 */
			try {
				FileProcessorI fileProcessor = new FileProcessor(args[0]);
				String line = fileProcessor.poll();
				while (line != null) {
					contextState.categorizeItems(line);
					line = fileProcessor.poll();

				}
				fileProcessor.close();

			} catch (IOException | InvalidPathException e) {
				e.printStackTrace();
			}

			/**
			 * Processing Input File.
			 */
			try {
				FileProcessorI fileProcessor = new FileProcessor(args[1]);
				String line = fileProcessor.poll();
				while (line != null) {
					String finalLine = line;
					ValidatorI formatValidator=new ValidatorI() {
						boolean isValid;
						@Override
						public boolean valid() {
							if(finalLine.contains("item:")) {
								isValid=true;
							}
							else isValid= finalLine.contains("money:");
							return isValid;
						}

					};
					if(formatValidator.valid()) {
						if (line.contains("money")) {
							int index = line.indexOf(':');
							String money = line.substring(index + 1);
							double moneyValue = Double.parseDouble(money);
							ValidatorI moneyValidator = new ValidatorI() {
								@Override
								public boolean valid() {
									if (moneyValue <= 0) {
										System.out.println("Money earned cannot be negative or Zero");
										return false;
									}
									return true;
								}
							};
							if (moneyValidator.valid())
								runningAverage.update(moneyValue);
							else return;
						}
						else budgetState.purchaseActionPerformed(line);
					}
					else {
						System.out.println("Incorrect Format. Format must be either item:<item name> or money:<money>");
						System.exit(0);
					}
					line = fileProcessor.poll();
				}
				fileProcessor.close();

			} catch (IOException | InvalidPathException e) {
				e.printStackTrace();
			}

			ResultProcessorI resultProcessor = new ResultProcessor(args[3]);
			resultProcessor.writeToFile();
		}
		else System.exit(0);
	}
	@Override
	public String toString(){
		String returnValue="Driver";
		return returnValue;
	}
}
