/**
 * 
 */
package edu.ncsu.csc216.stp.model.tests;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

/**
 * Tests the TestResult class
 * 
 * @author Veerendra Gottiveeti
 * @author Yi Zhang
 */
class TestResultTest {
	/** Boolean representation when test is passing */
	private static final Boolean PASSING = true;
	/** Boolean representation when test is failing */
	private static final Boolean FAILING = false;
	/** String to return when the test is passing */
	private static final String PASSING_RESULTS = "pass";
	/** String to return when the test is failing */
	private static final String FAILING_RESULTS = "fail";
	
	/** 
	 * Test method for TestResult constructor
	 */
	@Test
	void testTestResult() {
		TestResult test = new TestResult(PASSING, PASSING_RESULTS);
		TestResult test2 = new TestResult(FAILING, FAILING_RESULTS);
		
		assertTrue(test.isPassing());
		assertFalse(test2.isPassing());
		assertEquals(PASSING_RESULTS, test.getActualResults());
		
	}

	/**
	 * Test method for getActualResults()
	 */
	@Test
	void testGetActualResults() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new TestResult(PASSING, null));
		assertEquals("Invalid test results.", e1.getMessage());
		
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new TestResult(PASSING, ""));
		assertEquals("Invalid test results.", e2.getMessage());
	}


	/**
	 * Test method for toString()
	 */
	@Test
	void testToString() {
		TestResult test = new TestResult(PASSING, PASSING_RESULTS);
		TestResult test2 = new TestResult(FAILING, FAILING_RESULTS);
		
		assertEquals("PASS: pass", test.toString());
		assertEquals("FAIL: fail", test2.toString());	}

}
