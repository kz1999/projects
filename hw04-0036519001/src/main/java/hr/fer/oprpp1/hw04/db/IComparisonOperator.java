package hr.fer.oprpp1.hw04.db;

/**
 * This interface represents a strategy for comparing two strings
 */
public interface IComparisonOperator {
    /**
     * This method compares two strings
     * @param value1 first string
     * @param value2 second string
     * @return returns true or false, depending on each implementation
     */
    boolean satisfied(String value1, String value2);
}
