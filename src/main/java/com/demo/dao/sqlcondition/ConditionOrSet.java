package com.demo.dao.sqlcondition;

/**
 * "或"关系的ConditionSet
 *
 */
public class ConditionOrSet extends ConditionSet {

    private static final long serialVersionUID = 8050928780754095828L;

    /**
     * 静态构造方法
     * @param key
     * @param value
     * @return
     */
    protected static ConditionOrSet newInstance(String key, Object value) {
        return new ConditionOrSet(key, value);
    }
    
    /**
     * 静态构造方法
     */
    protected static ConditionOrSet newInstance() {
        return new ConditionOrSet();
    }

    /**
     * 构造函数
     */
    public ConditionOrSet() {
        super();
    }
    /**
     * 构造函数,构建一个带有CompareCondtion条件的ConditionOrSet
     */
    public ConditionOrSet(CompareCondition compareCondition) {
        super(compareCondition);
    }
    /**
     * 构造函数，构建一个条件
     * @param key
     * @param value
     */
    public ConditionOrSet(String key, Object value) {
        super(key, value);
    }
}
