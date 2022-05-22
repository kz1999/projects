package hr.fer.oprpp1.hw04.db;

import java.util.ArrayList;
import java.util.List;

/**
 * This class returns StudentDatabase queries from the passed data
 */
public class QueryParser {
    String data;
    List<ConditionalExpression> expressions;

    /**
     * This constructor initialises the private fields and then starts parsing over data
     * @param data input data
     */
    public QueryParser(String data) {
        this.data = data;
        this.data = this.data.trim();
        this.data = this.data.replaceAll(" ", "");
        this.expressions = new ArrayList<>();
        parseData();
    }

    /**
     * This method checks if the query is direct
     * @return true if query is direct, false otherwise
     */
    boolean isDirectQuery() {
        return data.matches("jmbag=\"\\d{10}\"");
    }

    /**
     * This method returns the queried jmbag from the direct query
     * @return jmbag
     * @throws IllegalStateException if the query was not direct
     */
    String getQueriedJMBAG() {
        if (!this.isDirectQuery())
            throw new IllegalStateException("Query was not a direct one");

        String temp = data.replaceAll("jmbag=", "");

        return temp.replaceAll("\"", "");
    }

    List<ConditionalExpression> getQuery() {
        return expressions;
    }

    /**
     * This method parses the passed data and stores queries into the private list
     */
    private void parseData() {
        String temp = data;
        Object[] exp = new Object[3];

        int counter = 0;
        while (counter < temp.length()) {
            if (Character.isLetter(temp.charAt(counter))) {
                String word = "";
                word += (temp.charAt(counter));
                counter++;
                boolean correct = false;
                while (counter < temp.length() && Character.isLetter(temp.charAt(counter))) {
                    word += (temp.charAt(counter));
                    counter++;
                    if (word.equalsIgnoreCase("and")) {
                        word = "";
                    } else if (word.equals("lastName")) {
                        exp[0] = FieldValueGetters.LAST_NAME;
                        correct = true;
                        break;
                    } else if (word.equals("firstName")) {
                        exp[0] = FieldValueGetters.FIRST_NAME;
                        correct = true;
                        break;
                    } else if (word.equals("jmbag")) {
                        exp[0] = FieldValueGetters.JMBAG;
                        correct = true;
                        break;
                    } else if (word.equals("LIKE")) {
                        exp[2] = ComparisonOperator.LIKE;
                        correct = true;
                        break;
                    }
                }
                if (!correct) {
                    //System.out.println("Incorrect word");
                    throw new IllegalArgumentException("Illegal field");
                }

            } else if (temp.charAt(counter) == '=') {
                counter++;
                exp[2] = ComparisonOperator.EQUALS;
            } else if (temp.charAt(counter) == '<') {
                if (temp.charAt(counter + 1) == '=') {
                    counter += 2;
                    exp[2] = ComparisonOperator.LESS_OR_EQUALS;
                } else {
                    counter++;
                    exp[2] = ComparisonOperator.LESS;
                }
            } else if (temp.charAt(counter) == '>') {
                if (temp.charAt(counter + 1) == '=') {
                    counter += 2;
                    exp[2] = ComparisonOperator.GREATER_OR_EQUALS;
                } else {
                    counter++;
                    exp[2] = ComparisonOperator.GREATER;
                }
            } else if (temp.charAt(counter) == '!') {
                if (temp.charAt(counter + 1) == '=') {
                    counter += 2;
                    exp[2] = ComparisonOperator.NOT_EQUALS;
                }
            } else if (temp.charAt(counter) == '\"') {
                counter++;
                StringBuilder sb = new StringBuilder();
                while (temp.charAt(counter) != '"') {
                    sb.append(temp.charAt(counter));
                    counter++;
                }
                counter++;
                exp[1] = sb.toString();
                expressions.add(new ConditionalExpression((IFieldValueGetter) exp[0], (String) exp[1], (IComparisonOperator) exp[2]));
                exp[0] = null;
                exp[1] = null;
                exp[2] = null;

            } else if (temp.charAt(counter) == '\t') {
                counter++;
            } else {
                throw new IllegalArgumentException("Illegal arguments");
            }
        }
    }
}
