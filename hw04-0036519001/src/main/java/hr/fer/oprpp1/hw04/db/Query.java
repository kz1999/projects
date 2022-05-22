package hr.fer.oprpp1.hw04.db;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class represent a program that offers queries into student records database
 */
public class Query {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(
                Paths.get("src/main/Resources/data.txt"),
                StandardCharsets.UTF_8
        );
        StudentDatabase db = new StudentDatabase(lines);
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            try {
                String in = sc.nextLine();
                if (in.startsWith("query")) {
                    in = in.replaceAll("query", "");
                    query(in, db);
                } else if (in.equals("quit")) {
                    System.out.println("Goodbye");
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();

    }

    public static void query(String in, StudentDatabase db) {
        QueryParser parser = new QueryParser(in);
        List<StudentRecord> list = new ArrayList<>();
        if (parser.isDirectQuery()) {
            StudentRecord r = db.forJMBAG(parser.getQueriedJMBAG());
            System.out.println("query" + in);
            list.add(r);


        } else {
            list.addAll(db.filter(new QueryFilter(parser.getQuery())));
            System.out.println("query" + in);
        }
        RecordFormatter.format(list);
        System.out.println("Records selected: " + list.size());
    }
}
