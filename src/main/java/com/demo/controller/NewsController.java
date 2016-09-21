package com.demo.controller;

import com.demo.entity.News;
import com.demo.entity.NewsCatgory;
import com.demo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * Created by queric on 2016/9/21.
 */
@Controller
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView get(){
        ModelAndView mav=new ModelAndView("newsList");
        List<News> newsList=newsService.findAll();
        mav.addObject("test",newsList);
        return mav;
    }
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String test(){
        News news=new News();
        news.setNewsTitle("hxxxxxxxxxxxxx");
        news.setNewsContent("dffffffffffffffffffffffffffffff");
        news.setUploadTime(new Date());
        NewsCatgory newsCatgory=new NewsCatgory();
        newsCatgory.setCatgoryId(1);
        newsCatgory.setCatgoryName("测试类别");
        news.setNewsCatgory(newsCatgory);
        newsService.save(news);
        return "news";
    }
}
