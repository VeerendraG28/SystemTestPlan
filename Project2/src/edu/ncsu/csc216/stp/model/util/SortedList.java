
package edu.ncsu.csc216.stp.model.util;

/**
 * SortedList class implements the ISortedList interface. The generic type
 * should extend the Comparable interface. The SortedList class uses the
 * Comparable.compareTo() method to determine the ordering of elements.
 * 
 * @author Veerendra Gottiveeti
 * @author Yi Zhang
 *
 * @param <E> An generic arrayList type
 */
public class SortedList<E extends Comparable<E>> implements ISortedList<E> {

	/** Constant int field for the size of the SortedList */
	private int size;
	/** Represents the first element of the list */
	private ListNode front;

	/**
	 * Constructor for the SortedList class
	 */
	public SortedList() {

		front = null;
		this.size = 0;

	}

	/**
	 * Adds an element in the SortedList. Should throw a NullPointerException with
	 * message “Cannot add null element.” if the parameter is null.
	 * 
	 * @param e Element to be added
	 * @throws NullPointerException if the parameter is null
	 * @throws NullPointerException if the parameter is a duplicate of an existing
	 *                              element
	 */
	@Override
	public void add(E e) {
		if (e == null) {
			throw new NullPointerException("Cannot add null element.");
		}

		// Check if element is a duplicate.
		if (this.contains(e)) {
			throw new IllegalArgumentException("Cannot add duplicate element.");
		}

		if (front == null || e.compareTo(front.data) < 0) {
			front = new ListNode(e, front);
		} else {
			ListNode current = front;
			while (current.next != null && current.next.data.compareTo(e) < 0) {
				current = current.next;
			}
			current.next = new ListNode(e, current.next);
		}

		size++;
	}

	/**
	 * Removes an element in the SortedList
	 * 
	 * @param index Index of the element
	 * @return the removed element at the index
	 * @throws IndexOutOfBoundsException if the index parameter is out of bounds for
	 *                                   the list
	 */
	@Override
	public E remove(int index) {
		// checks if index is out of bounds
		checkIndex(index);

		ListNode current = front;
		E value;
		if (index == 0) {
			value = front.data;
			front = front.next;
		} else {
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			value = current.next.data;
			current.next = current.next.next;
		}

		size--;
		return value;
	}

	/**
	 * Checking the index/position in the Sorted List
	 * 
	 * @param index Index of the element
	 * @throws NullPointerException      if the element is null
	 * @throws IndexOutOfBoundsException if the index parameter is out of bounds for
	 *                                   the list
	 */
	private void checkIndex(int index) {

		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}

	}

	/**
	 * Check if the SortedList contains an element
	 * 
	 * @param e An element in the SortedList
	 * 
	 * @return true if the SortedList contains the element, otherwise false
	 * @throws NullPointerException is element is null
	 */
	@Override
	public boolean contains(E e) {
		if (e == null) {
			throw new NullPointerException("Cannot add null element.");
		}

		for (int i = 0; i < size(); i++) {
			if (get(i).equals(e)) {
				return true;
			}
		}
		return false;

	}

	/**
	 * Returns element at given index position of a SortedList
	 * 
	 * @param index Index of a SortedList
	 * @return Element at given index position
	 * @throws IndexOutOfBoundsException if the index parameter is out of bounds for
	 *                                   the list
	 */
	@Override
	public E get(int index) {

		// checks if index is out of bounds
		checkIndex(index);

		if (front == null) {
			throw new NullPointerException();
		}

		if (index == 0) {
			return front.data;
		}

		ListNode node = front;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}

		return node.data;
	}

	/**
	 * Returns the number of elements in the SortedList
	 * 
	 * @return the size of the SortedList
	 */
	public int size() {
		return size;
	}

	/**
	 * Responsible for the splitting individual elements into Nodes and executing a
	 * train mechanism to pass through the elements.
	 * 
	 * @author Veerendra Gottiveeti
	 * @author Yi Zhang
	 *
	 */
	public class ListNode {

		/** E type for a data field */
		public E data;

		/** Represents the next node in the SortedList */
		public ListNode next;

		/**
		 * Method representing the nodes for the SortedList to pass through each
		 * individual element
		 * 
		 * @param data Element in the list
		 * @param next A node for a singly-linked list
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}
}
