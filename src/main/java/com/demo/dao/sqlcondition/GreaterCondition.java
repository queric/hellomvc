package com.demo.dao.sqlcondition;

public class GreaterCondition extends CompareCondition {
    /**
     * Creates a new GreaterCondition object.
     *
     * @param propertyName
     * @param value
     */
    public GreaterCondition(String propertyName, Object value) {
        super(propertyName, value);
    }
}
