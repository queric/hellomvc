package com.demo.dao.sqlcondition;

public class AscOrder extends OrderCondition {
    private static final long serialVersionUID = -2923948637027588374L;

    /**
     * Creates a new AscOrder object.
     *
     * @param propertyName
     */
    public AscOrder(String propertyName) {
        super(propertyName);
        super.setAscOrder(true);
    }

    /**
     * Creates a new AscOrder object.
     */
    public AscOrder() {
    }
}
