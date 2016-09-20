package com.demo.dao.sqlcondition;

public class NotInCondition  extends CompareCondition {
    /**
     * Creates a new CompareCondition object.
     *
     * @param propertyName
     */
    public NotInCondition(String propertyName, Object obj) {
        super(propertyName, obj);
    }
}
