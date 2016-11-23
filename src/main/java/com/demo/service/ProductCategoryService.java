package com.demo.service;

import com.demo.dao.ProductCategoryDao;
import com.demo.entity.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Queric on 2016/11/22.
 */
@Service
public class ProductCategoryService {
    @Autowired
    private ProductCategoryDao productCategoryDao;
    public ProductCategory findById(int categoryId){
        return productCategoryDao.findById(categoryId);
    }
    public List<ProductCategory> findAll(){
        return productCategoryDao.findAll();
    }
}
