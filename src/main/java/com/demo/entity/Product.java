package com.demo.entity;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

/**
 * Created by Queric on 2016/11/21.
 */
@Entity
public class Product {
    private int productId;
    private String productName;
    private String remark;
    private int stock;
    private String picPath;
    private String special;//规格
    private String specialUnit;//规格单位
    private Integer priceCents;
    private int recommendLevel;
    private Date updateTime;
    private ProductCategory category;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getSpecialUnit() {
        return specialUnit;
    }

    public void setSpecialUnit(String specialUnit) {
        this.specialUnit = specialUnit;
    }

    public Integer getPriceCents() {
        return priceCents/100;
    }

    public void setPriceCents(Float price) {
        this.priceCents = (int)(price*100);
    }

    public int getRecommendLevel() {
        return recommendLevel;
    }

    public void setRecommendLevel(int recommendLevel) {
        this.recommendLevel = recommendLevel;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @ManyToOne(targetEntity = ProductCategory.class)
    @JoinColumn(name = "categoryId")
    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }
}
