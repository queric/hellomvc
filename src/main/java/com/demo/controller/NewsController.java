package com.demo.controller;

import com.demo.entity.News;
import com.demo.entity.NewsCatgory;
import com.demo.service.NewsCatgoryService;
import com.demo.service.NewsService;
import com.demo.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private NewsCatgoryService newsCatgoryService;
    @RequestMapping( value = "/{page}", method = RequestMethod.GET)
    public ModelAndView get(@PathVariable Integer page, HttpServletRequest request){
        ModelAndView mav=new ModelAndView("newsList");
        String pageSize = request.getParameter("pageSize");
        if (pageSize == null){
            pageSize = "10";
        }
        PageBean pager=new PageBean(page, Integer.valueOf(pageSize));
        List<News> newsList=newsService.findAll(pager);
        mav.addObject("newsList",newsList);
        List<NewsCatgory> newsCatgories=newsCatgoryService.findAll();
        mav.addObject("catgories",newsCatgories);
        return mav;
    }
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String test(){
        News news=new News();
        news.setNewsTitle("hxxxxxxxxxxxxx");
        news.setNewsContent("dffffffffffffffffffffffffffffff");
        news.setUploadTime(new Date());
        NewsCatgory newsCatgory=new NewsCatgory();
        //newsCatgory.setCatgoryId(1);
        newsCatgory.setCatgoryName("测试类别233");
        news.setNewsCatgory(newsCatgory);
        newsService.save(news);
        return "news";
    }
}
