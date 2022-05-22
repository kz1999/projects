package hr.fer.oprpp1.hw04.db;

/**
 * This interface represents a strategy for retreiving specific field from student records
 */
public interface IFieldValueGetter {
    /**
     * This method returns a specific field from the student record
     * @param record student record
     * @return specific field from record
     */
    String get(StudentRecord record);
}
