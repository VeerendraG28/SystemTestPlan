/**
 * 
 */
package edu.ncsu.csc216.stp.model.tests;

/**
 * The TestResult class contains information about the results of a single
 * execution of a TestCase. The TestResult knows if it is passing or failing and
 * has details about the actual results from execution.
 * 
 * @author Veerendra Gottiveeti
 * @author Yi Zhang
 *
 */
public class TestResult {

	/** String to return when the test is passing */
	public static final String PASS = "PASS";
	/** String to return when the test is failing */
	public static final String FAIL = "FAIL";
	/** Boolean representing when test is passing */
	private boolean passing;
	/** String representing the actual results from a test plan */
	private String actualResults;

	/**
	 * Constructs a TestResult with the given parameter. Throws an IAE if
	 * actualResults is null or empty string.
	 * 
	 * @param passing       State of a test when it passes
	 * @param actualResults The actual results from a test plan
	 * @throws IllegalArgumentException if actualResults is null or empty string
	 */
	public TestResult(boolean passing, String actualResults) {

		setPassing(passing);

		setActualResults(actualResults);
	}

	/**
	 * Returns the actual results of the test plan
	 * 
	 * @return the actual results of the actual results of the test plan
	 */
	public String getActualResults() {
		return actualResults;
	}

	/**
	 * Sets the actual results of test plan. Private helper method to check for a
	 * valid actualResults string. Throws an IAE if null or empty string. The
	 * exception message is “Invalid test results.”.
	 * 
	 * @param actualResults The actual results from a test plan
	 * @throws IllegalArgumentException if actualResults is null or empty string
	 */
	private void setActualResults(String actualResults) {

		if (actualResults == null || "".equals(actualResults)) {
			throw new IllegalArgumentException("Invalid test results.");
		}

		this.actualResults = actualResults;

	}

	/**
	 * Returns the state of the test plan when it is passing
	 * 
	 * @return the state of the test plan when it is passing
	 */
	public boolean isPassing() {
		return passing;
	}

	/**
	 * Sets a test to a state where it either passes or fails
	 * 
	 * @param testValid Determines if a test passes or fails
	 */
	private void setPassing(boolean testValid) {

		if (testValid) {
			this.passing = true;
		} else {
			this.passing = false;
		}
	}

	/**
	 * Returns a string representation of the TestResult for printing to a file and
	 * listing in the GUI. If the test is passing, “PASS” is printed followed by a
	 * colon (:), a space, and the actual results. If the test is failing, “FAIL” is
	 * printed followed by a colon (:), a space, and the actual results.
	 * 
	 * @return the string representation of the TestResult for printing to a file
	 *         and listing in the GUI
	 */
	public String toString() {

		if (isPassing()) {
			return PASS + ":" + " " + actualResults;
		} else {
			return FAIL + ":" + " " + actualResults;
		}
	}

}