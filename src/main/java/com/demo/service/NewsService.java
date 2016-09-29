package com.demo.service;

import com.demo.dao.NewsDao;
import com.demo.dao.sqlcondition.*;
import com.demo.entity.News;
import com.demo.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by queric on 2016/9/21.
 */
@Service
public class NewsService {
    @Autowired
    private NewsDao newsDao;

    public void save(News news){
        newsDao.save(news);
    }
    public List<News> findAll(PageBean pager){
        //Map<String,Object> params=new HashMap<String, Object>();
        //params.put("newsId",1);

//        ConditionSet conditionAndSet=ConditionFactory.and()
//                .put(ConditionFactory.notEqual("newsId",1)).put(ConditionFactory.notEqual("newsId",5));
//        ConditionSet conditionAndSet2=ConditionFactory.or()
//                .put(ConditionFactory.notEqual("newsId",2)).put(ConditionFactory.notEqual("newsId",3));
//        conditionAndSet.put(conditionAndSet2);
        List<OrderCondition> order=new ArrayList<OrderCondition>();
        //order.add(ConditionFactory.orderByDesc("catgoryId"));
        return  newsDao.queryByHQL("from News as news order by news.newsCatgory.catgoryId asc,news.newsId asc",null,pager);
    }
}
