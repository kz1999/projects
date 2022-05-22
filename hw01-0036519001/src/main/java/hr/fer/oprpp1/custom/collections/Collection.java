package hr.fer.oprpp1.custom.collections;

/**
 * Representation of a general collection of objects
 *
 * @author Krunoslav Zadrić
 */
public class Collection {
    /**
     * a default constructor
     */
    protected Collection () {

    }

    /**
     * This method checks if the collection is empty
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * This method returns the number of elements stored in the collection
     * @return number of elements
     */
    public int size() {
        return 0;
    }

    /**
     * This method adds objects to the collection
     * @param value object that needs to be added to the collection
     */
    public void add(Object value) {

    }

    /**
     * This method checks if the collection contains the passed object
     * @param value object that needs to be checked
     * @return true if the collection contains the object, false otherwise
     */
    public boolean contains(Object value) {
        return false;
    }

    /**
     * This method removes one occurrence of the passed object
     * @param value object that needs to be removed
     * @return true if the collection contained the object and the object was removed,
     * false otherwise
     */
    public boolean remove(Object value) {
        return false;
    }

    /**
     * This method allocates new array with size equals to the size of this collection
     * and fills it with collection content
     * @return the new array
     * @throws UnsupportedOperationException
     */
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    /**
     * This method calls the process method of the passed instance of class Processor for
     * every element of the collection
     * @param processor instance of class Processor from which the method process needs to
     * be called
     */
    public void forEach(Processor processor) {

    }

    /**
     * This method adds all elements from the passed collection to the current collection
     * @param other the collection from which all elements need to be added
     */
    public void addAll(final Collection other) {
        class ProcessorAdder extends Processor {
            @Override
            public void process(Object value) {
                add(value);
            }
        }
        ProcessorAdder processor = new ProcessorAdder();
        forEach(processor);

    }

    /**
     * This method removes all the elements from the current collection
     */
    public void clear() {

    }
}
