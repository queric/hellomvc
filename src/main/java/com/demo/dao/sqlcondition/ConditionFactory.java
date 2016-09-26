package com.demo.dao.sqlcondition;

/**
 * 条件工厂
 *
 * @author liming
 */
public final class ConditionFactory {

    private ConditionFactory() {
    }

    /**
     * 获取降序排列某属性
     *
     * @param propertyName 属性名
     *
     * @return OrderCondition
     */
    public static OrderCondition orderByDesc(String propertyName) {
        return new DescOrder(propertyName);
    }

    /**
     * 获取升序排列某属性
     *
     * @param propertyName 属性名
     *
     * @return OrderCondition
     */
    public static OrderCondition orderByAsc(String propertyName) {
        return new AscOrder(propertyName);
    }

    /**
     * 属性值大于某个值的对象
     *
     * @param propertyName 属性名
     * @param value        大于的值
     *
     * @return CompareCondition
     */
    public static CompareCondition greaterThan(String propertyName, Object value) {
        return new GreaterCondition(propertyName, value);
    }

    /**
     * 属性值大于等于某个值的对象
     *
     * @param propertyName 属性名
     * @param value        大于等于的值
     *
     * @return CompareCondition
     */
    public static CompareCondition greaterOrEqual(String propertyName, Object value) {
        return new GreaterOrEqualCondition(propertyName, value);
    }

    /**
     * 属性值不等于某个值的对象
     *
     * @param propertyName 属性名
     * @param value        不等于的值
     *
     * @return CompareCondition
     */
    public static CompareCondition notEqual(String propertyName, Object value) {
        return new NotEqualCondition(propertyName, value);
    }

    /**
     * 属性值等于某个值的对象
     * @param propertyName 属性名
     * @param value 等于的值
     * @return
     */
    public  static CompareCondition equal(String propertyName, Object value){
        return new EqualCondition(propertyName,value);
    }

    /**
     * 属性值小于于某个值的对象
     *
     * @param propertyName 属性名
     * @param value        小于等于的值
     *
     * @return CompareCondition
     */
    public static CompareCondition lessThan(String propertyName, Object value) {
        return new LessThanCondition(propertyName, value);
    }

    /**
     * 属性值小于于某个值的对象
     *
     * @param propertyName 属性名
     * @param value        小于的值
     *
     * @return CompareCondition
     */
    public static CompareCondition lessOrEqual(String propertyName, Object value) {
        return new LessOrEqualCondition(propertyName, value);
    }

    /**
     * 属性值介于两个值之间的对象
     *
     * @param propertyName 属性名
     * @param minValue     小值
     * @param maxValue     大值
     *
     * @return CompareCondition
     */
    public static CompareCondition between(String propertyName, Object minValue, Object maxValue) {
        return new BetweenCondition(propertyName, minValue, maxValue);
    }

    /**
     * 创建一个属性值等于指定值的条件集
     *
     * @param propertyName 属性名
     * @param value        属性值
     *
     * @return 与条件集
     */
    public static ConditionAndSet and(String propertyName, Object value) {
        return ConditionAndSet.newInstance(propertyName, value);
    }

    public static ConditionAndSet and() {
        return ConditionAndSet.newInstance();
    }

    /**
     * 创建一个包含初始条件“属性名等于指定值”的“或”条件集
     *
     * @param propertyName 属性名
     * @param value        属性值
     *
     * @return “或”条件集
     */
    public static ConditionOrSet or(String propertyName, Object value) {
        return ConditionOrSet.newInstance(propertyName, value);
    }

    /**
     * 创建一个空的ConditionOrSet条件集
     * @return 空的“或”条件集
     */
    public static ConditionOrSet or() {
        return ConditionOrSet.newInstance();
    }

}