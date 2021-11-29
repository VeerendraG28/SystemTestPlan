/**
 * 
 */
package edu.ncsu.csc216.stp.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISortedList;
import edu.ncsu.csc216.stp.model.util.ISwapList;

/**
 * TestPlanWriter has one public method writeTestPlanFile() that receives a File
 * with the file name to write to and an ISortedList of TestPlans to write to
 * file. TestPlanWriter should use TestCase’s toString() method to create the
 * properly formatted output for a TestCase.
 * 
 * @author Veerendra Gottiveeti
 * @author Yi Zhang
 *
 */
public class TestPlanWriter {

	/**
	 * WriteTestPlanFile() receives a File with the file name to write to and an
	 * ISortedList of TestPlans to write to file. TestPlanWriter should use
	 * TestCase’s toString() method to create the properly formatted output for a
	 * TestCase.
	 * 
	 * @param fileName   Name of the file to write to
	 * @param sortedPlan an ISortedList of TestPlans to write to file
	 * @throws IllegalArgumentException if cannot write to file
	 */
	public static void writeTestPlanFile(File fileName, ISortedList<TestPlan> sortedPlan) {
		try {
			PrintWriter output = new PrintWriter(fileName);

			for (int i = 0; i < sortedPlan.size(); i++) {
				TestPlan item = sortedPlan.get(i);
				output.println("! " + item.getTestPlanName());
				ISwapList<TestCase> list = item.getTestCases();

				for (int j = 0; j < list.size(); j++) {
					output.println(list.get(j).toString());
					output.print(list.get(j).getActualResultsLog());
				}
			}
			output.close();
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to save file.");
		}
	}
}