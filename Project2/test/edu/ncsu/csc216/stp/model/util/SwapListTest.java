/**
 * 
 */
package edu.ncsu.csc216.stp.model.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the SwapList class
 * 
 * @author Veerendra Gottiveeti
 * @author Yi Zhang
 *
 */
class SwapListTest {

	/** An custom SwapList of Strings created for testing */
	private SwapList<String> list;

	/**
	 * Test method for SwapList constructor
	 */
	@Test
	void testSwapList() {
		list = new SwapList<String>();
		assertEquals(0, list.size());
	}

	/**
	 * Test method for add()
	 */
	@Test
	void testAdd() {
		list = new SwapList<String>();
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
	}

	/**
	 * Test method for remove()
	 */
	@Test
	void testRemove() {
		list = new SwapList<String>();
		assertEquals(0, list.size());

		list.add("One");
		assertEquals(1, list.size());
		list.add("Two");
		assertEquals(2, list.size());
		list.add("Three");
		assertEquals(3, list.size());

		list.remove(1);
		assertEquals(2, list.size());
		assertEquals("One", list.get(0));
		assertEquals("Three", list.get(1));

		list.remove(0);
		assertEquals(1, list.size());
		assertEquals("Three", list.get(0));

		assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(3));
	}

	/**
	 * Test method for moveUp()
	 */
	@Test
	void testMoveUp() {
		list = new SwapList<String>();
		assertEquals(0, list.size());

		list.add("One");
		assertEquals(1, list.size());
		list.add("Two");
		assertEquals(2, list.size());
		list.add("Three");
		assertEquals(3, list.size());
		list.add("Four");
		assertEquals(4, list.size());
		// element at the front
		list.moveUp(0);

		assertEquals("One", list.get(0));
		assertEquals("Two", list.get(1));

		// element not at the front
		list.moveUp(1);
		assertEquals("Two", list.get(0));
		assertEquals("One", list.get(1));

		// element not at the front
		list.moveUp(2);
		assertEquals("Three", list.get(1));
		assertEquals("One", list.get(2));

		assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(6));
	}

	/**
	 * Test method for moveDown()
	 */
	@Test
	void testMoveDown() {
		list = new SwapList<String>();
		assertEquals(0, list.size());

		list.add("One");
		assertEquals(1, list.size());
		list.add("Two");
		assertEquals(2, list.size());
		list.add("Three");
		assertEquals(3, list.size());
		list.add("Four");
		assertEquals(4, list.size());
		// element at the end
		list.moveDown(0);

		assertEquals("Two", list.get(0));
		assertEquals("One", list.get(1));

		// element not at the end
		list.moveDown(3);
		assertEquals("Four", list.get(3));
		assertEquals("Three", list.get(2));

		assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(5));
	}

	/**
	 * Test method for moveToFront()
	 */
	@Test
	void testMoveToFront() {
		list = new SwapList<String>();
		assertEquals(0, list.size());

		list.add("One");
		assertEquals(1, list.size());
		list.add("Two");
		assertEquals(2, list.size());
		list.add("Three");
		assertEquals(3, list.size());
		// element at the front
		list.moveToFront(0);

		assertEquals("One", list.get(0));
		assertEquals("Two", list.get(1));
		assertEquals("Three", list.get(2));

		// element not at the front
		list.moveToFront(2);
		assertEquals("Three", list.get(0));
		assertEquals("One", list.get(1));
		assertEquals("Two", list.get(2));

		assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(4));
	}

	/**
	 * Test method for moveToBack()
	 */
	@Test
	void testMoveToBack() {
		list = new SwapList<String>();
		assertEquals(0, list.size());

		list.add("One");
		assertEquals(1, list.size());
		list.add("Two");
		assertEquals(2, list.size());
		list.add("Three");
		assertEquals(3, list.size());
		// element at the end
		list.moveToBack(2);

		assertEquals("One", list.get(0));
		assertEquals("Two", list.get(1));
		assertEquals("Three", list.get(2));

		// element not at the end
		list.moveToBack(0);
		assertEquals("Two", list.get(0));
		assertEquals("Three", list.get(1));
		assertEquals("One", list.get(2));

		assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(4));
	}
}
