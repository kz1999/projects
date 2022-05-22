package hr.fer.oprpp1.hw04.db;

/**
 * This class offers various public comparison operators for comparing two strings
 */
public class ComparisonOperator {
    /**
     * This operator checks if the first string is less than the second one
     */
    public static IComparisonOperator LESS = (s1, s2) -> s1.compareTo(s2) < 0;
    /**
     * This operator checks if the first string is less or equal than the second one
     */
    public static IComparisonOperator LESS_OR_EQUALS = (s1, s2) -> s1.compareTo(s2) <= 0;
    /**
     *This operator checks if the first string is greater than the second one
     */
    public static IComparisonOperator GREATER = (s1, s2) -> s1.compareTo(s2) > 0;
    /**
     * This operator checks if the first string is greater or equal than the second one
     */
    public static IComparisonOperator GREATER_OR_EQUALS = (s1, s2) -> s1.compareTo(s2) >= 0;
    /**
     * This operator checks if the two strings are equal
     */
    public static IComparisonOperator EQUALS = (s1, s2) -> s1.compareTo(s2) == 0;
    /**
     * This operator checks if the two strings are not equal
     */
    public static IComparisonOperator NOT_EQUALS = (s1, s2) -> s1.compareTo(s2) != 0;
    /**
     * This operator checks it the strings matches the passed pattern
     */
    public static IComparisonOperator LIKE = (s1, s2) -> {
        int count = 0;
        for (int i = 0; i < s2.length(); i++) {
            if (s2.charAt(i) == '*') {
                count++;
                if (count > 1)
                    throw new IllegalArgumentException("Can't put more than one wildcard char");
            }
        }
        s2 = s2.replaceAll("\\*", ".*");
        return s1.matches(s2);
    };
}
