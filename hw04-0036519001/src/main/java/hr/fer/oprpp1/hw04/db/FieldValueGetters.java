package hr.fer.oprpp1.hw04.db;

/**
 * This class consists of three concrete strategies for retrieving fields from database
 */
public class FieldValueGetters {
    /**
     * This implementation returns the firstName field from the record
     */
    public static IFieldValueGetter FIRST_NAME = (record -> {
        if (record == null) {
            throw new NullPointerException("Record is null");
        } else {
            return record.getFirstName();
        }
    });
    /**
     * This implementation returns the lastName field from the record
     */
    public static IFieldValueGetter LAST_NAME = (record -> {
        if (record == null) {
            throw new NullPointerException("Record is null");
        } else {
            return record.getLastName();
        }
    });
    /**
     * This implementation returns the jmbag field from the record
     */
    public static IFieldValueGetter JMBAG = (record -> {
        if (record == null) {
            throw new NullPointerException("Record is null");
        } else {
            return record.getJmbag();
        }
    });
}
