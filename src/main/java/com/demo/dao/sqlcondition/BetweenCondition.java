package com.demo.dao.sqlcondition;


public class BetweenCondition extends CompareCondition {
    /**
     * Creates a new BetweenCondition object.
     *
     * @param propertyName
     * @param minValue
     * @param maxValue
     */
    public BetweenCondition(String propertyName, Object minValue, Object maxValue) {
        super(propertyName, minValue, maxValue);
    }
}
