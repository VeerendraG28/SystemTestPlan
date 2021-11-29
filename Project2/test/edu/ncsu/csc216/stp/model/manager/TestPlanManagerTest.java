/**
 * 
 */
package edu.ncsu.csc216.stp.model.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * Tests the TestPlanManager class
 * 
 * @author Veerendra Gottiveeti
 * @author Yi Zhang
 */
class TestPlanManagerTest {

	/** Valid course records */
	private final File validTestFile = new File("test-files/test-plans1.txt");
	/** Valid course records */
	private final File validTestFileThree = new File("test-files/test-plans0.txt");
	/** Valid course records */
	private final File validTestFileTwo = new File("test-files/test-plans9.txt");
	/** String Constant holding the name of the “Failing Tests” list. */
	public static final String FAILING_TEST_LIST_NAME = "Failing Tests";
	/** Id of the test case */
	private static final String ID = "WolfScheduler";
	/** Type of the test case */
	private static final String TYPE = "Requirements";
	/** Description of the test case */
	private static final String DESCRIPTION = "test description";
	/** Expected results of the test case */
	private static final String EXPECTED_RESULTS = "Pass: pass";
	/** Boolean representation when test is failing */
	private static final Boolean FAILING = false;
	/** String to return when the test is passing */
	private static final String FAILING_RESULTS = "FAIL";

	/**
	 * Test method for TestPlanManager constructor
	 */
	@Test
	void testTestPlanManager() {
		TestPlanManager test = new TestPlanManager();
		assertFalse(test.isChanged());
	}

	/**
	 * Test method for loadTestPlans()
	 */
	@Test
	void testLoadTestPlans() {
		TestPlanManager test = new TestPlanManager();
		test.loadTestPlans(validTestFileThree);
		assertEquals(FAILING_TEST_LIST_NAME, test.getCurrentTestPlan().getTestPlanName());
	}

	/**
	 * Test method for saveTestPlans()
	 */
	@Test
	void testSaveTestPlans() {

		TestPlanManager sd = new TestPlanManager();

		// Add a student.
		sd.addTestPlan("hello");
		assertEquals(2, sd.getTestPlanNames().length);
		sd.saveTestPlans(validTestFileTwo);

	}

	/**
	 * Test method for addTestPlan()
	 */
	@Test
	void testAddTestPlan() {
		TestPlanManager test = new TestPlanManager();
		assertEquals(0, test.getCurrentTestPlan().getNumberOfFailingTests());
		test.addTestPlan(ID);

		assertEquals("WolfScheduler", test.getCurrentTestPlan().getTestPlanName());
		assertEquals(2, test.getTestPlanNames().length);
		test.addTestPlan("Test");
		test.addTestPlan("Test2");
		assertEquals(4, test.getTestPlanNames().length);

		try {
			TestPlanManager test2 = new TestPlanManager();
			test2.addTestPlan(FAILING_TEST_LIST_NAME);
			fail();
		} catch (Exception e) {
			// empty
		}

		try {
			TestPlanManager test2 = new TestPlanManager();
			test2.addTestPlan("");
			fail();
		} catch (Exception e) {
			// empty
		}

	}

	/**
	 * Tests TestPlanManager.loadTestPlans().
	 */
	@Test
	public void testLoadPlansFromFile() {
		TestPlanManager planManager = new TestPlanManager();

		// Test valid file
		planManager.loadTestPlans(validTestFile);
		assertEquals(3, planManager.getTestPlanNames().length);

	}

	/**
	 * Test method for getTestPlanNames()
	 */
	@Test
	void testGetTestPlanNames() {
		TestPlanManager test = new TestPlanManager();
		test.addTestPlan(ID);

		assertEquals("WolfScheduler", test.getCurrentTestPlan().getTestPlanName());
		assertEquals(2, test.getTestPlanNames().length);
		test.addTestPlan("Test");
		test.addTestPlan("Test2");
		assertEquals(4, test.getTestPlanNames().length);
		assertEquals("Test2", test.getCurrentTestPlan().getTestPlanName());

		String[] testPlanNames = test.getTestPlanNames();

		assertEquals(4, testPlanNames.length);

		assertEquals("Failing Tests", testPlanNames[0]);
		assertEquals(FAILING_TEST_LIST_NAME, testPlanNames[0]);
		assertEquals("Test", testPlanNames[1]);
		assertEquals("Test2", testPlanNames[2]);
		assertEquals(ID, testPlanNames[3]);

	}

	/**
	 * Test method for getCurrentTestPlan()
	 */
	@Test
	void testGetCurrentTestPlan() {
		TestPlanManager test = new TestPlanManager();
		test.addTestPlan(ID);

		assertEquals(ID, test.getCurrentTestPlan().getTestPlanName());
	}

	/**
	 * Test method for editTestPlan()
	 */
	@Test
	void testEditTestPlan() {
		TestPlanManager test = new TestPlanManager();

		Exception e1 = assertThrows(IllegalArgumentException.class, () -> test.editTestPlan(FAILING_TEST_LIST_NAME));
		assertEquals("The Failing Tests list may not be edited.", e1.getMessage());

	}

	/**
	 * Test method for removeTestPlan()
	 */
	@Test
	void testRemoveTestPlan() {
		TestPlanManager test = new TestPlanManager();
		test.addTestPlan(ID);

		assertEquals("WolfScheduler", test.getCurrentTestPlan().getTestPlanName());
		assertEquals(2, test.getTestPlanNames().length);
		test.addTestPlan("Test");
		test.addTestPlan("Test2");
		test.removeTestPlan();
		assertEquals(3, test.getTestPlanNames().length);

		test.setCurrentTestPlan(DESCRIPTION);
		assertEquals(FAILING_TEST_LIST_NAME, test.getCurrentTestPlan().getTestPlanName());
	}

	/**
	 * Test method for addTestCase()
	 */
	@Test
	void testAddTestCase() {

		TestPlanManager test = new TestPlanManager();
		test.addTestPlan(ID);

		TestCase testCase = new TestCase(ID, TYPE, DESCRIPTION, EXPECTED_RESULTS);
		testCase.addTestResult(FAILING, FAILING_RESULTS);

		test.addTestCase(testCase);
		test.addTestResult(0, false, DESCRIPTION);

		assertEquals("WolfScheduler", test.getCurrentTestPlan().getTestPlanName());

		assertEquals(2, test.getTestPlanNames().length);

		test.addTestPlan("Test");

		test.addTestPlan("Test2");

		assertEquals(4, test.getTestPlanNames().length);

		assertEquals("Test2", test.getCurrentTestPlan().getTestPlanName());

		try {
			test.editTestPlan(FAILING_TEST_LIST_NAME);
		} catch (Exception e) {
			// empty
		}

		try {
			test.editTestPlan("Test");
		} catch (Exception e) {
			// empty
		}

	}

	/**
	 * Test method for clearTestPlans()
	 */
	@Test
	void testClearTestPlans() {
		TestPlanManager test = new TestPlanManager();
		test.addTestPlan(ID);

		assertEquals("WolfScheduler", test.getCurrentTestPlan().getTestPlanName());
		assertEquals(2, test.getTestPlanNames().length);

		test.clearTestPlans();
		assertEquals(1, test.getTestPlanNames().length);

	}

}