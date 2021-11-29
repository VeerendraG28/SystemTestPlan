/**
 * 
 */
package edu.ncsu.csc216.stp.model.io;

import static org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.SortedList;

/**
 * Tests the TestPlanWriter class
 * 
 * @author Veerendra Gottiveeti
 * @author Yi Zhang
 */
class TestPlanWriterTest {

	/** Test file to write on */
	private final File writerTestFile = new File("test-files/writerTestFile.txt");
	/** A non-exist test file for testing */
	private final File notExistFile = new File("test-files/notExist.txt");

	/**
	 * Tests valid test plan.
	 */
	@SuppressWarnings("static-access")
	@Test
	void testWriteTestPlanFile() {
		SortedList<TestPlan> testPlan = new SortedList<TestPlan>();

		TestPlan test = new TestPlan("WolfScheduler");
		TestCase testCase = new TestCase("test 1", "Equivalence Class", "Test Description", "ActualResults");
		test.addTestCase(testCase);
		testPlan.add(test);
		TestPlanWriter writer = new TestPlanWriter();

		try {
			writer.writeTestPlanFile(writerTestFile, testPlan);
		} catch (Exception e) {
			throw new IllegalArgumentException("Cannot write to file");
		}

		assertEquals(9, writerTestFile.compareTo(notExistFile));
	}

}