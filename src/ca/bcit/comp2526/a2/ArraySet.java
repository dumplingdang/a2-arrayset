package ca.bcit.comp2526.a2;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.Predicate;

/***
 * Describes a type ArraySet.
 * @author Jianfen Deng
 * @version 1.0
 * @param <E> this describes the type parameter
 */
public class ArraySet<E> implements Collection<E>, Set<E> {
    /***
     * Initial capacity of type arraySet is {@value}.
     */
    public static final int INITIAL_CAPACITY_OF_ARRAYSET = 10;
    /***
     * Initial size of type arraySet is {@value}.
     */
    public static final int INITIAL_SIZE_OF_ARRAYSET     = 0;
    /***
     * Constructs a type ArraySet.
     */
    private             int size;
    private             int capacity;
    private             E[] elements;
    /***
     * Constructs a type ArraySet.
     */
    public ArraySet() {
        size     = INITIAL_SIZE_OF_ARRAYSET;
        capacity = INITIAL_CAPACITY_OF_ARRAYSET;
        elements = (E[]) new Object[capacity];
    }
    /**
     * Returns the number of elements in this set.
     * @return the number of elements in this set
     */
    @Override
    public int size() {
        return size;
    }
    /**
     * Returns {@code true} if this set contains no elements.
     * @return {@code true} if this set contains no elements
     */
    @Override
    public boolean isEmpty() {
        int numberOfNonNullElements = 0;
        for (E element : elements) {
            if (element != null) {
                numberOfNonNullElements++;
            }
        }
        return numberOfNonNullElements == 0;
    }
    /**
     * Adds the specified element to this set if it is not already present.
     * @param e element to be added to this set
     * @return {@code true} if this set did not already contain the specified element
     * @throws NullPointerException     if the specified element is null and this
     *                                  set does not permit null elements
     */
    @Override
    public boolean add(final E e) {
        E[] interimArray = elements;
        if (e == null) {
            throw new NullPointerException();
        }
        for (E element : elements) {
            if (e.equals(element)) {
//                throw new IllegalArgumentException("element already exists in this set");
                return false;
            }
        }
        size++;
        if (size >= capacity) {
            capacity = capacity * 2;
            elements = (E[]) new Object[capacity];
            for (int i = 0; i < size; i++) {
                elements[i] = interimArray[i];
            }
        }
        elements[size - 1] = e;
        return true;
    }
    /**
     * Removes the specified element from this set if it is present.
     * @param o object to be removed from this set, if present
     * @return {@code true} if this set contained the specified element
     * @throws NullPointerException if the specified element is null and this
     *                              set does not permit null elements
     */
    @Override
    public boolean remove(final Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
        if (isEmpty()) {
            throw new NullPointerException();
        }
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                elements[i]        = elements[size - 1];
                elements[size - 1] = null;
                size--;
                return true;
            }
        }
        return false;
    }
    /**
     * Returns {@code true} if this set contains the specified element.
     * @param o element whose presence in this set is to be tested
     * @return {@code true} if this set contains the specified element
     * @throws NullPointerException if the specified element is null
     */
    @Override
    public boolean contains(final Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
        for (E element : elements) {
            if (o.equals(element)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Returns an iterator over the elements in this set.
     * @return an iterator over the elements in this set
     */
    @Override
    public Iterator<E> iterator() {
        return new ArraySetIterator<>();
    }
    private class ArraySetIterator<E> implements Iterator<E> {
        private int cursor = 0;
        ArraySetIterator() {
        }
        /*
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            if (elements[cursor] == null) {
                return false;
            } else {
                cursor++;
                return true;
            }
        }
        /*
         * Returns the next element in the iteration.
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public E next() {
            int index = --cursor;
            if (hasNext()) {
                return (E) elements[index];
            }
            throw new NoSuchElementException();
        }
    }
    /**
     * Returns an array containing all of the elements in this set.
     * @return an array containing all the elements in this set
     */
    @Override
    public Object[] toArray() {
        Object[] subjectElements = new Object[size];
        for (int i = 0; i < size(); i++) {
            if (elements[i] != null) {
                subjectElements[i] = elements[i];
            }
        }
        return subjectElements;
    }
    /**
     * Returns an array containing all of the elements in this set; the
     * runtime type of the returned array is that of the specified array.
     * @param a the array into which the elements of this set are to be
     *          stored
     * @return an array containing all the elements in this set
     * @throws NullPointerException if the specified array is null
     */
    @Override
    public <E> E[] toArray(final E[] a) {
        if (a.length != size()) {
            throw new NullPointerException();
        }
        for (int i = 0; i < size; i++) {
            if (elements[i] != null) {
                a[i] = (E) elements[i];
            }
        }
        return a;
    }
    /**
     * Returns {@code true} if this set contains all of the elements of the
     * specified collection.
     * @param c collection to be checked for containment in this set
     * @return {@code true} if this set contains all of the elements of the
     * specified collection
     * @throws NullPointerException if the specified collection contains one
     *                              or more null elements
     * @see #contains(Object)
     */
    @Override
    public boolean containsAll(final Collection<?> c) {
        int numberOfContainedElements = 0;
        if (c == null || c.isEmpty() || isEmpty()) {
            throw new NullPointerException();
        }
//        if (!c.getClass().equals(getClass())) {
//            throw new ClassCastException();
//        }
//        ArraySet<E> param = (ArraySet<E>) c;
        for (Object item : c) {
            if (item == null) {
                throw new NullPointerException();
            }
            if (contains(item)) {
                numberOfContainedElements++;
            }
        }
        if (numberOfContainedElements == c.size()) {
            return true;
        }
        return false;
    }
    /**
     * Adds all of the elements in the specified collection to this set if
     * they're not already present.
     * @param c collection containing elements to be added to this set
     * @return {@code true} if this set changed as a result of the call
     * @throws NullPointerException     if the specified collection contains one
     *                                  or more null elements and this set does not permit null
     *                                  elements, or if the specified collection is null
     * @throws IllegalArgumentException if some property of an element of the
     *                                  specified collection prevents it from being added
     *                                  to this set
     * @see #add(Object)
     */
    @Override
    public boolean addAll(final Collection<? extends E> c) {
        if (c == null || c.isEmpty()) {
            throw new NullPointerException();
        }
        for (E item : c) {
            if (item == null) {
                throw new NullPointerException();
            }
            if (contains(item)) {
                throw new IllegalArgumentException("element already exists in this arraySet");
            }
            add(item);
        }
        return true;
    }
    /**
     * Removes all elements in this set which is not contained in the specified collection.
     * @param c collection containing elements to be retained in this set
     * @return {@code true} if this set changed as a result of the call
     * @throws NullPointerException if this set contains a null element and the
     *                              specified collection does not permit null elements
     */
    @Override
    public boolean retainAll(final Collection<?> c) {
        int numberOfRemoved = 0;
        if (c == null || c.isEmpty() || isEmpty()) {
            throw new NullPointerException();
        }
        for (Object item : c) {
            if (item == null) {
                throw new NullPointerException();
            }
        }
        int length = size();
        for (int i = 0; i < length; i++) {
            if (elements[i] != null) {
                if (!c.contains(elements[i])) {
                    elements[i] = null;
                    size--;
                    numberOfRemoved++;
                }
            }
        }
//        for (E element : elements) {
//            if (element != null) {
//                if (!c.contains(element)) {
//                    remove(element);
//                    numberOfRemoved++;
//                }
//            }
//        }
        if (numberOfRemoved == 0) {
            return false;
        }
        return true;
    }
    /**
     * Removes from this set all of its elements that are contained in the
     * specified collection.
     * @param c collection containing elements to be removed from this set
     * @return {@code true} if this set changed as a result of the call
     * @throws NullPointerException if this set contains a null element and the
     *                              specified collection does not permit null elements
     * @see #contains(Object)
     */
    @Override
    public boolean removeAll(final Collection<?> c) {
        int numberOfRemoved = 0;
        if (c == null || c.isEmpty() || isEmpty()) {
            throw new NullPointerException();
        }
        for (Object item : c) {
            if (item == null) {
                throw new NullPointerException();
            }
        }
        int length = size();
        for (int i = 0; i < length; i++) {
            if (elements[i] != null) {
                if (c.contains(elements[i])) {
                    elements[i] = null;
                    size--;
                    numberOfRemoved++;
                }
            }
        }
        if (numberOfRemoved == 0) {
            return false;
        }
        return true;
    }
    /**
     * Removes all of the elements from this set.
     * The set will be empty after this call returns.
     */
    @Override
    public void clear() {
        int length = size();
        for (int i = 0; i < length; i++) {
            elements[i] = null;
            size--;
        }
    }
    /**
     * Removes all of the elements of this set that satisfy the given
     * predicate.
     * @param filter a predicate which returns {@code true} for elements to be
     *               removed
     * @return {@code true} if any elements were removed
     * @throws NullPointerException          if the specified filter is null
     */
    @Override
    public boolean removeIf(final Predicate<? super E> filter) {
        if (filter == null) {
            throw new NullPointerException();
        }
        int length = size();
        int numberOfRemoved = 0;
        for (int i = 0; i < length; i++) {
            if (filter.test(elements[i])) {
                elements[i] = null;
                size--;
                numberOfRemoved++;
            }
        }
        if (numberOfRemoved == 0) {
            return false;
        }
        return true;
    }
}
