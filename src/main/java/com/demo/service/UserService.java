package com.demo.service;

import com.demo.dao.sqlcondition.*;
import com.demo.util.PageBean;
import com.demo.dao.UserDao;
import com.demo.entity.User;
import org.apache.log4j.Logger;
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

    private static final Logger logger = Logger.getLogger(UserService.class);
    public User findById(Integer id){
        return userDao.findById(id);
    }
    public List<User> findByExample(User user){
        logger.info("this message is from log4j");
        return userDao.findByExample(user);
    }
    public void save(User user){
        userDao.save(user);
    }
    public List<User> findAll(PageBean... pager){
        List<OrderCondition> order=new ArrayList<OrderCondition>();
        DescOrder descOrder=new DescOrder("userId");
        order.add(new AscOrder("username"));
        order.add(descOrder);


        logger.info("this message is from log4j");
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