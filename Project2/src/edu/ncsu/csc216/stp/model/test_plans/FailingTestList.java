/**
 * 
 */
package edu.ncsu.csc216.stp.model.test_plans;

import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISwapList;

/**
 * Holds the methods relating to the list for the Failing Tests. Extends
 * AbstractTestPlan.
 * 
 * @author Veerendra Gottiveeti
 * @author Yi Zhang
 *
 */
public class FailingTestList extends AbstractTestPlan {

	/** SwapList containing TestResults */
	private ISwapList<TestCase> failingTestList;
	/** String Constant holding the name of the “Failing Tests” list. */
	public static final String FAILING_TEST_LIST_NAME = "Failing Tests";

	/**
	 * Constructs the FailingTestList with the expected name. (uses super due to
	 * extending with AbstractTestPlan)
	 */
	public FailingTestList() {

		super(FAILING_TEST_LIST_NAME);

		failingTestList = super.getTestCases();
	}

	/**
	 * Overrides the method to check that the TestCase is failing before adding to
	 * the end of the ISwapList. If the TestCase is not failing, the test is not
	 * added to the list and an IAE is thrown with the message “Cannot add passing
	 * test case.”.
	 * 
	 * @param t The test case to be added
	 * @throws IllegalArgumentException if the test is passing
	 */
	@Override
	public void addTestCase(TestCase t) {

		if ("PASS".equals(t.getStatus())) {
			throw new IllegalArgumentException("Cannot add passing test case.");
		} else {
			super.addTestCase(t);
		}
	}

	/**
	 * Overrides the method to ensure that the parameter value matches the expected
	 * name (case insensitive). If so, the name is set to the constant value.
	 * 
	 * @param testPlanName Name of the test plan
	 * @throws IllegalArgumentException if trying to edit failing test
	 */
	@Override
	public void setTestPlanName(String testPlanName) {

		String lowerCaseName = testPlanName.toLowerCase();
		String lowerCaseFailingTest = FAILING_TEST_LIST_NAME.toLowerCase();

		if (lowerCaseName.equals(lowerCaseFailingTest)) {
			super.setTestPlanName(testPlanName);
		} else {
			throw new IllegalArgumentException("The Failing Tests list cannot be edited.");
		}
	}

	/**
	 * Returns a 2D String array where the first column is the test case id, the
	 * second column is the test type, and the third column is the test plan name
	 * associated with the TestCase. If the test plan is null, then use empty string
	 * for the test plan name.
	 * 
	 * @return a a 2D String array where the first column is the test case id, the
	 *         second column is the test type, and the third column is the test plan
	 *         name associated with the TestCase
	 * @throws IllegalArgumentException if test is failing
	 */
	public String[][] getTestCasesAsArray() {

		String[][] testCases = new String[failingTestList.size()][3];

		for (int i = 0; i < failingTestList.size(); i++) {

			TestCase c = failingTestList.get(i);

			if ("FAIL".equals(c.getStatus())) {

				testCases[i][0] = c.getTestCaseId();
				testCases[i][1] = c.getTestType();

				// if the test plan is null, then use empty string for the test plan name
				if (failingTestList.get(i).getTestPlan() == null) {

					testCases[i][2] = "";

				} else {

					testCases[i][2] = failingTestList.get(i).getTestPlan().getTestPlanName();
				}
			} else {
				throw new IllegalArgumentException();
			}
		}
		return testCases;

	}

	/**
	 * Clears the FailingTestList of all TestCases.
	 */
	public void clearTests() {

		for (int i = 0; i < failingTestList.size(); i++) {
			while (failingTestList.size() > 0) {
				failingTestList.remove(i);
			}
		}
	}
}