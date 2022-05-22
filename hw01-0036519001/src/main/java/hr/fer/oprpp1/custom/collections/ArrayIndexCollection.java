package hr.fer.oprpp1.custom.collections;

import java.lang.reflect.Array;

/**
 * Implementation of resizable array-backed collection of objects
 *
 * @author Krunoslav Zadrić
 */
public class ArrayIndexCollection extends Collection {
    private int size;
    private Object[] elements;

    /**
     * Constructor which takes the desired capacity and makes a new instance of the collection
     * with that capacity
     * @param initialCapacity desired capacity
     */
    public ArrayIndexCollection(int initialCapacity) {
        if (initialCapacity < 1)
            throw new IllegalArgumentException("initialCapacity can't be less than 1");
        elements = new Object[initialCapacity];
        size = 0;
    }

    /**
     * Default constructor which creates an instance of the collection with capacity of 16 objects
     */
    public ArrayIndexCollection() {
        this(16);
    }

    /**
     * Constructor which creates a new instance of the collection and copies the elements from the passed
     * collection into the new one
     * @param initialCapacity desired capacity of the new collection
     * @param collection passed collection whose elements need to be copied into the new one
     * @throws NullPointerException throws an exception if the passed collection is null
     */
    public ArrayIndexCollection(int initialCapacity, Collection collection) {
        if (collection == null)
            throw new NullPointerException("the passed collection can't be null");
        if (initialCapacity < collection.size()) {
            elements = new Object[collection.size()];
        } else {
            elements = new Object[initialCapacity];
        }
        this.addAll(collection);
        size = collection.size();
    }

    /**
     * Constructor which takes the passed collection and copies its elements into a new one
     * @param collection the passed collection whose elements need to be copied
     */
    public ArrayIndexCollection(Collection collection) {
        this(16, collection);
    }

    @Override
    public int size() {
        return this.size;
    }

    /**
     * This method adds an object to the first empty place if that object is not null.
     * If the array is full, a new array is made double the size of the previous one.
     * @param value object that needs to be added to the collection
     */
    @Override
    public void add(Object value) {
        if (value == null) {
            throw new IllegalArgumentException("\"null\" is not a valid argument");
        }
        if (size == elements.length) {
            Object[] temp = new Object[2 * size];

            for (int i = 0; i < elements.length; i++) {
                temp[i] = elements[i];
            }
            elements = temp;
        }
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == null) {
                elements[i] = value;
                break;
            }
        }
        size++;
    }

    @Override
    public boolean contains(Object value) {
        if (value == null)
            return false;
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(value))
                return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object value) {
        if (value == null)
            throw new IllegalArgumentException("\"null\"is not a valid argument");

        for (int i = 0; i < size; i++) {
            if (elements[i].equals(value)) {
                elements[i] = null;
                Object[] temp = new Object[elements.length];
                //copying array elements into array temp
                for (int j = 0; j < size; j++) {
                    if (elements[j] == null)
                        continue;
                    temp[j] = elements[j];
                }
                elements = temp;
                size--;
                return true;
            }
        }
        return false;
    }

    @Override public Object[] toArray() {
        Object[] newArr = new Object[size];
        for (int i = 0; i < size; i++) {
            newArr[i] = elements[i];
        }
        return newArr;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * This method returns the array element from the specified index
     * @param index position from which the element needs to be returned
     * @return element at the given index
     * @throws IndexOutOfBoundsException throws an exception if the index is invalid
     */
    public Object get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        return elements[index];
    }

    /**
     * This method inserts the element into the given position and shifts the rest of
     * the array on the index of insertion and greater one place up
     * @param value object to be inserted
     * @param position index of the insertion
     */
    public void insert(Object value, int position) {
        if (position < 0 || position > size)
            throw new IndexOutOfBoundsException("Position of the insertion needs to be between 0 and " + (size - 1));
        Object[] temp;

        if (size == elements.length) {
            temp = new Object[2 * elements.length];
        } else {
            temp = new Object[elements.length];
        }

        for (int i = 0; i < position; i++) {
            temp[i] = elements[i];
        }
        temp[position] = value;

        for (int i = position; i < elements.length; i++) {
            temp[i + 1] = elements[i];
        }
        elements = temp;
        add(value);
    }

    /**
     * This method searches the collection and returns the index of the first occurrence of the given value
     * or -1 if the value is
     * not found.
     * @param value object which needs to be found
     * @return index of the first occurrence of value
     */
    public int indexOf(Object value) {
        int index = -1;

        for (int i = 0; i < size; i++) {
            if (elements[i].equals(value)) {
                index = i;
                return index;
            }
        }
        return index;
    }

    /**
     * This method removes element at specified index from collection.
     * @param index index from which the element needs to be removed
     * @throws IndexOutOfBoundsException exception occurs if the index is invalid
     */
    public void remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Position of the removal needs to be between 0 and " + (size - 1));

        elements[index] = null;
        Object[] temp = new Object[elements.length];
        //copying array elements into array temp
        for (int i = 0; i < size; i++) {
            if (elements[i] == null)
                continue;
            temp[i] = elements[i];
        }
        elements = temp;
        size--;
    }

}
