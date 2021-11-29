/**
 * 
 */
package edu.ncsu.csc216.stp.model.util;

/**
 * The Log class implements the ILog interface. The interface provides Javadoc
 * that describes what each method should do and the exceptions that should be
 * thrown. The Log allows duplicate elements.
 * 
 * @author Veerendra Gottiveeti
 * @author Yi Zhang
 *
 * @param <E> An generic arrayList type
 */
public class Log<E> implements ILog<E> {

	/** Generic type list array for Log */
	private E[] log;

	/** Private field for the size of a log/array */
	private int size;

	/** Constant integer field for the initial capacity of the log/array */
	private static final int INIT_CAPACITY = 10;

	/**
	 * Constructor for the Log class
	 */
	@SuppressWarnings("unchecked")
	public Log() {

		// creating a new log with the given size capacity
		log = (E[]) new Object[INIT_CAPACITY];

		size = 0;
	}

	/**
	 * Adding an element for the log Should throw a NullPointerException with
	 * message “Cannot add null element.” if the parameter is null.
	 * 
	 * @param e Element to be added
	 * @throws NullPointerException if the parameter is null
	 */
	@Override
	public void add(E e) {

		// if parameter e is null, throw null pointer exception
		if (e == null) {
			throw new NullPointerException("Cannot add null element.");
		}

		size++;

		if (size() == log.length) {
			@SuppressWarnings("unchecked")
			E[] temp = (E[]) new Object[log.length * 2];
			for (int i = 0; i < log.length; i++) {
				temp[i] = log[i];
			}
			log = temp;
		}

		log[size - 1] = e;

	}

	/**
	 * Returns the element at a given index in the list. If index is less than 0 or
	 * greater than or equal to size, throw exception.
	 * 
	 * @param index Index of the element
	 * @return Element at the given index
	 * @throws NullPointerException if the index parameter is out of bounds for the
	 *                              list
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}

		return log[index];

	}

	/**
	 * Returns the size of the list.
	 * 
	 * @return the number of elements in the list
	 */
	@Override
	public int size() {
		return size;
	}
}
