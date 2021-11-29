/**
 * 
 */
package edu.ncsu.csc216.stp.model.test_plans;

import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISwapList;
import edu.ncsu.csc216.stp.model.util.SwapList;

/**
 * AbstractTestPlan class is an abstract class at the top of the hierarchy for
 * test plans. The AbstractTestPlan knows its testPlanName and the ISwapList of
 * TestCases
 * 
 * @author Veerendra Gottiveeti
 * @author Yi Zhang
 *
 */
public abstract class AbstractTestPlan {

	/** String field for the name of a test plan */
	private String testPlanName;
	/** SwapList of TestCases */
	private ISwapList<TestCase> testCases;

	/**
	 * Sets the fields from the parameters and constructs a SwapList for the
	 * TestCases. An IAE is thrown with the message “Invalid name.” if the
	 * testPlanName is null or empty string.
	 * 
	 * @param testPlanName Name of the test plan
	 * @throws IllegalArgumentException if the testPlanName is null or empty string
	 */
	public AbstractTestPlan(String testPlanName) {

		setTestPlanName(testPlanName);

		testCases = new SwapList<TestCase>();

	}

	/**
	 * Sets the test plan name. An IAE is thrown with the message “Invalid name.” if
	 * the testPlanName is null or empty string.
	 * 
	 * @param testPlanName Name of the test plan
	 * @throws IllegalArgumentException if the testPlanName is null or empty string
	 */
	public void setTestPlanName(String testPlanName) {

		if (testPlanName == null || "".equals(testPlanName)) {
			throw new IllegalArgumentException("Invalid name.");
		}

		this.testPlanName = testPlanName;
	}

	/**
	 * Returns the name of the test plan
	 * 
	 * @return the name of the test plan
	 */
	public String getTestPlanName() {
		return testPlanName;
	}

	/**
	 * Returns the test cases
	 * 
	 * @return the test cases
	 */

	public ISwapList<TestCase> getTestCases() {
		return testCases;

	}

	/**
	 * Adds the TestCase to the end of the list. Note that any exceptions from the
	 * list should be thrown out of the method
	 * 
	 * @param t The test case to be added
	 * @throws NullPointerException if the test case is null
	 */

	public void addTestCase(TestCase t) {

		// Check null element
		if (t == null) {
			throw new NullPointerException();
		}

		testCases.add(t);

	}

	/**
	 * Removes the TestCase from the list of test cases and returns the removed test
	 * case. Note that any exceptions from the list should be thrown out of the
	 * method
	 * 
	 * @param index Index of the list
	 * @return the removed test case at given index
	 * @throws IndexOutOfBoundsException if index is out of bound
	 */
	public TestCase removeTestCase(int index) {

		// Check valid index
		if (index < 0 || index > testCases.size()) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}

		return testCases.remove(index);
	}

	/**
	 * Returns the test case
	 * 
	 * @param index Index of the list
	 * @return the element at given index
	 * @throws IndexOutOfBoundsException if index is out of bound
	 */
	public TestCase getTestCase(int index) {

		// Check valid index
		if (index < 0 || index >= testCases.size()) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}

		return testCases.get(index);
	}

	/**
	 * Counts the number of TestCases that are failing. Remember that TestCases have
	 * a method to tell you if they are passing or failing.
	 * 
	 * @return the number of TestCases that are failing
	 */
	public int getNumberOfFailingTests() {

		int counter = 0;

		for (int i = 0; i < testCases.size(); i++) {
			if (!testCases.get(i).isTestCasePassing()) {
				counter++;
			}
		}

		return counter;
	}

	/**
	 * Sends the test result parameters to the TestCase at the given index. Note
	 * that any exceptions from the list should be thrown out of the method
	 * 
	 * @param idx           idx of the list
	 * @param passing       State of a test if it passes
	 * @param actualResults Actual result of a test
	 * @throws IndexOutOfBoundsException if index is out of bound
	 * @throws IllegalArgumentException  if actual results is null or empty string
	 */
	public void addTestResult(int idx, boolean passing, String actualResults) {

		// Check valid index
		if (idx < 0 || idx > testCases.size()) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}

		// Check null element
		if (actualResults == null || "".equals(actualResults)) {
			throw new IllegalArgumentException();
		}

		testCases.get(idx).addTestResult(passing, actualResults);
	}

	/**
	 * An abstract method that returns a 2D String array.
	 * 
	 * @return a 2D String array of test cases
	 */
	public abstract String[][] getTestCasesAsArray();

	/**
	 * Returns the hash code of the test plan
	 * 
	 * @return the hash code of the test plan
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((testPlanName == null) ? 0 : testPlanName.hashCode());
		return result;
	}

	/**
	 * Checks if two Test Plan objects are the same
	 * 
	 * @return true if two objects are the same, otherwise return false
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractTestPlan other = (AbstractTestPlan) obj;
		if (testPlanName == null) {
			if (other.testPlanName != null)
				return false;
		} else if (!testPlanName.equalsIgnoreCase(other.testPlanName))
			return false;
		return true;
	}

}