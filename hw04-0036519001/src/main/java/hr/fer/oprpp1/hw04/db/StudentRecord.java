package hr.fer.oprpp1.hw04.db;

import java.util.Objects;

/**
 * This class represents a record of a student which contains his jmbag, full name and final grade
 */
public class StudentRecord {
    private String jmbag;
    private String lastName;
    private String firstName;
    private String finalGrade;

    public StudentRecord(String jmbag, String lastName, String firstName, String finalGrade) {
        this.jmbag = jmbag;
        this.lastName = lastName;
        this.firstName = firstName;
        this.finalGrade = finalGrade;
    }

    public String getJmbag() {
        return jmbag;
    }

    String getLastName() {
        return lastName;
    }

    String getFirstName() {
        return firstName;
    }

    String getFinalGrade() {
        return finalGrade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentRecord that = (StudentRecord) o;
        return jmbag.equals(that.jmbag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jmbag);
    }
}
