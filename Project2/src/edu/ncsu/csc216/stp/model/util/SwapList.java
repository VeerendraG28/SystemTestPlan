/**
 * 
 */
package edu.ncsu.csc216.stp.model.util;

/**
 * The SwapList class implements the ISwapList interface. The interface provides
 * Javadoc that describes what each method should do and the exceptions that
 * should be thrown. The SwapList allows duplicate elements.
 * 
 * @author Veerendra Gottiveeti
 * @author Yi Zhang
 *
 * @param <E> An generic arrayList type
 */
public class SwapList<E> implements ISwapList<E> {

	/** Constant integer field for the initial capacity of the log/array */
	private static final int INITIAL_CAPACITY = 10;

	/** Private E array for the log */
	private E[] list;

	/** Constant int field for the size of the SortedList */
	private int size;

	/**
	 * Constructor for the SwapList class
	 */
	@SuppressWarnings("unchecked")
	public SwapList() {
		// creating a new log with the given size capacity
		list = (E[]) new Object[INITIAL_CAPACITY];

		size = 0;
	}

	/**
	 * Adding an element for the log Should throw a NullPointerException with
	 * message “Cannot add null element.” if the parameter is null.
	 * 
	 * @param e Element to be added
	 * @throws NullPointerException     if the parameter is null
	 * @throws IllegalArgumentException if element cannot be added
	 */
	@Override
	public void add(E e) {

		// Check if element is a null
		if (e == null) {
			throw new NullPointerException("Cannot add null element.");
		}

		// Check if element is a duplicate
		for (int i = 0; i < size(); i++) {
			if (this.get(i).equals(e)) {
				throw new IllegalArgumentException("Cannot add duplicate element");
			}
		}

		checkCapacity(size);

		size++;

		list[size - 1] = e;
	}

	/**
	 * Checks the capacity of the list using an index, if the size of the list is
	 * greater or equal than capacity, the capacity doubles.
	 * 
	 * @param index Index of the SortedList
	 * @throws IndexOutOfBoundsException if the index is out of bounds for the list
	 */
	@SuppressWarnings("unchecked")
	private void checkCapacity(int index) {

		if (index == list.length) {
			E[] newArray = (E[]) new Object[list.length * 2];
			for (int i = 0; i < list.length; i++) {
				newArray[i] = list[i];
			}

			list = newArray;
		}

	}

	/**
	 * Removes an element from the given index.
	 * 
	 * @param index Index to start removing elements
	 * @return element removed at given index
	 * @throws IndexOutOfBoundsException if the index is out of bounds for the list
	 */
	@Override
	public E remove(int index) {

		checkIndex(index);

		E removedElement = list[index];

		if (index == size() - 1) {
			list[index] = null;
			size--;
			return removedElement;
		} else {
			for (int i = index; i < size(); i++) {
				list[i] = list[i + 1];
			}
		}

		list[size() - 1] = null;

		size--;

		return removedElement;
	}

	/**
	 * Checking the index/position in the Sorted List
	 * 
	 * @param index Index of the element
	 * @throws IndexOutOfBoundsException if the index parameter is out of bounds for
	 *                                   the list
	 */
	private void checkIndex(int index) {

		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}

		checkCapacity(index);

	}

	/**
	 * Moves the element at give index to to one index to the left only if the
	 * element is not at the front of the list.
	 * 
	 * @param index Index of the element to move up
	 * @throws IndexOutOfBoundsException if the index is out of bounds for the list
	 */
	@Override
	public void moveUp(int index) {

		// if index is less than 0 or greater than or equal to size, throw exception
		checkIndex(index);

		if (index != 0) {
			E list2 = list[index - 1];
			list[index - 1] = list[index];
			list[index] = list2;
		}
	}

	/**
	 * Moves the element at give index to to one index to the right only if the
	 * element is not at the end of the list.
	 * 
	 * @param index Index of the element to move down
	 * @throws IndexOutOfBoundsException if the index is out of bounds for the list
	 */
	@Override
	public void moveDown(int index) {

		// if index is less than 0 or greater than or equal to size, throw exception
		checkIndex(index);

		if (index != size - 1) {
			E list2 = list[index + 1];
			list[index + 1] = list[index];
			list[index] = list2;
		}
	}

	/**
	 * Moves the element at give index to the front of the list only if the element
	 * is not at the front of the list.
	 * 
	 * @param index Index of element to move to front
	 * @throws IndexOutOfBoundsException if the index is out of bounds for the list
	 */
	@Override
	public void moveToFront(int index) {
		// if index is less than 0 or greater than or equal to size, throw exception
		checkIndex(index);

		if (index != 0) {
			E list2 = list[index];
			for (int i = index; i > 0; i--) {
				list[i] = list[i - 1];
			}
			list[0] = list2;
		}
	}

	/**
	 * Moves the element at give index to the end of the list only if the element is
	 * not at the end of the list.
	 * 
	 * @param index Index of element to move to end
	 * @throws IndexOutOfBoundsException if the index is out of bounds for the list
	 */
	@Override
	public void moveToBack(int index) {
		// if index is less than 0 or greater than or equal to size, throw exception
		checkIndex(index);

		if (index != size - 1) {
			E list2 = list[index];
			for (int i = index; i < size - 1; i++) {
				list[i] = list[i + 1];
			}
			list[size - 1] = list2;
		}
	}

	/**
	 * Returns the element at the given index.
	 * 
	 * @param index index of the element to retrieve
	 * @return element at the given index
	 * @throws IndexOutOfBoundsException if the index is out of bounds for the list
	 */
	@Override
	public E get(int index) {
		// if index is less than 0 or greater than or equal to size, throw exception
		checkIndex(index);

		return list[index];

	}

	/**
	 * Returns the number of elements in the list.
	 * 
	 * @return the size of the list
	 */
	@Override
	public int size() {
		return size;
	}

}
