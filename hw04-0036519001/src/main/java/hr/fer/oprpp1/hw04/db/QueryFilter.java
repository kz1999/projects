package hr.fer.oprpp1.hw04.db;

import java.util.List;

/**
 * This class represents a filter for queries into student records.
 */
public class QueryFilter implements IFilter {
    List<ConditionalExpression> list;

    public QueryFilter(List<ConditionalExpression> list) {
        this.list = list;
    }
    @Override
    public boolean accepts(StudentRecord record) {
        boolean accepted = false;
        for (ConditionalExpression e : list) {
            String studValue = e.getField().get(record);
            if (e.getComparison().satisfied(studValue, e.getValue())) {
                accepted = true;
            } else {
                accepted = false;
            }
        }
        return accepted;
    }
}
