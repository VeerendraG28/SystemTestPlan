/**
 * 
 */
package edu.ncsu.csc216.stp.model.test_plans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * Tests the AbstractTestPlan class
 * 
 * @author Veerendra Gottiveeti
 * @author Yi Zhang
 */
class AbstractTestPlanTest {
	/** name of the test plan */
	private static final String TEST_PLAN = "test plan name";
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
	
	/**
	 * Test method for AbstractTestPlan constructor
	 */
	@Test
	void testAbstractTestPlan() {
		AbstractTestPlan test = new TestPlan(TEST_PLAN);
		assertEquals(TEST_PLAN, test.getTestPlanName());
		
		try {
			test.addTestResult(-1, false, DESCRIPTION);
		}
		catch (Exception e) {
			//empty
		}
		
		try {
			test.addTestResult(100, false, DESCRIPTION);
		}
		catch (Exception e) {
			//empty
		}
		
		try {
			test.addTestResult(1, false, null);
		}
		catch (Exception e) {
			//empty
		}
		
		try {
			test.addTestResult(1, false, "");
		}
		catch (Exception e) {
			//empty
		}
	}

	/**
	 * Test method for setTestPlanName()
	 */
	@Test
	void testSetTestPlanName() {
		
		AbstractTestPlan passingTest = new TestPlan("Carolina");
		assertEquals("Carolina", passingTest.getTestPlanName());
				
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new TestPlan(null));
		assertEquals("Invalid name.", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new TestPlan(""));
		assertEquals("Invalid name.", e2.getMessage()); 
	}


	/**
	 * Test method for addTestCase()
	 */
	@Test
	void testAddTestCase() {
		
		AbstractTestPlan passingTest = new TestPlan(TEST_PLAN);
		
		TestCase caseTest = new TestCase(ID, TYPE, DESCRIPTION, EXPECTED_RESULTS);
		
		passingTest.addTestCase(caseTest);
		
		assertEquals("# WolfScheduler,Requirements\n* test description\n* Pass: pass", passingTest.getTestCase(0).toString());
		
		try {
			passingTest.getTestCase(-1);
		}
		catch(Exception e) {
			//empty
		}
		
		
	}

	/**
	 * Test method for removeTestCase()
	 */
	@Test
	void testRemoveTestCase() { 
		
		AbstractTestPlan passingTest = new TestPlan(TEST_PLAN);
		
		TestCase caseTest = new TestCase(ID, TYPE, DESCRIPTION, EXPECTED_RESULTS);
		
		passingTest.addTestCase(caseTest);
		
		assertEquals("# WolfScheduler,Requirements\n* test description\n* Pass: pass", passingTest.getTestCase(0).toString());
		
		assertEquals(1, passingTest.getTestCases().size());
		
		passingTest.removeTestCase(0);
		
		assertEquals(0, passingTest.getTestCases().size());
		
		try {
			passingTest.removeTestCase(-1);
		}
		catch(Exception e) {
			//empty
		}
	}

	/**
	 * Test method for getTestCase()
	 */
	@Test
	void testGetTestCase() {
		
		AbstractTestPlan passingTest = new TestPlan(TEST_PLAN);
		
		TestCase caseTest = new TestCase(ID, TYPE, DESCRIPTION, EXPECTED_RESULTS);
		
		passingTest.addTestCase(caseTest);
		
		assertEquals("# WolfScheduler,Requirements\n* test description\n* Pass: pass", passingTest.getTestCase(0).toString());
		
		assertEquals(1, passingTest.getTestCases().size());
		
		passingTest.removeTestCase(0);
		
		assertEquals(0, passingTest.getTestCases().size());
				
	}

	/**
	 * Test method for getNumberOfFailingTests()
	 */
	@Test
	void testGetNumberOfFailingTests() {
		
		AbstractTestPlan passingTest = new TestPlan(TEST_PLAN);
		
		TestCase caseTest = new TestCase(ID, TYPE, DESCRIPTION, EXPECTED_RESULTS);
		
		caseTest.addTestResult(PASSING, PASSING_RESULTS);
		
		passingTest.addTestCase(caseTest);
		
		TestCase caseTest2 = new TestCase(ID, TYPE, DESCRIPTION, EXPECTED_RESULTS);
		
		caseTest2.addTestResult(FAILING, FAILING_RESULTS);
		
		passingTest.addTestCase(caseTest2);
		
		TestCase caseTest3 = new TestCase(ID, TYPE, DESCRIPTION, EXPECTED_RESULTS);
		
		caseTest3.addTestResult(FAILING, FAILING_RESULTS);
		
		passingTest.addTestCase(caseTest3);
		
		assertEquals(2, passingTest.getNumberOfFailingTests());
		
	}
	
	/**
	 * Test method for hashCode()
	 */
	@Test
	void testHashCode() {
		AbstractTestPlan test = new TestPlan(TEST_PLAN);
		AbstractTestPlan test2 = new TestPlan(TEST_PLAN);
		AbstractTestPlan test3 = new TestPlan("Different");
		
		assertEquals(test.hashCode(), test2.hashCode());
		assertNotEquals(test.hashCode(), test3.hashCode());
		assertNotEquals(test2.hashCode(), test3.hashCode());
	}

	/**
	 * Test method for equals()
	 */
	@Test
	void testEquals() {
		AbstractTestPlan test = new TestPlan(TEST_PLAN);
		AbstractTestPlan test2 = new TestPlan(TEST_PLAN);
		AbstractTestPlan test3 = new TestPlan("Different");
		
		assertTrue(test.equals(test2));
        assertTrue(test2.equals(test));
        assertFalse(test.equals(test3));
        assertFalse(test2.equals(test3));

	}

}