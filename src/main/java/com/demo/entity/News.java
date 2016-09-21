package com.demo.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by queric on 2016/9/21.
 */
@Entity
public class News {
    private int newsId;
    private String newsTitle;
    private String newsContent;
    private Date uploadTime;
    private NewsCatgory newsCatgory;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    @Column(length = 1000,nullable = false)
    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    @ManyToOne(targetEntity = NewsCatgory.class)
    @JoinColumn( name = "catgoryId", referencedColumnName = "catgoryId")
    public NewsCatgory getNewsCatgory() {
        return newsCatgory;
    }

    public void setNewsCatgory(NewsCatgory newsCatgory) {
        this.newsCatgory = newsCatgory;
    }
}
