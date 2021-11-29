/**
 * 
 */
package edu.ncsu.csc216.stp.model.tests;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.util.ILog;
import edu.ncsu.csc216.stp.model.util.Log;

/**
 * The TestCase class contains the information about each individual test case
 * including the testCaseId, testType, testDescription, expectedResults, and
 * ILog of TestResults. The TestCase additionally contains a TestPlan that the
 * TestCase belongs to.
 * 
 * @author Veerendra Gottiveeti
 * @author Yi Zhang
 *
 */
public class TestCase {

	/** Test Id for a Test Case */
	private String testCaseId;
	/** Test Type for a Test Case */
	private String testType;
	/** Test Description for a Test Case */
	private String testDescription;
	/** Expected Results for a Test Case */
	private String expectedResults;
	/** Test plan for an object Test Plan */
	private TestPlan testPlans;
	/** Log of TestResults */
	private ILog<TestResult> testResults;

	/**
	 * Constructs the TestCase with the given parameters. The testResults field is
	 * constructed to an empty Log of TestResults. The testPlan is set to null.
	 * 
	 * @param testCaseId      Id of the test case
	 * @param testType        Type of the test case
	 * @param testDescription Description of the test case
	 * @param expectedResults Expected results of the test case
	 * @throws IllegalArgumentException if testCaseId is null or empty string
	 * @throws IllegalArgumentException if testType is null or empty string
	 * @throws IllegalArgumentException if testDescription is null or empty string
	 * @throws IllegalArgumentException if expectedResults is null or empty string
	 */
	public TestCase(String testCaseId, String testType, String testDescription, String expectedResults) {

		testResults = new Log<TestResult>();

		setTestCaseId(testCaseId);

		setTestType(testType);

		setTestDescription(testDescription);

		setExpectedResults(expectedResults);

		testPlans = null;
	}

	/**
	 * Returns the id of the test case.
	 * 
	 * @return the string form of the id of the test case
	 */
	public String getTestCaseId() {
		return this.testCaseId;
	}

	/**
	 * Sets the id of the test case, method throws an IAE if null or empty string.
	 * 
	 * @param testCaseId Id of the test case
	 * @throws IllegalArgumentException if testCaseId is null or empty string
	 */
	private void setTestCaseId(String testCaseId) {

		if (testCaseId == null || "".equals(testCaseId)) {
			throw new IllegalArgumentException("Invalid test information.");
		}

		this.testCaseId = testCaseId;
	}

	/**
	 * Returns the type of the test case.
	 * 
	 * @return string form of the type of the test case
	 */
	public String getTestType() {
		return this.testType;
	}

	/**
	 * Sets the type of the test case, method throws an IAE if null or empty string.
	 * 
	 * @param testType Type of the test case
	 * @throws IllegalArgumentException if testType is null or empty string
	 */
	private void setTestType(String testType) {
		if (testType == null || "".equals(testType)) {
			throw new IllegalArgumentException("Invalid test information.");
		}

		this.testType = testType;
	}

	/**
	 * Returns the description of the test case.
	 * 
	 * @return the string form of the description of the test case
	 */
	public String getTestDescription() {
		return this.testDescription;
	}

	/**
	 * Sets the description of the test case, method throws an IAE if null or empty
	 * string.
	 * 
	 * @param testDescription Description of the test case
	 * @throws IllegalArgumentException if testDescription is null or empty string
	 */
	private void setTestDescription(String testDescription) {

		if (testDescription == null || "".equals(testDescription)) {
			throw new IllegalArgumentException("Invalid test information.");
		}

		this.testDescription = testDescription;
	}

	/**
	 * Returns the expected results of the test case.
	 * 
	 * @return string form of the expected results of the test case
	 */
	public String getExpectedResults() {
		return this.expectedResults;
	}

	/**
	 * Sets the expected result of the test case, method throws an IAE if null or
	 * empty string.
	 * 
	 * @param expectedResults Expected results of the test case
	 * @throws IllegalArgumentException if expectedResults is null or empty string
	 */
	private void setExpectedResults(String expectedResults) {

		if (expectedResults == null || "".equals(expectedResults)) {
			throw new IllegalArgumentException("Invalid test information.");
		}

		this.expectedResults = expectedResults;
	}

	/**
	 * Creates a TestResult from the given values and adds the TestResult to the end
	 * of the testResults log. This method throws an IAE if the TestResult cannot be
	 * constructed.
	 * 
	 * @param passing    State of a test if it passes
	 * @param testResult Test results of a test
	 * @throws IllegalArgumentException if the TestResult cannot be constructed
	 */
	public void addTestResult(boolean passing, String testResult) {

		try {
			TestResult test = new TestResult(passing, testResult);
			this.testResults.add(test);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid test results.");
		}

	}

	/**
	 * Returns true if the last TestResult in the Log is passing. If there are no
	 * TestResults in the Log the test is considered failing since it has not yet
	 * run and false is returned.
	 * 
	 * @return false if the test is failing or there are no TestResults in the Log,
	 *         otherwise return true
	 */
	public boolean isTestCasePassing() {

		if (testResults.size() == 0) {
			return false;
		}

		TestResult lastTestResult = testResults.get(testResults.size() - 1);

		return lastTestResult.isPassing();
	}

	/**
	 * Returns the status of the TestCase as “PASS” or “FAIL”.
	 * 
	 * @return the status of the TestCase as “PASS” or “FAIL”.
	 */
	public String getStatus() {

		if (isTestCasePassing()) {
			return "PASS";
		}

		return "FAIL";
	}

	/**
	 * Returns a string representation of the testResults Log. A leading “- “ is
	 * added to the start of each TestResult and a “\n” is added to the end.
	 * 
	 * @return a string representation of the testResults Log
	 */
	public String getActualResultsLog() {
		String stringLog = "";
		for (int i = 0; i < testResults.size(); i++) {
			stringLog = stringLog + "- " + testResults.get(i).toString() + "\n";
		}
		return stringLog;
	}

	/**
	 * Sets the testPlan field to the given TestPlan. If the parameter is null, an
	 * IAE is thrown with the message “Invalid test plan.”.
	 * 
	 * @param testPlan Test plan for an object Test Plan
	 * @throws IllegalArgumentException if testPlan is null
	 */
	public void setTestPlan(TestPlan testPlan) {

		if (testPlan == null) {
			throw new IllegalArgumentException("Invalid test plan.");
		}

		this.testPlans = testPlan;
	}

	/**
	 * Returns the test plan
	 * 
	 * @return the test plans
	 */
	public TestPlan getTestPlan() {
		return this.testPlans;
	}

	/**
	 * Returns a string representation of the TestCase for printing to a file.
	 * 
	 * @return a string representation of the TestCase for printing to a file
	 */
	public String toString() {
		return "# " + getTestCaseId() + "," + getTestType() + "\n" + "* " + getTestDescription() + "\n" + "* "
				+ getExpectedResults();
	}

}