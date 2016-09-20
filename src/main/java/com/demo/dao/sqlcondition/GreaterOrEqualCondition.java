package com.demo.dao.sqlcondition;

public class GreaterOrEqualCondition extends CompareCondition {
    /**
     * Creates a new LessOrEqualCondition object.
     *
     * @param propertyName
     * @param value
     */
    public GreaterOrEqualCondition(String propertyName, Object value) {
        super(propertyName, value);
    }
}
