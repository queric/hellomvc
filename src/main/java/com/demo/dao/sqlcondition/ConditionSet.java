package com.demo.dao.sqlcondition;

import org.springframework.util.Assert;

import java.util.*;


/**
 * dao使用，用于衔接查询条件拼装和criteria生成
 *
 */
public abstract class ConditionSet {

    private static final long serialVersionUID = -110803228763256709L;
    private List<Object> values=new ArrayList<Object>();  //从Set改成List使条件按put顺序来
    //private Set<Object> values = new HashSet<Object>();
    /**
     * groupBy属性列
     */
    private List<String> groups = new ArrayList<String>();

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }
    
    public void addGroupBy(String propertyName) {
        groups.add(propertyName);
    }
    
    public ConditionSet() {
    }
    
    public ConditionSet(String key, Object value) {
        this.addCompareCondition(new EqualCondition(key, value));
    }
    
    public ConditionSet(CompareCondition condition) {
        this.addCompareCondition(condition);
    }

    /**
     * put一个EqualCondition并返回当前ConditionAndSet，可连续对此进行操作
     * @param key 属性名
     * @param value 属性值
     * @return “与”条件集
     */
    public final ConditionSet put(String key, Object value) {
        this.addCompareCondition(new EqualCondition(key, value));
        return this;
    }

    /**
     * put一个CompareCondition并返回当前ConditionAndSet，可连续对此进行操作
     * @param compareCondition
     * @return
     */
    public final ConditionSet put(CompareCondition compareCondition){
        Assert.notNull(compareCondition, "待添加的CompareCondition类型不能为null");
        this.addCompareCondition(compareCondition);
        return this;
    }

    /**
     * put一个ConditionSet并返回当前ConditionAndSet，可连续对此进行操作
     * @param conditionSet
     * @return
     */
    public final ConditionSet put(ConditionSet conditionSet){
        Assert.notNull(conditionSet, "待添加的ConditionSet类型不能为null");
        this.addConditionSet(conditionSet);
        return this;
    }

    private void addCompareCondition(CompareCondition condition) {
        values.add(condition);
    }
    
    private void addConditionSet(ConditionSet conditionSet) {
        // 增加自己同类型时，自动打平合并
        if (this.getClass().equals(conditionSet.getClass())) {
            for (Iterator<Object> it = conditionSet.getValues().iterator(); it.hasNext();) {
                Object obj = it.next();
                if (obj instanceof CompareCondition) {
                    this.addCompareCondition((CompareCondition) obj);
                }
                else if (obj instanceof ConditionSet) {
                    this.addConditionSet((ConditionSet) obj);
                }
                else {
                    throw new RuntimeException("待增加的ConditionSet中遇到了意外的Condition类型");
                }
            }
        }
        else {
            values.add(conditionSet);
        }
    }

    /**
     * 返回所有的集合中的对象信息。普通的列与值通过equal条件返回
     * @return
     */
    public List<Object> getValues() {
        return this.values;
        
    }

    public int size() {
        return this.values.size();
    }  
}
