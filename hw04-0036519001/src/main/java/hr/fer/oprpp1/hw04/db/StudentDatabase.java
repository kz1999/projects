package hr.fer.oprpp1.hw04.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents a database that contains student records.
 */
public class StudentDatabase {
    public Map<String, StudentRecord> studentMap = new HashMap<>();
    private List<StudentRecord> studentList = new ArrayList<>();

    /**
     * This constructor creates instances of class StudentRecord for each line of passed data and then stores them
     * into internal storage.
     * @param data student data
     * @throws Error if grade is not between 1 and 5, jmbag is duplicate or number of args in one line is different
     * than 4
     */
    public StudentDatabase(List<String> data) {
        String[] temp;

        for (String s : data) {
            temp = s.split("\t");
            if (studentMap.containsKey(temp[0]))
                throw new Error("Invalid input data. Duplicate jmbag.");

            int grade = Integer.parseInt(temp[temp.length - 1]);
            if (grade < 1 || grade > 5)
                throw new Error("Invalid input data. Grade out of range");

            if (temp.length != 4) {
                throw new Error("Invalid input data. Number of arguments invalid, must be 4 per line");
            }

            String jmbag = temp[0];
            String lastName = temp[1];
            String firstName = temp[2];
            String finalGrade = temp[3];

            StudentRecord record = new StudentRecord(jmbag, lastName, firstName, finalGrade);
            studentMap.put(jmbag, record);
            studentList.add(record);
        }
    }

    /**
     * This method returns the record for the student with the specified jmbag
     * @param jmbag jmbag of the student with the desired record
     * @return StudentRecord instance of the student with the desired jmbag
     */
    public StudentRecord forJMBAG(String jmbag) {
        return studentMap.get(jmbag);
    }

    /**
     * This method passes over every student record it has stored, calls the accept method of interface IFilter and
     * adds every record which is accepted in the accept method.
     * @param filter instance of interface IFilter
     * @return list of records that were accepted by the filter
     */
    public List<StudentRecord> filter(IFilter filter) {
        List<StudentRecord> temp = new ArrayList<>();

        for (StudentRecord rec : studentList) {
            if (filter.accepts(rec))
                temp.add(rec);
        }
        return temp;
    }
}
