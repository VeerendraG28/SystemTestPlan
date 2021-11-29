/**
 * 
 */
package edu.ncsu.csc216.stp.model.manager;

import java.io.File;

import edu.ncsu.csc216.stp.model.io.TestPlanReader;
import edu.ncsu.csc216.stp.model.io.TestPlanWriter;
import edu.ncsu.csc216.stp.model.test_plans.AbstractTestPlan;
import edu.ncsu.csc216.stp.model.test_plans.FailingTestList;
import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISortedList;
import edu.ncsu.csc216.stp.model.util.SortedList;

/**
 * A TestPlanManager has an ISortedList of TestPlans, one FailingTestList, an
 * AbstractTestPlan for the currentTestPlan, and a boolean flag that keeps track
 * of if the TestPlanManager has been changed since the last save.
 * 
 * @author Veerendra Gottiveeti
 * @author Yi Zhang
 */
public class TestPlanManager {

	/** Boolean isChanged represents if there is a update to the new test plan */
	private boolean isChanged;

	/** ISortedList of TestPlans */
	private ISortedList<TestPlan> testPlans;

	/** List that represents failing */
	private FailingTestList failingTestList;

	/** Current Test Plan */
	private AbstractTestPlan currentTestPlan;

	/** String Constant holding the name of the “Failing Tests” list. */
	public static final String FAILING_TEST_LIST_NAME = "Failing Tests";

	/**
	 * Constructor for the TestPlanManager testPlans field is constructed as a
	 * SortedList failingTestList is constructed and is set as currentTestPlan
	 * isChanged is set to false and clearTestPlans() is useful for completing these
	 * tasks
	 */
	public TestPlanManager() {
		clearTestPlans();

	}

	/**
	 * The TestPlanReader is used to load the file and return a list of potential
	 * testPlans to add to the list of TestPlans
	 * 
	 * @param testPlanFile testPlanFile of test plans
	 * @throws FileNotFoundException FileNotFoundException when file not found
	 * 
	 */
	public void loadTestPlans(File testPlanFile) {

		testPlans = TestPlanReader.readTestPlansFile(testPlanFile);

		setCurrentTestPlan(FAILING_TEST_LIST_NAME);

		isChanged = true;

	}

	/**
	 * Saves the current TestPlans to the given file. isChanged is updated to false.
	 * 
	 * @param testPlanFile testPlanFile of test plans
	 * @throws IOException IOException when not able to save
	 * 
	 */
	public void saveTestPlans(File testPlanFile) {

		TestPlanWriter.writeTestPlanFile(testPlanFile, testPlans);

		isChanged = false;

	}

	/**
	 * Checks if there is an update between the current Test Plan and new Test Plan
	 * 
	 * @return if the state if changed
	 */
	public boolean isChanged() {
		return isChanged;
	}

	/**
	 * Responsible for adding a test plan If the new TestPlan’s name is
	 * FAILING_TESTS_LIST_NAME or a duplicate of an existing TestPlan (both case
	 * insensitive), then an IAE is thrown with message “Invalid name.”. Otherwise,
	 * the TestPlan is added to the list of test plans, the current test plan is
	 * updated to the new test plan isChanged is updated to true.
	 * 
	 * @param testPlanName Name of the test plan
	 * @throws IllegalArgumentException if parameter is null or empty string
	 * @throws IllegalArgumentException if test plan name is failing tests (case
	 *                                  insensitive)
	 * @throws IllegalArgumentException if current test plan name is a duplicate of
	 *                                  an existing TestPlan (case insensitive)
	 */
	public void addTestPlan(String testPlanName) {

		TestPlan newTest = new TestPlan(testPlanName);

		if (testPlanName == null) {
			throw new IllegalArgumentException("Invalid name.");
		}
		String name = testPlanName.toLowerCase();
		if ("".equals(testPlanName) || FAILING_TEST_LIST_NAME.equals(name)) {
			throw new IllegalArgumentException("Invalid name.");
		}

		for (int i = 0; i < testPlans.size(); i++) {
			TestPlan c = testPlans.get(i);
			if (name.equals(c.getTestPlanName().toLowerCase())) {
				throw new IllegalArgumentException("Invalid name.");
			}
		}

		testPlans.add(newTest);

		setCurrentTestPlan(testPlanName);

		isChanged = true;

	}

	/**
	 * Returns a list of test plan names. The “Failing Tests” list is always listed
	 * first.
	 * 
	 * @return a list of test plan names
	 */
	public String[] getTestPlanNames() {

		String[] testPlanArray = new String[testPlans.size() + 1];
		// adds Failing Tests to the beginning of the list
		testPlanArray[0] = failingTestList.getTestPlanName();

		for (int i = 0; i < testPlans.size(); i++) {
			testPlanArray[i + 1] = testPlans.get(i).getTestPlanName();

		}
		return testPlanArray;

	}

