/**
 * 
 */
package edu.ncsu.csc216.stp.model.util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Tests the Log class
 * 
 * @author Veerendra Gottiveeti
 * @author Yi Zhang
 */
class LogTest {

	/** An custom ArrayList of Strings created for testing. */
	private Log<String> list;

	/**
	 * Test method for Log constructor
	 */
	@Test
	void testLog() {
		list = new Log<String>();
		assertEquals(0, list.size());
	}

	/**
	 * Test method for add()
	 */
	@Test
	void testAdd() {
		list = new Log<String>();
		assertEquals(0, list.size());

		list.add("One");
		assertEquals(1, list.size());
		assertEquals("One", list.get(0));

		list.add("Two");
		assertEquals(2, list.size());
		assertEquals("Two", list.get(1));

		list.add("Three");
		assertEquals(3, list.size());
		assertEquals("Three", list.get(2));

		assertThrows(NullPointerException.class, () -> list.add(null));

		assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));

		assertThrows(IndexOutOfBoundsException.class, () -> list.get(4));

		list.add("Four");

		list.add("Four");

		list.add("Four");

		list.add("Four");

		list.add("Four");

		list.add("Four");

		list.add("Four");
		list.add("Four");
		list.add("Four");
		list.add("Four");
		list.add("Four");
		list.add("Four");
		list.add("Four");
		list.add("Four");
		list.add("Four");
		list.add("Four");
		list.add("Four");

	}
}
