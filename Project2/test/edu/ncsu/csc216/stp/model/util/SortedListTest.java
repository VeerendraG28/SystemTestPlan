/**
 * 
 */
package edu.ncsu.csc216.stp.model.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the SortedList class
 * 
 * @author Veerendra Gottiveeti
 * @author Yi Zhang
 *
 */
class SortedListTest {
	/** An custom SortedList of Strings created for testing */
	private SortedList<String> list;

	/**
	 * Test method for SortedList constructor
	 */
	@Test
	void testSortedList() {
		list = new SortedList<String>();
		assertEquals(0, list.size());
	}

	/**
	 * Test method for add()
	 */
	@Test
	void testAdd() {

		list = new SortedList<String>();
		assertEquals(0, list.size());

		list.add("One");
		assertEquals(1, list.size());
		assertEquals("One", list.get(0));

		list.add("Two");
		assertEquals(2, list.size());
		assertEquals("Two", list.get(1));

		assertThrows(NullPointerException.class, () -> list.add(null));

		assertThrows(IllegalArgumentException.class, () -> list.add("Two"));

		assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));

		assertThrows(IndexOutOfBoundsException.class, () -> list.get(3));
	}

	/**
	 * Test method for remove()
	 */
	@Test
	void testRemove() {
		list = new SortedList<String>();
		assertEquals(0, list.size());

		list.add("One");
		assertEquals(1, list.size());
		list.add("Two");
		assertEquals(2, list.size());
		list.add("Three");
		assertEquals(3, list.size());

		list.remove(1);
		assertEquals(2, list.size());
		assertEquals("Two", list.get(1));
		list.remove(0);
		assertEquals(1, list.size());
		assertEquals("Two", list.get(0));

		assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
	}

	/**
	 * Test method for contains()
	 */
	@Test
	void testContains() {
		list = new SortedList<String>();
		assertEquals(0, list.size());

		list.add("One");
		assertEquals(1, list.size());
		assertEquals("One", list.get(0));

		list.add("Two");
		assertEquals(2, list.size());
		assertEquals("Two", list.get(1));

		assertTrue(list.contains("One"));
		assertTrue(list.contains("Two"));
		assertFalse(list.contains("Three"));
	}
}