	/**
	 * A private helper method that is useful for working with the FailingTestList.
	 * The order that TestCases are stored in the FailingTestList list is related to
	 * the order of the TestPlans and then the order of the failing TestCases in
	 * those TestPlans.
	 */
	private void getFailingTests() {

		failingTestList = new FailingTestList();

		for (int i = 0; i < testPlans.size(); i++) {
			for (int j = 0; j < testPlans.get(i).getTestCases().size(); j++) {
				if (!testPlans.get(i).getTestCase(j).isTestCasePassing()) {
					failingTestList.addTestCase(testPlans.get(i).getTestCase(j));
				}
			}
		}
	}

	/**
	 * Sets the currentTestPlan to the AbstractTestPlan with the given name.
	 * 
	 * @param testPlanName Name of the test plan
	 */
	public void setCurrentTestPlan(String testPlanName) {

		for (int i = 0; i < testPlans.size(); i++) {
			if (testPlans.get(i).getTestPlanName().equals(testPlanName)) {

				currentTestPlan = testPlans.get(i);
				return;
			}
		}
		getFailingTests();
		currentTestPlan = failingTestList;

	}

	/**
	 * Returns the currentTestPlan with the specific given name
	 * 
	 * @return currentTestPlan with the specific given name
	 */
	public AbstractTestPlan getCurrentTestPlan() {
		return currentTestPlan;
	}

	/**
	 * Responsible for editing a specific test plan. When editing the
	 * currentTestPlan and you cannot edit the TestPlan in place in the ISortedList.
	 * isChanged is updated to true.
	 * 
	 * @param testPlanName Name of the test plan
	 * @throws IllegalArgumentException if the currentTestPlan is an FailingTestList
	 * @throws IllegalArgumentException if the new name is Failing Tests (case
	 *                                  insensitive)
	 * @throws IllegalArgumentException if the new name is a duplicate of the name
	 *                                  of an existing testPlan (case insensitive)
	 */
	public void editTestPlan(String testPlanName) {
		if (currentTestPlan == failingTestList) {
			throw new IllegalArgumentException("The Failing Tests list may not be edited.");
		}

		if (FAILING_TEST_LIST_NAME.equals(testPlanName)) {
			throw new IllegalArgumentException("Invalid name.");
		}

		for (int i = 0; i < testPlans.size(); i++) {
			if (testPlans.get(i).getTestPlanName().equals(testPlanName)) {
				throw new IllegalArgumentException("Invalid name.");
			}
		}

		int idx = 0;
		for (int i = 0; i < testPlans.size(); i++) {
			if (testPlans.get(i).getTestPlanName().equals(currentTestPlan.getTestPlanName())) {
				idx = 1;
			}
		}

		TestPlan editor = testPlans.remove(idx);
		editor.setTestPlanName(testPlanName);
		testPlans.add(editor);
		isChanged = true;
		getFailingTests();

	}

	/**
	 * Responsible for removing a test plan. isChanged is updated to true.
	 * 
	 * @throws IllegalArgumentException if the current test plan is failing
	 */
	public void removeTestPlan() {

		if (currentTestPlan == failingTestList) {
			throw new IllegalArgumentException("The Failing Tests list may not be deleted.");
		}

		for (int i = 0; i < testPlans.size(); i++) {
			if (testPlans.get(i).equals(currentTestPlan)) {

				testPlans.remove(i);

				getFailingTests();

				currentTestPlan = failingTestList;

				isChanged = true;
			}
		}

	}

	/**
	 * A TestCase can only be added directly to a TestPlan. If the currentTestPlan
	 * is not a TestPlan do nothing with the TestCase. This is a place where the
	 * helper method getFailingTests() can be helpful. isChanged is updated to true.
	 * 
	 * @param t Test case to be added
	 */
	public void addTestCase(TestCase t) {

		String testPlanName = currentTestPlan.getTestPlanName().toLowerCase();

		if (testPlanName.equals(FAILING_TEST_LIST_NAME.toLowerCase())) {
			return;
		}

		currentTestPlan.addTestCase(t);

		isChanged = true;

	}

	/**
	 * Adds the test result to the test case at the given index in the current test
	 * plan. If the tests are failing, then the Failing Test List should be updated.
	 * 
	 * @param idx          Index of the current test plan
	 * @param passing      State of a test if it passes
	 * @param actualResult Actual result of a test.
	 */
	public void addTestResult(int idx, boolean passing, String actualResult) {

		currentTestPlan.addTestResult(idx, passing, actualResult);

		if (!passing) {
			failingTestList.addTestCase(currentTestPlan.getTestCase(idx));
		}

	}

	/**
	 * Clears out the TestPlanManager by setting testPlans to an empty SortedList,
	 * failingTestList to an empty FailingTestList(), currentTestPlan to the
	 * failingTestList. isChanged to false.
	 */
	public void clearTestPlans() {

		testPlans = new SortedList<TestPlan>();

		failingTestList = new FailingTestList();

		currentTestPlan = failingTestList;

		isChanged = false;

	}
}
