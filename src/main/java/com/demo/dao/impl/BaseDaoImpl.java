package com.demo.dao.impl;

import com.demo.dao.BaseDao;
import com.demo.util.PageBean;
import com.demo.dao.sqlcondition.*;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * DAO层基础实现, 主要采用HibernateTemplate实现. 各个具体的DAO通过继承此类来获得基本的CRUD功能
 * Created by Queric on 2016/7/29.
 **/
public  abstract class BaseDaoImpl<T> implements BaseDao<T>  {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    /**
     * 当参数集合大于500时，将自动转换为join
     */
    private static final int MAX_PARAM_COLLECTION_SIZE = 500;

    protected Class<T> entityClass;
    protected String entityClassName;

    public BaseDaoImpl() {
        // 通过范型反射，获取在子类中定义的entityClass.
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.entityClassName = entityClass.getSimpleName();
    }

    /**
     * 保存对象
     * @param object 待保存的对象
     */
    public void save(T object) {
        hibernateTemplate.save(object);
    }
    /**
     * merge对象
     *
     * @param object 待merge的对象
     */
    public T merge(T object) {
        return (T) hibernateTemplate.merge(object);
    }
    /**
     * 删除对象
     *
     * @param object 待删除的对象
     */
    public void delete(T object) {
        hibernateTemplate.delete(object);
        hibernateTemplate.flush();
    }
    /**
     * 更新对象
     */
    public void update(T object) {
        hibernateTemplate.update(object);
    }

