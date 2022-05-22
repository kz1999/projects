package hr.fer.oprpp1.hw04.db;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Disabled;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class StudentDatabaseTest {

    @Test
    public void firstTest() throws IOException {
        List<String> in = Files.readAllLines(Path.of("src/main/Resources/data.txt"));
        StudentDatabase db = new StudentDatabase(in);

        System.out.println(db.forJMBAG("0000000063").getFinalGrade() + " " + db.forJMBAG("0000000063").getLastName());
    }

    @Test
    public void filterThatOnlyReturnsFalse() throws IOException {
        List<String> in = Files.readAllLines(Path.of("src/main/Resources/data.txt"));
        StudentDatabase db = new StudentDatabase(in);

        List<StudentRecord> fil = db.filter((record) -> false);
        Assertions.assertEquals(0, fil.size());
    }

    @Test
    public void filterThatOnlyReturnsTrue() throws IOException {
        List<String> in = Files.readAllLines(Path.of("src/main/Resources/data.txt"));
        StudentDatabase db = new StudentDatabase(in);

        List<StudentRecord> fil = db.filter((record) -> true);
        Assertions.assertEquals(in.size(), fil.size());
    }

    @Test
    public void filterThatOnlyReturnsGradeFive() throws IOException {
        List<String> in = Files.readAllLines(Path.of("src/main/Resources/data.txt"));
        StudentDatabase db = new StudentDatabase(in);

        List<StudentRecord> fil = db.filter((record) -> record.getFinalGrade().equals("5"));
        Assertions.assertEquals(15, fil.size());
    }

    @Test
    public void comparisonTest() throws IOException {
        List<String> in = Files.readAllLines(Path.of("src/main/Resources/data.txt"));
        StudentDatabase db = new StudentDatabase(in);

        IComparisonOperator oper = ComparisonOperator.LESS;
        System.out.println(oper.satisfied("Ana", "Jasna"));//true
        oper = ComparisonOperator.LIKE;
        System.out.println(oper.satisfied("Zagreb", "Aba*"));  // false
        System.out.println(oper.satisfied("AAA", "AA*AA"));    // false
        System.out.println(oper.satisfied("AAAA", "AA*AA"));   // true
        //System.out.println(oper.satisfied("AAAA", "AA*AA*"));
        System.out.println(oper.satisfied("AAAA", "AAA*"));   // true
        System.out.println(oper.satisfied("AAAA", "*AA"));   // true
        System.out.println(oper.satisfied("Branko", "Bran*"));
    }


}
