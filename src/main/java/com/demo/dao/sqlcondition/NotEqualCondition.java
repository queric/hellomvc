package com.demo.dao.sqlcondition;

public class NotEqualCondition extends CompareCondition {
    /**
     * Creates a new NotEqualCondition object.
     *
     * @param propertyName
     * @param value
     */
    public NotEqualCondition(String propertyName, Object value) {
        super(propertyName, value);
    }
}
