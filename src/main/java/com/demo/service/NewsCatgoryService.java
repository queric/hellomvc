package com.demo.service;

import com.demo.dao.NewsCatgoryDao;
import com.demo.dao.sqlcondition.ConditionFactory;
import com.demo.dao.sqlcondition.OrderCondition;
import com.demo.entity.NewsCatgory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by queric on 2016/9/26.
 */
@Service
public class NewsCatgoryService {
    @Autowired
    private NewsCatgoryDao newsCatgoryDao;
    public List<NewsCatgory> findAll(){
        List<OrderCondition> conditions = new ArrayList<OrderCondition>();
        conditions.add(ConditionFactory.orderByAsc("catgoryId"));
        return newsCatgoryDao.findAll(conditions);
    }
}
