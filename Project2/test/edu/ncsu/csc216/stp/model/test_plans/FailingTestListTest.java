/**
 * 
 */
package edu.ncsu.csc216.stp.model.test_plans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * Tests the FailingTestList class
 * 
 * @author Veerendra Gottiveeti
 * @author Yi Zhang
 */
class FailingTestListTest {

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
	private static final String FAILING_RESULTS = "FAIL";
	/** Boolean representation when test is passing */
	private static final Boolean PASSING = false;
	/** String to return when the test is passing */
	private static final String PASSING_RESULTS = "PASS";
	/** Boolean representation when test is passing */
	private static final Boolean PASSINGTRUE = true;
	/**
	 * Test method for FailingTestList constructor
	 */
	@Test 
	void testFailingTestList() {
		
		FailingTestList test = new FailingTestList();
		
		assertEquals(FAILING_TEST_LIST_NAME, test.getTestPlanName());
		
		
	}
	/**
	 * Test method for addTestCase()
	 */
	@Test
	void testAddTestCase() {
		
		FailingTestList test = new FailingTestList();

		TestCase test2 = new TestCase(ID, TYPE, DESCRIPTION, EXPECTED_RESULTS);
		
		test2.addTestResult(false, FAILING_RESULTS);

		test.addTestCase(test2);
		
		assertEquals(1, test.getTestCases().size());
		
		try {
			TestCase test3 = new TestCase(ID, TYPE, DESCRIPTION, EXPECTED_RESULTS);
			
			test3.addTestResult(true, PASSING_RESULTS);
			
			test.addTestCase(test3);
		}
		catch (Exception e) {
			//empty
		}
		
		assertEquals(1, test.getTestCases().size());
	}

	/**
	 * Test method for setTestPlanName()
	 */
	@Test
	void testSetTestPlanName() {
		
		try {
			FailingTestList test = new FailingTestList();
			
			test.setTestPlanName(FAILING_TEST_LIST_NAME);
			
			assertEquals(FAILING_TEST_LIST_NAME, test.getTestPlanName());
		}
		catch (Exception e) {
			//empty
		}

	}

	/**
	 * Test method for getTestCasesAsArray()
	 */
	@Test
	void testGetTestCasesAsArray() {
		
		FailingTestList planTest = new FailingTestList(); 
		
		TestCase test2 = new TestCase(ID, TYPE, DESCRIPTION, EXPECTED_RESULTS);
		
		test2.addTestResult(PASSING, FAILING_RESULTS);
		
		TestCase test3 = new TestCase(ID, TYPE, DESCRIPTION, EXPECTED_RESULTS);
		
		test3.addTestResult(PASSINGTRUE, PASSING_RESULTS);
		
		planTest.addTestCase(test2);
		
		try {
			planTest.addTestCase(test3);
		}
		catch (Exception e) {
			//empty
		}
	
		assertEquals(ID, planTest.getTestCase(0).getTestCaseId());
		assertEquals(TYPE, planTest.getTestCase(0).getTestType());
		assertEquals(FAIL, planTest.getTestCase(0).getStatus());

		String[][] caseDirectory = planTest.getTestCasesAsArray();
		
		assertEquals(1, caseDirectory.length);
		
		assertEquals(ID, caseDirectory[0][0]);
		assertEquals(TYPE, caseDirectory[0][1]);
		assertEquals("", caseDirectory[0][2]);
	} 

	/**
	 * Test method for clearTests()
	 */
	@Test
	void testClearTests() {
		
		FailingTestList planTest = new FailingTestList(); 
		
		TestCase test2 = new TestCase(ID, TYPE, DESCRIPTION, EXPECTED_RESULTS);
		
		test2.addTestResult(PASSING, FAILING_RESULTS);
		
		TestCase test3 = new TestCase(ID, TYPE, DESCRIPTION, EXPECTED_RESULTS);
		
		test3.addTestResult(PASSING, FAILING_RESULTS);
		
		planTest.addTestCase(test2); 
		planTest.addTestCase(test3);
		
		assertEquals(2, planTest.getTestCases().size());
		
		planTest.clearTests();
		
		assertEquals(0, planTest.getTestCases().size());
		
	}
}