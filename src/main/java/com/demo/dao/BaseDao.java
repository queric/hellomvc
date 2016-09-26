package com.demo.dao;

import com.demo.util.PageBean;
import com.demo.dao.sqlcondition.ConditionSet;
import com.demo.dao.sqlcondition.OrderCondition;
import org.hibernate.criterion.DetachedCriteria;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Queric on 2016/7/29.
 */
public interface BaseDao<T> {
    /**
     * 保存对象
     */
    void save(T object);
    /**
     * 保存对象
     */
    T merge(T object);
    /**
     * 删除对象
     */
    void delete(T object);
    /**
     * 更新对象
     */
    void update(T object);
    /**
     * 从session中移除
     */
    void evict(T object);
    /**
     * 根据ID查找
     *
     * @param id
     *            Serializable
     * @return T
     */
    T findById(Serializable id);
    /**
     * 保存或更新(如果对象已存在)
     *
     * @param object
     *            T
     */
    void saveOrUpdate(T object);
    /**
     * 批量删除
     *
     * @param objects
     *            Collection
     */
    void deleteBatch(Collection<T> objects);
    /**
     * 批量保存或更新
     *
     * @param objects
     *            Collection
     */
    //void saveOrUpdateAll(Collection<T> objects);
    /**
     * 查找所有对象
     *
     * @param pageParam
     *            PageBean 分页查找参数，开始页，与最大记录条数
     * @return Collection
     */
    List<T> findAll(PageBean... pageParam);
    /**
     * 查找所有对象
     *
     * @param orderByProperties
     *            OrderBy[] 需要排序的属性名，
     * @param pageParam
     *            Integer[] 分页查找参数，开始页，与最大记录条数
     * @return Collection
     */
    List<T> findAll(List<OrderCondition> orderByProperties, PageBean... pageParam);
    /**
     * 查找和所给对象相似的所有对象，相似表示所给对象中非空的属性值相同的对象
     *
     * @param object
     *            T
     * @param pageParam
     *            Integer[] 分页查找参数，开始页，与最大记录条数
     * @return Collection
     */
    List<T> findByExample(T object, PageBean... pageParam);
    /**
     * 根据属性查找，属性对应的值可以支持含有like条件，以及是sqlcondition下的条件对象。
     *
     * @param propertyName
     *            String
     * @param value
     *            Object
     * @param pageParam
     *            Integer[] 分页查找参数，开始页，与最大记录条数
     * @return Collection
     */
    List<T> findByProperty(String propertyName, Object value, PageBean... pageParam);
    /**
     * 根据单个属性查找,并指定排序列，属性对应的值可以支持含有like，以及是sqlcondition下的对象。
     *
     * @param propertyName
     *            String
     * @param value
     *            Object
     * @param orderByProperties
     *            OrderBy[] 需要排序的属性名，
     * @param pageParam
     *            Integer[] 分页查找参数，开始页，与最大记录条数
     * @return Collection
     */
    List<T> findByProperty(String propertyName, Object value, List<OrderCondition> orderByProperties, PageBean... pageParam);
    /**
     * 根据属性查找,并指定排序列，属性对应的值可以支持含有like条件，以及是sqlcondition下的对象。
     *
     * @param properties
     *            Map
     * @param orderByProperties
     *            OrderBy[] 需要排序的属性名，
     * @param pageParam
     *            Integer[] 分页查找参数，开始页，与最大记录条数
     * @return Collection
     */
    List<T> findByProperties(ConditionSet properties, List<? extends OrderCondition> orderByProperties, PageBean... pageParam);
    /**
     * 根据属性查找,并指定排序列，属性对应的值可以支持含有like条件，以及是sqlcondition下的对象。
     *
     * @param properties
     *            Map
     * @param orderByProperty OrderBy 需要排序的属性名
     * @param pageParam
     *            Integer[] 分页查找参数，开始页，与最大记录条数
     * @return Collection
     */
    List<T> findByProperties(ConditionSet properties, OrderCondition orderByProperty, PageBean... pageParam);
    /**
     * 根据属性查找，属性对应的值可以支持含有like条件，如果是sqlcondition.Like的类型。
     *
     * @param conditionSet
     *            Map
     * @param pageParam
     *            Integer[] 分页查找参数，开始页，与最大记录条数
     * @return Collection
     */
    List<T> findByProperties(ConditionSet conditionSet, PageBean... pageParam);
    /**
     * 根据属性查找,并指定排序列，属性对应的值可以支持含有like条件，以及是sqlcondition下的对象。
     *
     * @param properties
     *            Map
     * @return Collection
     */
    T findUniqueByProperties(ConditionSet properties);
    /**
     * 根据属性查询记录个数
     * @param properties
     * @return
     */
    Long findCountByProperties(ConditionSet properties);
    /**
     * 执行hql语句查询，如果上面的那些方法都不可用的话，如果不带totalCountHql，本方法不计算总记录数
     *
     * @param hql
     *            String
     *
     * @param pageParam
     *            Integer[]
     * @return Collection
     */
    List queryByHQL(String hql, String totalCountHql, PageBean... pageParam);
    /**
     * 执行hql语句查询，如果上面的那些方法都不可用的话
     *
     * @param hql
     *            String
     * @param queryParam
     *            Integer[]
     * @return Collection
     */
    List queryByHQL(String hql, Map<String, Object> queryParam);
    /**
     * 跟与hql和参数查询,如果不带totalCountHql，本方法不计算总记录数
     * @param hql
     * @param queryParam
     * @param pageParam
     * @return
     */
    List queryByHQL(final String hql, final Map<String, Object> queryParam, String totalCountHql, final PageBean... pageParam);
    /**
     * 执行hql语句查询;hql语句中变量为?;根据动态参数顺序传入查询参数
     */
    List queryByHqlWithParams(final String hql, final Object... values);
    /**
     * 提供sql和参数查询,如果不带totalCountSql，本方法不计算总记录数
     * @param sql
     * @param queryParam
     * @param totalCountHql
     * @param entities(实体映射)
     * @param pageParam
     * @return
     */
    public List queryBySQL(final String sql, final Map<String, Object> queryParam, final String totalCountHql, final Map<String, Class<?>> entities, final PageBean... pageParam);
    /**
     * 提供sql和参数查询,如果不带totalCountSql，本方法不计算总记录数
     * @author lichang
     * @param sql
     * @param queryParam
     * @param totalCountSql
     * @param pageParam
     * @return
     */
    List queryBySQL(final String sql, final Map<String, Object> queryParam, String totalCountSql, final PageBean... pageParam);
    /**
     * 执行更新的hql
     *
     * @param hql
     *            String
     */
    Integer executeUpdateHql(String hql);
    Integer executeUpdateHql(String hql, Map<String, Object> params);
    Integer executeUpdateSql(final String sql, final Map<String, Object> params);
    /**
     * 根据属性值，批量删除
     * @param propertyName
     * @param value
     * @return
     */
    public Integer deleteByProperty(String propertyName, Object value);
    /**
     * 根据属性的值批量删除
     *
     * @param props
     * @return
     */
    Integer deleteByProperties(Map<String, Object> props);
    /**
     * 根据特殊的条件查询
     * @param properties                   属性名称数组，属性名称支持段式："属性名OPERATOR属性值",OPERATOR可以为"=,>,<",字符串类型可以带%查询，表示匹配任意
     * @param orderBy                      按属性排序， 属性名：0|1，0降序，1升序
     * @param pageBean
     */
    List<T> findByCommonCondition(String[] properties, String[] orderBy, PageBean... pageBean);
    List<T> findByCriteria(DetachedCriteria criteria);
    List<T> findByCriteriaWithPages(final DetachedCriteria criteria, final PageBean... pageBean);
}
