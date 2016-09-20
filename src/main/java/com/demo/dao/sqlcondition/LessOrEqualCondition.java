package com.demo.dao.sqlcondition;

public class LessOrEqualCondition extends CompareCondition {
    /**
     * Creates a new LessOrEqualCondition object.
     *
     * @param propertyName
     * @param value
     */
    public LessOrEqualCondition(String propertyName, Object value) {
        super(propertyName, value);
    }
}
