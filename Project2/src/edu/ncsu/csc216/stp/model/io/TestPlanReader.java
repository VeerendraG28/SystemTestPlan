/**
 * 
 */
package edu.ncsu.csc216.stp.model.io;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc216.stp.model.test_plans.AbstractTestPlan;
import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.tests.TestResult;
import edu.ncsu.csc216.stp.model.util.ISortedList;
import edu.ncsu.csc216.stp.model.util.SortedList;

/**
 * TestPlanReader has one public method readTestPlansFile() that receives a File
 * with the file to read from. If the file cannot be loaded because it doesn’t
 * exist, the method will throw an IllegalArgumentException with the message
 * “Unable to load file.” Any invalid test plans or test cases (i.e., they
 * cannot be constructed or information is missing) are ignored.
 * 
 * @author Veerendra Gottiveeti
 * @author Yi Zhang
 *
 */
public class TestPlanReader {

	/**
	 * readTestPlansFile() is a method that receives a File with the file to read
	 * from. If the file cannot be loaded because it doesn’t exist, the method will
	 * throw an IllegalArgumentException with the message “Unable to load file.”
	 * 
	 * @param validFile valid file for test plans
	 * @return a sorted list of test plans and test cases
	 * @throws IllegalArgumentException if file cannot be processed
	 * @throws FileNotFoundException    if unable to load file
	 */
	public static ISortedList<TestPlan> readTestPlansFile(File validFile) {

		try {

			Scanner fileReader = new Scanner(new FileInputStream(validFile));

			SortedList<TestPlan> testPlans = new SortedList<TestPlan>(); // Create an empty array of Test Plans objects

			String string = "";

			while (fileReader.hasNextLine()) {
				string = string + fileReader.nextLine() + "\n";
			}

			// checks if the first letter of the file is proper !
			if (string.charAt(0) != '!') {
				throw new IllegalArgumentException("Unable to load file.");

			}
			Scanner scanner = new Scanner(string);
			// delimiter that separates the test plans
			scanner.useDelimiter("\\r?\\n?[!]");

			// uses the processProduct to read the file
			try {
				while (scanner.hasNext()) {

					TestPlan value = processTestPlan(scanner.next());

					// if the values match the requirements, add
					if (value != null) {
						testPlans.add(value);
					}
				}
			} catch (IllegalArgumentException e) {
				scanner.close();
				return testPlans;
				// empty catch block
			}

			// Close the Scanner b/c we're responsible with our file handles
			fileReader.close();
			scanner.close();
			// Return the SortedList with all the courses we read!
			return testPlans;
		}
		// if file not found, throw exception
		catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
	}

	/**
	 * Responsible for processing the Test Plan properties/informtion gathered from
	 * the readTestPlansFile() method This method uses delimiters \\r?\\n?[!] to
	 * break the line into test plans.
	 * 
	 * @param test Name of the test plan to process
	 * @return processed test plans, null if file cannot be processed
	 */

	private static TestPlan processTestPlan(String test) {

		String line = test.trim();

		if (line.charAt(0) == '#' || line.charAt(0) == '*') {
			return null;
		}

		Scanner scanner = new Scanner(line);

		scanner.useDelimiter("\\r?\\n?[#]");

		try {

			String testPlanName = scanner.nextLine().trim();

			TestPlan readTestPlan = new TestPlan(testPlanName);

			while (scanner.hasNext()) {
				try {
					TestCase testCase = processTest(readTestPlan, scanner.next());
					if (testCase != null) {
						readTestPlan.addTestCase(testCase);
					}
				} catch (Exception e) {
					// empty

				}
			}

			scanner.close();
			return readTestPlan;

		} catch (Exception e) {

			scanner.close();
			return null;
		}

	}

	/**
	 * Responsible for processing the Test Cases properties/informtion gathered from
	 * the processTestPlan() method This method uses delimiters \\r?\\n?[#] to break
	 * the line into test cases. \\r?\\n?[-] delimiter to break apart the string
	 * into the description and expected results and the actual results tokens. The
	 * first token contains the description and expected results that can be broken
	 * apart using the delimiter \\r?\\n?[*].
	 * 
	 * @param testPlan File to be processed
	 * @param test     Name of the test plan to process
	 * @return processed test cases, null if file cannot be processed
	 * @throws IllegalArgumentException if test result is in a wrong state
	 */

	private static TestCase processTest(AbstractTestPlan testPlan, String test) {

		String blank = test.trim();

		Scanner scanner = new Scanner(blank);

		scanner.useDelimiter(",");

		try {

			String testCaseId = scanner.next();

			scanner.useDelimiter("\\r?\\n?[*]");

			String testCaseType = scanner.next().trim().substring(1);

			String testDescription = scanner.next().trim().replaceAll("\r", "");

			scanner.useDelimiter("\\r?\\n?[-]");

			String expectedResults = scanner.next().trim().replaceAll("\r", "").substring(2);

			TestCase testCase = new TestCase(testCaseId, testCaseType, testDescription, expectedResults);

			while (scanner.hasNext()) {
				String result = scanner.next().trim();

				boolean passed;

				Scanner resultRead = new Scanner(result);
				resultRead.useDelimiter(":");
				String passTest = resultRead.next().trim();

				if (passTest.equals(TestResult.PASS)) {
					passed = true;
				} else if (passTest.equals(TestResult.FAIL)) {
					passed = false;
				} else {
					resultRead.close();
					throw new IllegalArgumentException();
				}

				String actualResults = resultRead.next().trim().replaceAll("\r", "");
				testCase.addTestResult(passed, actualResults);
				resultRead.close();
			}

			scanner.close();

			testPlan.addTestCase(testCase);

			return testCase;

		}

		catch (Exception e) {
			scanner.close();
			return null;
		}

	}
}