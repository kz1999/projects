package hr.fer.oprpp1.hw04.db;

/**
 * This interface serves as a tool for filtering student record
 */
public interface IFilter {
    /**
     * This method either accepts or declines a student record based on each interface implementation
     * @param record record to be accepted
     * @return true if accepted, false otherwise
     */
    boolean accepts(StudentRecord record);
}
