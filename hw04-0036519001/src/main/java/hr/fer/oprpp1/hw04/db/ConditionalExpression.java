package hr.fer.oprpp1.hw04.db;

/**
 * This class models a conditional expression. It consists of a record field, value than needs to be compared and
 * of a concrete strategy
 */
public class ConditionalExpression {
    private IFieldValueGetter field;
    private String value;
    private IComparisonOperator comparison;

    public ConditionalExpression(IFieldValueGetter field, String value, IComparisonOperator comparison) {
        this.field = field;
        this.value = value;
        this.comparison = comparison;
    }

    public IFieldValueGetter getField() {
        return field;
    }

    public String getValue() {
        return value;
    }

    public IComparisonOperator getComparison() {
        return comparison;
    }
}
