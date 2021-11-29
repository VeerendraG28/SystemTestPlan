/**
 * 
 */
package edu.ncsu.csc216.stp.model.test_plans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * Tests the TestPlan class.
 * 
 * @author Veerendra Gottiveeti
 * @author Yi Zhang
 */
class TestPlanTest {
	/** name of the test plan */
	private static final String TEST_PLAN = "test plan name";
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
	/** String to return when the test is passing */
	public static final String PASS = "PASS";
	/** String to return when the test is failing */
	public static final String FAIL = "FAIL";
	/** String to return when the test is passing */
	private static final String PASSING_RESULTS = "PASS";
	/** Boolean representation when test is passing */
	private static final Boolean PASSING = true;



	/**
	 * Test method for TestPlan constructor
	 */
	@Test
	void testTestPlan() {
		TestPlan test = new TestPlan(TEST_PLAN);
		assertEquals(TEST_PLAN, test.getTestPlanName());

		Exception e1 = assertThrows(IllegalArgumentException.class, () -> new TestPlan(FAILING_TEST_LIST_NAME));
		assertEquals("Invalid name.", e1.getMessage());
	}

	/**
	 * Test method for getTestCasesAsArray()
	 */
	@Test
	void testGetTestCasesAsArray() {
		
		TestPlan planTest = new TestPlan(TEST_PLAN); 
				
		TestCase test2 = new TestCase(ID, TYPE, DESCRIPTION, EXPECTED_RESULTS);
		
		
		test2.addTestResult(PASSING, PASSING_RESULTS);
		
		assertEquals(PASSING, test2.isTestCasePassing());

		planTest.addTestCase(test2);
		
		assertEquals(1, planTest.getTestCases().size());
		
		assertEquals(ID, planTest.getTestCase(0).getTestCaseId());
		assertEquals(TYPE, planTest.getTestCase(0).getTestType());
		assertEquals(PASS, planTest.getTestCase(0).getStatus()); 

		String[][] caseDirectory = planTest.getTestCasesAsArray();
		
		assertEquals(1, caseDirectory.length);
		
		assertEquals(ID, caseDirectory[0][0]);
		assertEquals(TYPE, caseDirectory[0][1]);
		assertEquals(PASS, caseDirectory[0][2]);
		
	}

	/**
	 * Test method for addTestCase()
	 */
	@Test
	void testAddTestCase() {
		TestPlan test = new TestPlan(TEST_PLAN);

		TestCase test2 = new TestCase(ID, TYPE, DESCRIPTION, EXPECTED_RESULTS);

		test.addTestCase(test2);

		assertEquals("# WolfScheduler,Requirements\n* test description\n* Pass: pass", test.getTestCase(0).toString());
	}

	/**
	 * Test method for compareTo()
	 */
	@Test
	void testCompareTo() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new TestPlan(null));
		assertEquals("Invalid name.", e1.getMessage());
		
		TestPlan test = new TestPlan("AsDf");
		TestPlan test2 = new TestPlan("Bsdf");
		TestPlan test3 = new TestPlan("Asdf");
		
		assertEquals(-1, test.compareTo(test2));
		assertEquals(1, test2.compareTo(test));
		assertEquals(0, test.compareTo(test3));
		assertEquals(0, test.compareTo(test));
	}

}