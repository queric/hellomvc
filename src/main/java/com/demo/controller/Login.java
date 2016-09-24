package com.demo.controller;

import com.demo.entity.User;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Queric on 2016/7/14.
 */
@Controller
public class Login {
    @Autowired
   private UserService userService;
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String Index(){
        return "login";
    }

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String DoLogin(String username,String password){
        ModelAndView view=new ModelAndView("login");

        System.out.println(username);

        //userService.save("");
        view.addObject("test","test");
        return "index";
    }
}
