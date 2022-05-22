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

public class QueryParserTest {

    @Test
    public void queryParserFirstTest() {
        QueryParser parser = new QueryParser(" jmbag       =\"0123456789\"    ");
        System.out.println(parser.data);
        System.out.println(parser.isDirectQuery());
        System.out.println(parser.data);
        System.out.println(parser.getQueriedJMBAG());
        System.out.println(parser.data);

        /*QueryParser qp2 = new QueryParser("jmbag=\"0123456789\" and lastName>\"J\"");
        System.out.println("isDirectQuery(): " + qp2.isDirectQuery()); // false
        System.out.println(qp2.data);*/
        // System.out.println(qp2.getQueriedJMBAG()); // would throw!
        //System.out.println("size: " + qp2.getQuery().size()); // 2
    }

}
