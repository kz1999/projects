package hr.fer.oprpp1.hw04.db;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Disabled;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FieldValueGettersTest {
    @Test
    public void fieldValueFirstTest() throws IOException {
        List<String> in = Files.readAllLines(Path.of("src/main/Resources/data.txt"));
        StudentDatabase db = new StudentDatabase(in);

        StudentRecord record = db.forJMBAG("0000000046");
        System.out.println("First name: " + FieldValueGetters.FIRST_NAME.get(record));
        System.out.println("Last name: " + FieldValueGetters.LAST_NAME.get(record));
        System.out.println("JMBAG: " + FieldValueGetters.JMBAG.get(record));
    }
}
