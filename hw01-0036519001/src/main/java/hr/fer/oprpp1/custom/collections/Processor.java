package hr.fer.oprpp1.custom.collections;

/**
 * This class models an object capable of performing some operation on the passed object.
 * Class Processor represents an conceptual contract between clients which will have
 * objects to be processed, and each concrete Processor which knows how to perform
 * the selected operation.
 * @author Krunoslav Zadrić
 */
public class Processor {

    /**
     * A method which processes the passed value in a way that is defined by each
     * concrete Processor.
     * @param value object to be processed
     */
    public void process(Object value) {

    }
}
