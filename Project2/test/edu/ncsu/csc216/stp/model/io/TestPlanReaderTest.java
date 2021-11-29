/**
 * 
 */
package edu.ncsu.csc216.stp.model.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.util.ISortedList;

/**
 * Tests the TestPlanReader class
 * 
 * @author Veerendra Gottiveeti
 * @author Yi Zhang
 */
class TestPlanReaderTest { 
	
	/**
	 * Test method for TestPlanReader constructor.
	 */
	@SuppressWarnings("static-access")
	@Test
	void testTestPlanReader() {
		
		TestPlanReader reader = new TestPlanReader();
		File validTestFile = new File("test-files/test-plans4.txt");
		
		ISortedList<TestPlan> tester = assertDoesNotThrow(() -> reader.readTestPlansFile(validTestFile), "Should not throw");
						
		TestPlan pantherSimulation = tester.get(0);
		//TestPlan pantherSimulation2 = tester.get(1);

		
		assertEquals("WolfScheduler", pantherSimulation.getTestPlanName());
		//assertEquals("WolfScheduler", pantherSimulation2.getTestPlanName());
		
	}

	/**
	 * Test method for readTestPlansFile()
	 */
	@SuppressWarnings("static-access")
	@Test
	void testReadTestPlansFile() {
		TestPlanReader reader = new TestPlanReader();
		File validTestFile = new File("test-files/test-plans1.txt");
		
		ISortedList<TestPlan> tester = assertDoesNotThrow(() -> reader.readTestPlansFile(validTestFile), 
				"Should not throw exception.");
		
		TestPlan test = tester.get(0);
		TestPlan test2 = tester.get(1);
		
		assertEquals("PackScheduler", test.getTestPlanName());
		assertEquals("WolfScheduler", test2.getTestPlanName());
		
	}

}
