/**
 * 
 */
package edu.ncsu.csc216.stp.model.test_plans;

import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISwapList;

/**
 * Responsible for generating and dealing with various implementations of a test
 * plan. Extends AbstractTestPlan and implements the Comparable interface.
 * 
 * @author Veerendra Gottiveeti
 * @author Yi Zhang
 *
 */
public class TestPlan extends AbstractTestPlan implements Comparable<TestPlan> {

	/** SwapList containing TestResults */
	private ISwapList<TestCase> testCase;

	/**
	 * Constructs the TestPlan with the given name. If the proposed name is the same
	 * as FailingListTest.FAILING_TEST_LIST_NAME (case insensitive), then throw an
	 * IAE with message “Invalid name.”.
	 * 
	 * @param testPlanName Name of the test plan
	 * @throws IllegalArgumentException if the proposed name is Failing Tests
	 */
	public TestPlan(String testPlanName) {

		super(testPlanName);

		if (testPlanName.equalsIgnoreCase(FailingTestList.FAILING_TEST_LIST_NAME)) {
			throw new IllegalArgumentException("Invalid name.");
		}

		testCase = super.getTestCases();

	}

	/**
	 * Returns a 2D String array where the first column is the test case id, the
	 * second column is the test type, and the third column is the status (“PASS” or
	 * “FAIL”).
	 * 
	 * @return testCases 2d Array where the first column is the test case id, the
	 *         second column is the test type, and the third column is the status
	 */
	public String[][] getTestCasesAsArray() {

		String[][] testCases = new String[testCase.size()][3];

		for (int i = 0; i < testCase.size(); i++) {

			TestCase c = testCase.get(i);

			testCases[i][0] = c.getTestCaseId();
			testCases[i][1] = c.getTestType();
			testCases[i][2] = c.getStatus();
		}

		return testCases;
	}

	/**
	 * Override the AbstractTestPlan.addTestCase() method so that it adds the test
	 * case via call to super AND sets the TestCase’s TestPlan to the current
	 * TestPlan.
	 * 
	 * @param t The test case to be added
	 */

	@Override
	public void addTestCase(TestCase t) {
		t.setTestPlan(this);

		super.addTestCase(t);

	}

	/**
	 * Compares the names of the TestPlans. This comparison is case insensitive.
	 * 
	 * @param s Represents a test plan
	 * @return the comparison result
	 * @throws IllegalArgumentException is the test plan is null
	 */
	public int compareTo(TestPlan s) {

		if (s == null) {
			throw new IllegalArgumentException();
		}

		if (super.getTestPlanName().compareToIgnoreCase(s.getTestPlanName()) < 0) {
			return -1;
		} else if (super.getTestPlanName().compareToIgnoreCase(s.getTestPlanName()) > 0) {
			return 1;
		}

		return 0;

	}

}