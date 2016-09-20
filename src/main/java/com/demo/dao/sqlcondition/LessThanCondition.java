package com.demo.dao.sqlcondition;

public class LessThanCondition extends CompareCondition {
    /**
     * Creates a new LessThanCondition object.
     *
     * @param propertyName
     * @param value
     */
    public LessThanCondition(String propertyName, Object value) {
        super(propertyName, value);
    }
}
