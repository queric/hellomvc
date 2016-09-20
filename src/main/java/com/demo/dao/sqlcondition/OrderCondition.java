package com.demo.dao.sqlcondition;

import org.apache.commons.lang.builder.ToStringBuilder;

public class OrderCondition implements java.io.Serializable {
    private static final long serialVersionUID = 131982036485132446L;
    private String propertyName;
    private boolean ascOrder;

    /**
     * Creates a new OrderCondition object.
     */
    public OrderCondition() {
    }

    /**
     * 只有子类或本包可以使用,子类需说明orderby顺序
     *
     * @param property String
     */
    protected OrderCondition(String property) {
        this.propertyName = property;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public boolean isAscOrder() {
        return ascOrder;
    }

    public void setAscOrder(boolean ascOrder) {
        this.ascOrder = ascOrder;
    }

    /**
     * @see Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("propertyName", this.propertyName)
                .append("ascOrder", this.ascOrder).toString();
    }
}
