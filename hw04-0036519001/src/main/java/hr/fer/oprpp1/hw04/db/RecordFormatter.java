package hr.fer.oprpp1.hw04.db;

import java.util.List;

/**
 * This class serves as a formatter for printing data from database
 */
public class RecordFormatter {
    /**
     * This method prints formatted data from database
     * @param records data from database
     */
    public static void format(List<StudentRecord> records) {
        int maxFirst = -1;
        int maxLast = -1;

        for (StudentRecord s : records) {
            if (s.getFirstName().length() > maxFirst) {
                maxFirst = s.getFirstName().length();
            }
            if (s.getLastName().length() > maxLast) {
                maxLast = s.getLastName().length();
            }
        }

        maxFirst += 2;
        maxLast += 2;

        if (records.size() == 0) {
            return;
        }

        separator(maxFirst, maxLast);

        for (StudentRecord s : records) {
            System.out.print("| " + s.getJmbag() + " | " + s.getLastName());
            int lastN = maxLast - s.getLastName().length();
            for (int i = 1; i < lastN; i++) {
                System.out.print(" ");
            }
            System.out.print("| " + s.getFirstName());
            int firstN = maxFirst - s.getFirstName().length();
            for (int i = 1; i < firstN; i++) {
                System.out.print(" ");
            }
            System.out.print("| " + s.getFinalGrade() + " |\n");
        }

        separator(maxFirst, maxLast);
    }

    /**
     * This method prints the separator line for formatting
     * @param maxFirst longest first name
     * @param maxLast longest last name
     */
    private static void separator(int maxFirst, int maxLast) {
        System.out.print('+');
        for (int i = 0; i < 12; i++) {
            System.out.print("=");
        }
        System.out.print('+');
        for (int i = 0; i < maxLast; i++) {
            System.out.print("=");
        }
        System.out.print('+');
        for (int i = 0; i < maxFirst; i++) {
            System.out.print("=");
        }

        System.out.print("+===+\n");
    }

}
