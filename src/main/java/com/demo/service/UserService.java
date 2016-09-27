package com.demo.service;

import com.demo.dao.sqlcondition.*;
import com.demo.util.PageBean;
import com.demo.dao.UserDao;
import com.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Queric on 2016/8/2.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public void save(User user){
        userDao.save(user);
    }
    public List<User> findAll(PageBean... pager){
        List<OrderCondition> order=new ArrayList<OrderCondition>();
        DescOrder descOrder=new DescOrder("id");
        order.add(new AscOrder("username"));
        order.add(descOrder);



        ConditionOrSet conditionOrSet=new ConditionOrSet("21",1);
        return userDao.findByProperties(null,order,pager);
    }
    public void add(User user){
        userDao.save(user);
    }

    public void delete(User user) {
        userDao.delete(user);
    }
}