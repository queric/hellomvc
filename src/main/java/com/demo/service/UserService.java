package com.demo.service;

import com.demo.dao.sqlcondition.CompareCondition;
import com.demo.dao.sqlcondition.DescOrder;
import com.demo.dao.sqlcondition.GreaterCondition;
import com.demo.dao.sqlcondition.OrderCondition;
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
        List<CompareCondition> compare=new ArrayList<CompareCondition>();
        DescOrder descOrder=new DescOrder("id");
        GreaterCondition greaterCondition=new GreaterCondition("id",20);
        order.add(0, descOrder);
        compare.add(0,greaterCondition);
        return userDao.findAll(order,pager);
    }
    public void add(User user){
        userDao.save(user);
    }

    public void delete(User user) {
        userDao.delete(user);
    }
}