    public void evict(T object) {
        hibernateTemplate.evict(object);
    }
    /**
     * 根据ID查找
     *
     * @param id 对象id
     * @return 对应的对象或null
     */
    public T findById(Serializable id) {
        return (T)hibernateTemplate.get(entityClass, id);
    }
    /**
     * 保存或更新(如果对象已存在)
     *
     * @param object 待保存或更新的对象
     */
    public void saveOrUpdate(T object) {
        hibernateTemplate.saveOrUpdate(object);
    }
    /**
     * 批量删除
     *
     * @param objects 待删除的对象集合
     */
    public void deleteBatch(Collection<T> objects) {
        hibernateTemplate.deleteAll(objects);
        hibernateTemplate.flush();
    }
    /**
     * 批量保存或更新
     *
     * param objects
     *            Collection
     */
    /*public void saveOrUpdateAll(Collection<T> objects) {
        hibernateTemplate.saveOrUpdateAll(objects);
    }*/
    public List<T> findAll(){
        DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
        return (List<T>)this.hibernateTemplate.findByCriteria(criteria);
    }
    /**
     * 查找所有对象
     *
     * @param pageParam 分页查找参数，开始页，与最大记录条数
     * @return 查找到的所有对象
     */
    public List<T> findAll(PageBean... pageParam) {
        return findAll(null, pageParam);
    }
    /**
     * 查找所有对象
     *
     * @param orderByProperties 需要排序的属性名，按数组中先后顺序order by
     * @param pageParam 分页查找参数，开始页，与最大记录条数
     * @return 查找到的所有对象
     */
    public List<T> findAll(final List<OrderCondition> orderByProperties, final PageBean... pageParam) {
        return (List) hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Criteria criteria = session.createCriteria(entityClass);
                fillOrderByAndPageParam(criteria, orderByProperties, pageParam);
                return criteria.list();
            }
        });
    }
    /**
     * 查找和所给对象相似的所有对象，相似表示所给对象中非空的属性值相同的对象
     *
     * @param example 给出的对象
     * @param pageParam 分页查找参数，开始页，与最大记录条数
     * @return Collection
     */
    public List<T> findByExample(final T example, final PageBean... pageParam) {
        return (List) hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Criteria crit = session.createCriteria(entityClass);
                Example examp = Example.create(example);
                crit.add(examp);
                fillOrderByAndPageParam(crit, null, pageParam);
                return crit.list();
            }
        });
    }
    /**
     * 根据属性查找，属性对应的值可以支持含有like条件，以及是sqlcondition下的条件对象。
     *
     * @param propertyName
     *            Map
     * @param pageParam
     *            Integer[] 分页查找参数，开始页，与最大记录条数
     * @return Collection
     */
    public List<T> findByProperty(String propertyName, Object value, PageBean... pageParam) {
        ConditionSet pv = ConditionAndSet.newInstance(propertyName, value);
        return findByProperties(pv, pageParam);
    }
    /**
     * 根据单个属性查找,并指定排序列，属性对应的值可以支持含有like，以及是sqlcondition下的对象。
     *
     * @param propertyName
     *            Map
     * @param orderByProperties
     *            OrderBy[] 需要排序的属性名，
     * @param pageParam
     *            Integer[] 分页查找参数，开始页，与最大记录条数
     * @return Collection
     */
    public List<T> findByProperty(String propertyName, Object value, List<OrderCondition> orderByProperties, PageBean... pageParam) {
        ConditionSet pv = ConditionAndSet.newInstance(propertyName, value);
        return findByProperties(pv, orderByProperties, pageParam);
    }
    /**
     * 根据属性查找,并指定排序列，属性对应的值可以支持含有like条件，
     *
     * @param properties      条件属性
     * @param orderByProperties     排序属性
     * @param pageParam     分页查找参数，开始页，与最大记录条数
     * @return Collection
     */
    public List<T> findByProperties(final ConditionSet properties, final List<? extends OrderCondition> orderByProperties, final PageBean... pageParam) {
        return (List) hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Criteria crit = convertProperties2Criteria(properties, session);
                fillOrderByAndPageParam(crit, orderByProperties, pageParam);
                return crit.list();
            }
        });
    }
    /**
     * 根据属性查找,并指定排序列，属性对应的值可以支持含有like条件，
     *
     * @param properties
     *            Map
     * @param orderByProperty
     *            String
     * @param pageParam
     *            Integer[] 分页查找参数，开始页，与最大记录条数
     * @return Collection
     */
    public List<T> findByProperties(final ConditionSet properties, final OrderCondition orderByProperty, final PageBean... pageParam) {
        List<OrderCondition> orderConditions = new ArrayList<OrderCondition>();
        if (orderByProperty != null) {
            orderConditions.add(orderByProperty);
        }
        return findByProperties(properties, orderConditions, pageParam);
    }
    /**
     * 根据属性查找，属性对应的值可以支持含有like条件的value 。
     *
     * @param properties
     *            Map
     * @param pageParam
     *            Integer[] 分页查找参数，开始页，与最大记录条数
     * @return Collection
     */
    public List<T> findByProperties(final ConditionSet properties, final PageBean... pageParam) {
        return findByProperties(properties, new ArrayList<OrderCondition>(), pageParam);
    }
    /**
     * 根据属性查找,并指定排序列，属性对应的值可以支持含有like条件，
     *
     * @param properties
     *            Map
     * @param orderByProperties
     *            String[]
     * @param pageParam
     *            Integer[] 分页查找参数，开始页，与最大记录条数
     * @return Collection
     */
    public T findUniqueByProperties(final ConditionSet properties, final List<OrderCondition> orderByProperties, final PageBean... pageParam) {
        return (T) hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Criteria crit = convertProperties2Criteria(properties, session);
                fillOrderByAndPageParam(crit, orderByProperties, pageParam);
                return crit.uniqueResult();
            }
        });
    }
    /**
     * 根据属性查找，属性对应的值可以支持含有like条件的value 。
     *
     * @param properties
     *            Map
     * @param pageParam
     *            Integer[] 分页查找参数，开始页，与最大记录条数
     * @return Collection
     */
    public T findUniqueByProperties(final ConditionSet properties, final PageBean... pageParam) {
        return findUniqueByProperties(properties, null, pageParam);
    }
    /**
     * 根据属性查询记录个数
     *
     * @param properties
     * @return
     */
    public Long findCountByProperties(final ConditionSet properties) {
        return (Long) hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Criteria crit = convertProperties2Criteria(properties, session);
                crit.setProjection(Projections.rowCount());
                List<String> groupBys = properties.getGroups();
                if (groupBys != null && !groupBys.isEmpty()) {
                    for (String propGroupBy : groupBys) {
                        crit.setProjection(Projections.groupProperty(propGroupBy));
                    }
                }
                Long totalCount = (Long) crit.uniqueResult();
                return totalCount;
            }
        });
    }
    /**
     * 执行hql语句查询，如果上面的那些方法都不可用的话
     *
     * @param hql
     *            String
     * @param pageParam
     *            Integer[]
     * @return Collection
     */
    public List queryByHQL(final String hql, final String totalCountHql, final PageBean... pageParam) {
        return this.queryByHQL(hql, null, totalCountHql, pageParam);
    }
    /**
     * 执行hql语句查询，如果上面的那些方法都不可用的话
     *
     * @param hql
     *            String
     * @param params
     *            Integer[]
     * @return Collection
     */
    public List queryByHQL(final String hql, final Map<String, Object> params) {
        return this.queryByHQL(hql, params, null);
    }

    /**
     * 执行hql语句查询;hql语句中变量为?;参数为Map集合
     * @param hql 要执行的hql语句
     * @param queryParam 查询参数
     * @param totalCountHql 计数hql语句
     * @param pageParam 分页参数封装PageBean
     * @return
     */
    public List queryByHQL(final String hql, final Map<String, Object> queryParam, final String totalCountHql, final PageBean... pageParam) {
        return (List) hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                if (queryParam != null) {
                    query = setParams(query, queryParam);
                }
                if (pageParam.length == 1 && pageParam[0] != null) {
                    if (totalCountHql != null) { // 如果带了需要查询总数的HQL，查询总数设置到pagebean中
                        Query countQuery = session.createQuery(totalCountHql);
                        if (queryParam != null) {
                            countQuery = setParams(countQuery, queryParam);
                        }
                        Integer count = Integer.parseInt(countQuery.uniqueResult() + "");
                        pageParam[0].setRecordCount(count);
                    }
                    int start = pageParam[0].getCurrentPage() - 1;
                    start = (start >= 0 ? start : 0);
                    query.setFirstResult(start * pageParam[0].getPageSize()).setMaxResults(pageParam[0].getPageSize());
                }
                return query.list();
            }
        });
    }

    /**
     * 执行hql语句查询;hql语句中变量为?;根据动态参数顺序传入查询参数
     */
    public List queryByHqlWithParams(final String hql, final Object... values) {
        return (List) hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                if (values != null) {
                    for (int i = 0; i < values.length; i++) {
                        query.setParameter(i, values[i]);
                    }
                }
                return query.list();
            }
        });
    }

    public List queryBySQL(final String sql, final Map<String, Object> queryParam, final String totalCountHql, final Map<String, Class<?>> entities, final PageBean... pageParam) {
        return (List) hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {

                Query query = session.createSQLQuery(sql);
                if (entities != null) {
                    for (String alias : entities.keySet()) {
                        ((SQLQuery) query).addEntity(alias, entities.get(alias));
                    }
                }
                if (queryParam != null) {
                    query = setParams(query, queryParam);
                }
                if (pageParam.length == 1 && pageParam[0] != null) {
                    if (totalCountHql != null) { // 如果带了需要查询总数的sql，查询总数设置到pagebean中
                        Query countQuery = session.createSQLQuery(totalCountHql);
                        if (queryParam != null) {
                            countQuery = setParams(countQuery, queryParam);
                        }
                        Integer count = Integer.parseInt(countQuery.uniqueResult() + "");
                        pageParam[0].setRecordCount(count);
                    }
                    int start = pageParam[0].getCurrentPage() - 1;
                    start = start >= 0 ? start : 0;
                    query.setFirstResult(start * pageParam[0].getPageSize()).setMaxResults(
                            pageParam[0].getPageSize());
                }
                return query.list();
            }
        });
    }

    public List queryBySQL(final String sql, final Map<String, Object> queryParam, final String totalCountHql, final PageBean... pageParam) {
        return (List) hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {

                Query query = session.createSQLQuery(sql);
                if (entityClass != Object.class) {
                    //暂时注释掉这行
                    ((SQLQuery)query).addEntity(entityClass);
                }
                if (queryParam != null) {
                    query = setParams(query, queryParam);
                }
                if ( pageParam.length == 1 && pageParam[0] != null) {
                    if (totalCountHql != null) { // 如果带了需要查询总数的sql，查询总数设置到pagebean中
                        Query countQuery = session.createSQLQuery(totalCountHql);
                        if (queryParam != null) {
                            countQuery = setParams(countQuery, queryParam);
                        }
                        Integer count = Integer.parseInt(countQuery.uniqueResult() + "");
                        pageParam[0].setRecordCount(count);
                    }
                    int start = pageParam[0].getCurrentPage() - 1;
                    start = start >= 0 ? start : 0;
                    query.setFirstResult(start * pageParam[0].getPageSize()).setMaxResults(
                            pageParam[0].getPageSize());
                }
                return query.list();
            }
        });
    }
    /**
     * 执行更新hql
     *
     * @param hql 更新hql语句
     */
    public Integer executeUpdateHql(final String hql) {
        return (Integer) hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                return query.executeUpdate();
            }
        });
    }
    /**
     * 执行更新hql
     *
     * @param hql
     *            String
     */
    public Integer executeUpdateHql(final String hql, final Map<String, Object> params) {
        return (Integer) hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                if (params != null) {
                    query = setParams(query, params);
                }
                return query.executeUpdate();
            }
        });
    }
    /**
     * 执行更新sql
     *
     * @param sql
     *            String
     */
    public Integer executeUpdateSql(final String sql, final Map<String, Object> params) {
        return (Integer) hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createSQLQuery(sql);
                if (params != null) {
                    query = setParams(query, params);
                }
                return query.executeUpdate();
            }
        });
    }
    /**
     * 根据属性值，批量删除
     *
     * @param propertyName
     * @param value
     * @return
     */
    public Integer deleteByProperty(String propertyName, Object value) {
        Map pv = new HashMap(1);
        pv.put(propertyName, value);
        return deleteByProperties(pv);
    }
    /**
     * 根据属性的值批量删除
     *
     * @param props
     * @return Integer
     */
    public Integer deleteByProperties(Map<String, Object> props) {
        // 构建HQL删除
        String className = entityClass.getSimpleName();
        String alisname = className.toLowerCase();
        StringBuilder hqlbuffer = new StringBuilder("delete from " + entityClass.getName() + " as " + alisname + " where ");

        Iterator<Map.Entry<String, Object>> i = props.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry<String, Object> entry = i.next();
            if (entry.getValue() == null) {
                hqlbuffer.append(" ").append(alisname).append(".").append(entry.getKey()).append(" is null and");
                // 如果是为空的话就不需要props了
                i.remove();
            } else if (entry.getValue() instanceof Collection) {
                if (((Collection) entry.getValue()).isEmpty()) {
                    hqlbuffer.append(" 1<>1 and");
                } else {
                    hqlbuffer.append(" ").append(alisname).append(".").append(entry.getKey()).append(" in(:").append(entry.getKey()).append(") and");
                }

            } else {
                hqlbuffer.append(" ").append(alisname).append(".").append(entry.getKey()).append("=:").append(entry.getKey()).append(" and");
            }
        }
        hqlbuffer.replace(hqlbuffer.length() - 3, hqlbuffer.length(), ""); // 去最后一个and
        return executeUpdateHql(hqlbuffer.toString(), props);
    }
    /**
     * 根据特殊的条件查询
     * @param properties                   属性名称数组，属性名称支持段式："属性名OPERATOR属性值",OPERATOR可以为"=,>,<",字符串类型可以带%查询，表示匹配任意
     * @param orderBy                      按属性排序， 属性名：0|1，0降序，1升序
     * @param pageBean
     */
    public List<T> findByCommonCondition(String[] properties, String[] orderBy, PageBean... pageBean) {
        String[] hqls = convertProps2Hql(properties, orderBy);
        return this.queryByHQL(hqls[0], pageBean.length > 0 ? hqls[1] : null, pageBean);
    }

    public List<T> findByCriteria(DetachedCriteria criteria) {
        return (List<T>)this.hibernateTemplate.findByCriteria(criteria);
    }

    public List<T> findByCriteriaWithPages(final DetachedCriteria criteria, final PageBean... pageParam) {
        Assert.notNull(criteria, "DetachedCriteria must not be null");
        return this.hibernateTemplate.execute(new HibernateCallback<List>() {
            public List doInHibernate(Session session) throws HibernateException {
                Criteria executableCriteria = criteria.getExecutableCriteria(session);
                fillOrderByAndPageParam(executableCriteria, null, pageParam);
                return executableCriteria.list();
            }
        });
    }

    /**
     * 本类使用，属性排序和分页设置
     *
     * @param crit
     *            Criteria
     * @param orderByProperties
     *            OrderBy[]
     * @param pageParam
     *            Integer[]
     */
    private void fillOrderByAndPageParam(Criteria crit, List<? extends OrderCondition> orderByProperties, PageBean... pageParam) {
        if (pageParam != null && pageParam.length == 1 && pageParam[0] != null) {
            // 首先得到总记录数，付给pagebean对象的totalCount值
            crit.setProjection(Projections.rowCount());
            Long totalCount = (Long) crit.uniqueResult();
            pageParam[0].setRecordCount(totalCount.intValue());
            // 将crit投影值空，继续查结果集合
            crit.setProjection(null);
            int start = pageParam[0].getCurrentPage() - 1;
            start = start >= 0 ? start : 0;
            crit.setFirstResult(start * pageParam[0].getPageSize()).setMaxResults(pageParam[0].getPageSize());
        }
        if (orderByProperties != null) {
            for (OrderCondition orderBy : orderByProperties) {
                Order order;
                if (orderBy instanceof DescOrder) {
                    order = Order.desc(orderBy.getPropertyName());
                } else {
                    order = Order.asc(orderBy.getPropertyName());
                }
                crit.addOrder(order);
            }
        }
    }
    /**
     * 构造查询所有条件
     *
     * @param properties
     * @param session
     * @return
     */
    private Criteria convertProperties2Criteria(ConditionSet properties, Session session) {
        Criteria crit = session.createCriteria(entityClass);
        if (properties != null) {
            crit.add(constructCriteriaValue(null, properties));
            List<String> groupBys = properties.getGroups();
            if (groupBys != null && !groupBys.isEmpty()) {
                for (String propGroupBy : groupBys) {
                    crit.setProjection(Projections.groupProperty(propGroupBy));
                }
            }
        }
        return crit;
    }
    /**
     * 构造查询条件
     *
     * @param propertyName
     *            String
     * @param propValue
     *            Object
     * @return Criterion
     */
    private Criterion constructCriteriaValue(String propertyName, Object propValue) {
        Criterion crit = null;
        if (propValue == null) {
            crit = Restrictions.isNull(propertyName);
        } else if (propValue instanceof String && ((String) propValue).contains("%")) {
            // 属性值是字符串类型的并且含有%,用like匹配
            crit = Restrictions.like(propertyName, propValue);
        } else if (propValue instanceof Collection) {
            Collection valueCollection = (Collection) propValue;
            if (valueCollection.isEmpty()) {
                crit = Restrictions.isNull(propertyName);
            } else {
                crit = Restrictions.in(propertyName, valueCollection);
            }

        } else if (propValue instanceof Object[]) {
            Object[] values = (Object[]) propValue;
            if (values.length == 0) {
                crit = Restrictions.isNull(propertyName);
            } else {
                crit = Restrictions.in(propertyName, values);
            }
            crit = Restrictions.in(propertyName, (Object[]) propValue);
        } else if (propValue instanceof CompareCondition) {
            CompareCondition condition = (CompareCondition) propValue;
            if (propertyName == null) {
                propertyName = condition.getPropertyName();
            }
            if (propValue instanceof EqualCondition) {
                crit = constructCriteriaValue(propertyName, condition.getValue());
            } else if (propValue instanceof NotEqualCondition) {
                Object notValue = condition.getValue();
                if (notValue == null) {
                    crit = Restrictions.isNotNull(propertyName);
                } else {
                    crit = Restrictions.ne(propertyName, notValue);
                }
            } else if (propValue instanceof NotInCondition) {
                crit = Restrictions.not(Restrictions.in(propertyName, (Collection) condition.getValue()));
            } else if (propValue instanceof GreaterCondition) {
                crit = Restrictions.gt(propertyName, condition.getValue());
            } else if (propValue instanceof LessThanCondition) {
                crit = Restrictions.lt(propertyName, condition.getValue());
            } else if (propValue instanceof BetweenCondition) {
                crit = Restrictions.between(propertyName, condition.getMinValue(), condition.getMaxValue());
            } else if (propValue instanceof LessOrEqualCondition) {
                crit = Restrictions.le(propertyName, condition.getValue());
            } else if (propValue instanceof GreaterOrEqualCondition) {
                crit = Restrictions.ge(propertyName, condition.getValue());
            }

        }

        else if (propValue instanceof ConditionSet) {
            ConditionSet propMap = (ConditionSet) propValue;
            List<Criterion> ccIt = new ArrayList<Criterion>();
            for (Object condition : propMap.getValues()) {
                ccIt.add(constructCriteriaValue(null, condition));
            }

            if (ccIt.size() > 1) {
                if (propValue instanceof ConditionAndSet) {
                    crit = Restrictions.and(ccIt.get(0), ccIt.get(1));
                    for (int i = 2; i < ccIt.size(); i++) {
                        crit = Restrictions.and(crit, ccIt.get(i));
                    }
                } else {
                    crit = Restrictions.or(ccIt.get(0), ccIt.get(1));
                    for (int i = 2; i < ccIt.size(); i++) {
                        crit = Restrictions.or(crit, ccIt.get(i));

                    }
                }
            } else if (ccIt.size() == 1) {
                crit = ccIt.get(0);
            }
        } else {
            crit = Restrictions.eq(propertyName, propValue);
        }
        return crit;
    }

    private Query setParams(Query resultQuery, Map queryParam) {
        // 设置查询参数
        for (Map.Entry<String, Object> entry : (Set<Map.Entry<String, Object>>) queryParam.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Collection) {
                Collection collection = (Collection) value;
                if (collection.size() < MAX_PARAM_COLLECTION_SIZE) {
                    resultQuery.setParameterList(key, (Collection) value);
                } else {
                    String collectionStr = "'" + StringUtils.join(collection, "','") + "'";
                    resultQuery.setParameter(key, collectionStr);
                }
            } else {
                resultQuery.setParameter(key, value);
            }
        }
        return resultQuery;
    }

    private void handleFieldCondition(StringBuilder sbuffer, String alias, String fieldCondition) {
        if (fieldCondition.startsWith("(")) {
            int count = 1;
            boolean inQuote = false;
            for (int i = 1; i < fieldCondition.length(); i++) {
                if (fieldCondition.charAt(i) == '\'') {
                    inQuote = !inQuote;
                } else if (!inQuote) {
                    if (fieldCondition.charAt(i) == '(') {
                        count++;
                    } else if (fieldCondition.charAt(i) == ')') {
                        count--;
                    }
                }

                if (count == 0) {
                    String first = fieldCondition.substring(1, i).trim();
                    String second = fieldCondition.substring(i + 1).trim();

                    if (second.startsWith("OR")) {
                        sbuffer.append("(");
                        handleFieldCondition(sbuffer, alias, first);
                        sbuffer.append(" OR ");
                        handleFieldCondition(sbuffer, alias, second.substring(2).trim());
                        sbuffer.append(")");
                    } else if (second.startsWith("AND")) {
                        sbuffer.append("(");
                        handleFieldCondition(sbuffer, alias, first);
                        sbuffer.append(" AND ");
                        handleFieldCondition(sbuffer, alias, second.substring(3).trim());
                        sbuffer.append(")");
                    }

                    break;
                }
            }
        } else {
            int pos = fieldCondition.indexOf(" OR ");
            if (pos >= 0) {
                sbuffer.append("(");
                handleFieldCondition(sbuffer, alias, fieldCondition.substring(0, pos).trim());
                sbuffer.append(" OR ");
                handleFieldCondition(sbuffer, alias, fieldCondition.substring(pos + 4).trim());
                sbuffer.append(")");
            } else {
                pos = fieldCondition.indexOf(" AND ");
                if (pos >= 0) {
                    sbuffer.append("(");
                    handleFieldCondition(sbuffer, alias, fieldCondition.substring(0, pos).trim());
                    sbuffer.append(" AND ");
                    handleFieldCondition(sbuffer, alias, fieldCondition.substring(pos + 4).trim());
                    sbuffer.append(")");
                } else {
                    sbuffer.append(alias).append(".").append(fieldCondition.trim());
                }
            }
        }
    }

    private String[] convertProps2Hql(String[] properties, String[] orderBy) {
        String[] results = new String[2];
        String className = this.entityClassName;
        String alias = "_" + className;
        StringBuilder sbuffer = new StringBuilder(" from " + className + " " + alias);
        if (properties != null && properties.length > 0) {
            sbuffer.append(" where ");
        }
        for (int i = 0; properties != null && i < properties.length; i++) {
            String fieldCondition = properties[i];
            handleFieldCondition(sbuffer, alias, fieldCondition.trim());
            if (i < properties.length - 1) {
                sbuffer.append(" and ");
            }
        }
        results[1] = "select count(*) " + sbuffer; // 第二给只有属性的查询总数查询
        if (orderBy != null && orderBy.length > 0) {
            sbuffer.append(" order by ");

            for (String odby : orderBy) {
                String[] obs = odby.split(":");
                sbuffer.append(" ").append(alias).append(".")
                        .append(obs[0]).append(" ")
                        .append("1".equals(obs[1]) ? "asc" : "desc ").append(",");
            }
            sbuffer.delete(sbuffer.length() - 1, sbuffer.length());
        }
        results[0] = sbuffer.toString();
        return results;
    }
}
