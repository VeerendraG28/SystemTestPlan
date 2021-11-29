/**
 * 
 */
package edu.ncsu.csc216.stp.model.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.util.Log;

/**
 * Tests the TestCase class
 * 
 * @author Veerendra Gottiveeti
 * @author Yi Zhang
 */
class TestCaseTest {
	/** Id of the test case */
	private static final String ID = "WolfScheduler";
	/** Type of the test case */
	private static final String TYPE = "Requirements";
	/** Description of the test case */
	private static final String DESCRIPTION = "test description";
	/** Expected results of the test case */
	private static final String EXPECTED_RESULTS = "Pass: pass";
	/** Boolean representation when test is passing */
	private static final Boolean PASSING = true;
	/** Boolean representation when test is failing */
	private static final Boolean FAILING = false;
	/** String to return when the test is passing */
	private static final String PASSING_RESULTS = "PASS";
	/** String to return when the test is failing */
	private static final String FAILING_RESULTS = "FAIL";
	/** Log of TestResults */
	Log<TestResult> testResults = new Log<TestResult>();
	/** Test Plan Name */
	TestPlan testPlan;

	/**
	 * Test method for TestCase constructor
	 */
	@Test
	void testTestCase() {
		
		TestCase test = new TestCase(ID, TYPE, DESCRIPTION, EXPECTED_RESULTS);

		assertEquals("WolfScheduler", test.getTestCaseId());
		assertEquals("Requirements", test.getTestType());
		assertEquals("test description", test.getTestDescription());
		assertEquals("Pass: pass", test.getExpectedResults());
		
		TestCase testTwo = new TestCase("ID 1", "type 1", "description 1", "expected results 1");

		assertEquals("ID 1", testTwo.getTestCaseId());
		assertEquals("type 1", testTwo.getTestType());
		assertEquals("description 1", testTwo.getTestDescription());
		assertEquals("expected results 1", testTwo.getExpectedResults());
		
	
	}

	/**
	 * Test method for getTestCaseId()
	 */
	@Test
	void testGetTestCaseId() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new TestCase(null, TYPE, DESCRIPTION, EXPECTED_RESULTS));
		assertEquals("Invalid test information.", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new TestCase("", TYPE, DESCRIPTION, EXPECTED_RESULTS));
		assertEquals("Invalid test information.", e2.getMessage());
	}

	/**
	 * Test method for getTestType()
	 */
	@Test
	void testGetTestType() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new TestCase(ID, null, DESCRIPTION, EXPECTED_RESULTS));
		assertEquals("Invalid test information.", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new TestCase(ID, "", DESCRIPTION, EXPECTED_RESULTS));
		assertEquals("Invalid test information.", e2.getMessage());
	}

	/**
	 * Test method for getTestDescription()
	 */
	@Test
	void testGetTestDescription() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new TestCase(ID, TYPE, null, EXPECTED_RESULTS));
		assertEquals("Invalid test information.", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> new TestCase(ID, TYPE, "", EXPECTED_RESULTS));
		assertEquals("Invalid test information.", e2.getMessage());
	}

	/**
	 * Test method for getExpectedResults()
	 */
	@Test
	void testGetExpectedResults() {
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> new TestCase(ID, TYPE, DESCRIPTION, null));
		assertEquals("Invalid test information.", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> new TestCase(ID, TYPE, DESCRIPTION, ""));
		assertEquals("Invalid test information.", e2.getMessage());
	}

	/**
	 * Test method for addTestResult()
	 */
	@Test
	void testAddTestResult() {
		TestCase test = new TestCase(ID, TYPE, DESCRIPTION, EXPECTED_RESULTS);

		test.addTestResult(PASSING, PASSING_RESULTS);
		assertEquals("- " + "PASS: PASS" + "\n", test.getActualResultsLog());
	}

	/**
	 * Test method for isTestCasePassing()
	 */ 
	@Test
	void testIsTestCasePassing() {
				
		TestCase test = new TestCase(ID, TYPE, DESCRIPTION, EXPECTED_RESULTS);
		
		test.addTestResult(PASSING, PASSING_RESULTS);
		assertEquals(PASSING, test.isTestCasePassing());
		test.addTestResult(FAILING, FAILING_RESULTS);
		assertEquals(FAILING, test.isTestCasePassing());

	}

	/**
	 * Test method for getStatus()
	 */
	@Test
	void testGetStatus() {
		
		TestCase test = new TestCase(ID, TYPE, DESCRIPTION, EXPECTED_RESULTS);

		test.addTestResult(PASSING, PASSING_RESULTS);

		assertEquals("PASS", test.getStatus());
		
		TestCase test2 = new TestCase(ID, TYPE, DESCRIPTION, EXPECTED_RESULTS);
		
		test2.addTestResult(FAILING, FAILING_RESULTS);

		assertEquals("FAIL", test2.getStatus());

	}
	
	/**
	 * Test method for getTestPlan()
	 */
	@Test
	void testGetTestPlan() {
		
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> new TestPlan(null));
		assertEquals("Invalid name.", e1.getMessage());

	}

	/**
	 * Test method for toString()
	 */
	@Test
	void testToString() {
		TestCase test = new TestCase(ID, TYPE, DESCRIPTION, EXPECTED_RESULTS);
		assertEquals("# WolfScheduler,Requirements\n* test description\n* Pass: pass", test.toString());
	}

}
