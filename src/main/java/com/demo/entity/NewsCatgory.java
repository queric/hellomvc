package com.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by queric on 2016/9/21.
 */
@Entity
public class NewsCatgory {
    private int catgoryId;
    private String catgoryName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getCatgoryId() {
        return catgoryId;
    }

    public void setCatgoryId(int catgoryId) {
        this.catgoryId = catgoryId;
    }

    public String getCatgoryName() {
        return catgoryName;
    }

    public void setCatgoryName(String catgoryName) {
        this.catgoryName = catgoryName;
    }
}
