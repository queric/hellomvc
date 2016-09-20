package com.demo.dao.sqlcondition;

public class DescOrder extends OrderCondition {
    private static final long serialVersionUID = -2473406369581360611L;

    /**
     * Creates a new DescOrder object.
     *
     * @param propertyName
     */
    public DescOrder(String propertyName) {
        super(propertyName);
        super.setAscOrder(false);
    }

    /**
     * Creates a new DescOrder object.
     */
    public DescOrder() {
    }
}
