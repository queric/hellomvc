package com.demo.service;

import com.demo.dao.NewsDao;
import com.demo.dao.sqlcondition.ConditionAndSet;
import com.demo.dao.sqlcondition.ConditionFactory;
import com.demo.dao.sqlcondition.ConditionOrSet;
import com.demo.dao.sqlcondition.ConditionSet;
import com.demo.entity.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<News> findAll(){
        //Map<String,Object> params=new HashMap<String, Object>();
        //params.put("newsId",1);
        ConditionOrSet conditionOrSet=ConditionFactory.or()
                .put(ConditionFactory.greaterThan("newsId",1)).put(ConditionFactory.lessOrEqual("newsId",5));
        return  newsDao.findByProperties(conditionOrSet);
    }
}
