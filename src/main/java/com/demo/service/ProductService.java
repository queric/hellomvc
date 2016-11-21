package com.demo.service;

import com.demo.dao.ProductDao;
import com.demo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Queric on 2016/11/21.
 */
@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    public void  add(Product product){
        productDao.save(product);
    }

    public void edit(Product product){
        productDao.update(product);
    }
}
