package com.demo.dao.sqlcondition;

public class EqualCondition extends CompareCondition {
    public EqualCondition(String propertyName, Object value) {
        super(propertyName, value);
    }
}
