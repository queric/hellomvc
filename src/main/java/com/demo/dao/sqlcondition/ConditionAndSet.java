package com.demo.dao.sqlcondition;


import org.springframework.util.Assert;

/**
 * “与”关系的ConditionSet
 *
 */
public class ConditionAndSet extends ConditionSet<ConditionAndSet> {

    private static final long serialVersionUID = -7785215850623407374L;

    /**
     * 静态构造方法
     * @param key
     * @param value
     * @return
     */
    public static ConditionAndSet newInstance(String key, Object value) {
        return new ConditionAndSet(key, value);
    }

    /**
     * 静态构造方法
     * @return
     */
    public static ConditionAndSet newInstance() {
        return new ConditionAndSet();
    }

    /**
     * 构造函数
     */
    public ConditionAndSet() {
        super();
    }
    /**
     * 构造函数，构建一个条件
     * @param key
     * @param value
     */
    private ConditionAndSet(String key, Object value) {
        super(key, value);
    }
}
